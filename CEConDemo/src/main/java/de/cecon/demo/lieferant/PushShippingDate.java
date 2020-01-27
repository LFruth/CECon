package de.cecon.demo.lieferant;

import de.cecon.CECon;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigInteger;

public class PushShippingDate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("LIEFERANT: Versanddatum pushen:");

        //CECon Instanz laden
        CECon cecon = CECon.getInstance();

        CeconDemoContract contract = (CeconDemoContract) cecon.getSmartContractFromInstance("lieferant");


        long orderid = (long) delegateExecution.getVariable("orderid");
        long day = (long) delegateExecution.getVariable("day");
        long month = (long) delegateExecution.getVariable("month");
        long year = (long) delegateExecution.getVariable("year");

        cecon.callFunction(contract.addShippingDetails(BigInteger.valueOf(orderid), BigInteger.valueOf(year),
                BigInteger.valueOf(month), BigInteger.valueOf(day)));

        System.out.println("Versanddatum (" + day + "." + month + "." + year + ") " +
                        "geloggt für Bestellung (ID: " + orderid + ")");
    }
}
