package de.cecon.demo.lieferant;

import de.cecon.CECon;
import de.cecon.CEConUtils;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import rx.Subscription;

import javax.tools.JavaCompiler;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PullNewOrder implements JavaDelegate {

    private CECon cecon;
    private CeconDemoContract contract;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("LIEFERANT: Neueste Bestellung pullen:");

        //CECon Instanz laden
        cecon = CECon.getInstance();

        contract = (CeconDemoContract) cecon.getSmartContractFromInstance("lieferant");

        //Auf neueste Bestellung warten...
        CeconDemoContract.NewOrderEventResponse newOrder = pullOrder();

        System.out.println("Bestellung erfolgreich empfangen.");

        //Informationen der Bestellung als Variable merken
        delegateExecution.setVariable("orderid", newOrder.orderID.longValue());
        delegateExecution.setVariable("productname", newOrder.productName);
        delegateExecution.setVariable("productcount", newOrder.productCount.longValue());
        delegateExecution.setVariable("details", newOrder.orderDetails);
        delegateExecution.setVariable("costs", newOrder.orderCosts.longValue());


        cecon.callFunction(contract.addPendingOrder(newOrder.orderID));
        System.out.println("Bestellung (ID: " + newOrder.orderID + ") als \"laufend\" geloggt.");
    }

    private CeconDemoContract.NewOrderEventResponse pullOrder() {
        List<CeconDemoContract.NewOrderEventResponse> orders = new ArrayList<>();
        List<BigInteger> sentOrders = new ArrayList<>();
        List<BigInteger> pendingOrders = new ArrayList<>();
        List<BigInteger> payedOrders = new ArrayList<>();

        Subscription orderSub = contract.newOrderEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(orderResponse -> orders.add(orderResponse));

        Subscription sentSub = contract.shippingConfirmationEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(response -> sentOrders.add(response.orderID));

        Subscription pendingSub = contract.pendingOrderEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(response -> pendingOrders.add(response.orderID));

        Subscription payedSub = contract.payedOrderEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(response -> payedOrders.add(response.orderID));

        CeconDemoContract.NewOrderEventResponse newOrder = null;

        //Warten bis eine neue schon bezahlte Bestellung ankommt
        while(newOrder == null) {
            for(CeconDemoContract.NewOrderEventResponse o : orders) {
                if (!sentOrders.contains(o.orderID) && !pendingOrders.contains(o.orderID) && payedOrders.contains(o.orderID)) {
                    newOrder = o;
                }
            }
        }

        return newOrder;
    }

}
