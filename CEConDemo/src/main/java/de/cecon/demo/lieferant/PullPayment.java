package de.cecon.demo.lieferant;

import de.cecon.CECon;
import de.cecon.CEConUtils;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import rx.Subscription;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PullPayment implements JavaDelegate {
    private CECon cecon;
    private CeconDemoContract contract;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("LIEFERANT: Bezahlungsfreigabe pullen:");

        //CECon Instanz laden
        cecon = CECon.getInstance();

        contract = (CeconDemoContract) cecon.getSmartContractFromInstance("lieferant");

        long orderid = (long) delegateExecution.getVariable("orderid");

        pullReleasedPayment(orderid);

        System.out.println("Bestellung (ID: " + orderid + ") wurde erfolgreich bezahlt.");

        cecon.disconnect();
    }


    private void pullReleasedPayment(long orderid) {
        List<Long> releasedPayments = new ArrayList<>();

        Subscription sub = contract.paymentReleasedEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(response -> releasedPayments.add(response.orderID.longValue()));

        while(!releasedPayments.contains(orderid)) {

        }

    }
}
