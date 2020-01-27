package de.cecon.demo.hersteller;

import de.cecon.CECon;
import de.cecon.CEConUtils;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import rx.Subscription;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PushPayment implements JavaDelegate {

    private CECon cecon;
    private CeconDemoContract contract;

    private BigInteger orderid, costs;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("HERSTELLER: Bezahlung pushen:");

        //CECon Instanz laden
        cecon = CECon.getInstance();

        contract = (CeconDemoContract) cecon.getSmartContractFromInstance("hersteller");

        //orderid & costs ermitteln
        System.out.println("Kosten der Bestellung abfragen...");
        pullOrderInformations();
        delegateExecution.setVariable("orderid", (long) orderid.longValue());

        System.out.println("Bestellung bezahlen...");
        cecon.callFunction(contract.addPayment(orderid, costs));

        System.out.println("Bestellung (ID: " + orderid + ") bezahlt! (" + costs + " WEI)");
    }


    private void pullOrderInformations() {
        List<CeconDemoContract.NewOrderEventResponse> orders = new ArrayList<>();
        List<BigInteger> sentOrders = new ArrayList<>();
        List<BigInteger> pendingOrders = new ArrayList<>();
        List<BigInteger> payedOrders = new ArrayList<>();

        //Events subscriben:

        Subscription orderSub = contract.newOrderEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(orderResponse -> orders.add(orderResponse));

        Subscription sentSub = contract.shippingConfirmationEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(orderID -> sentOrders.add(orderID.orderID));

        Subscription pendingSub = contract.pendingOrderEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(orderID -> pendingOrders.add(orderID.orderID));

        Subscription payedSub = contract.payedOrderEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(response -> payedOrders.add(response.orderID));

        CeconDemoContract.NewOrderEventResponse newestOrder = null;

        while(newestOrder == null) {
            for(CeconDemoContract.NewOrderEventResponse o : orders) {
                if (!sentOrders.contains(o.orderID) && !pendingOrders.contains(o.orderID) && !payedOrders.contains(o.orderID)) {
                    newestOrder = o;
                }
            }
        }

        orderid = newestOrder.orderID;
        costs = newestOrder.orderCosts;
    }
}
