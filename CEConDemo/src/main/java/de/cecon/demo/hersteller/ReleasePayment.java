package de.cecon.demo.hersteller;

import de.cecon.CECon;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigInteger;

public class ReleasePayment implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("HERSTELLER: Bezahlung an Lieferanten freigeben.");

        //CECon Instanz laden
        CECon cecon = CECon.getInstance();

        CeconDemoContract contract = (CeconDemoContract) cecon.getSmartContractFromInstance("hersteller");

        long orderid = (long) delegateExecution.getVariable("orderid");

        cecon.callFunction(contract.releasePayment(BigInteger.valueOf(orderid)));

        System.out.println("Bezahlung an Lieferant freigegeben und geloggt");

    }
}
