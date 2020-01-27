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

public class PullShippingConfirmation implements JavaDelegate {

    private CECon cecon;
    private CeconDemoContract contract;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("HERSTELLER: Versandbestaetigung pullen:");

        //CECon Instanz laden
        cecon = CECon.getInstance();

        contract = (CeconDemoContract) cecon.getSmartContractFromInstance("hersteller");

        long orderid = (long) delegateExecution.getVariable("orderid");

        pullConfirmation(orderid);

        System.out.println("Versandbestaetigung der Bestellung (ID: " + orderid + ") empfangen");
    }

    private void pullConfirmation(long orderid) {
        List<Long> shippedOrders = new ArrayList<>();

        Subscription orderSub = contract.shippingConfirmationEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(response -> shippedOrders.add(response.orderID.longValue()));

        while(!shippedOrders.contains(orderid)) {

        }
    }
}
