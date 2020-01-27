package de.cecon.demo.logistik;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import de.cecon.CECon;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigInteger;

public class PushShippingConfirmation implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("LOGISTIK: Versandbestaetigung pushen:");

        //CECon Instanz laden
        CECon cecon = CECon.getInstance();

        CeconDemoContract contract = (CeconDemoContract) cecon.getSmartContractFromInstance("logistik");

        BigInteger orderid = BigInteger.valueOf((long) delegateExecution.getVariable("orderid"));
        cecon.callFunction(contract.addShippingConfirmation(orderid));

        System.out.println("Versandbestaetigung für Bestellung (ID: " + orderid + ") geloggt");
    }
}
