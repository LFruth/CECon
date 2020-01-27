package de.cecon.demo.hersteller;

import de.cecon.CECon;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigInteger;

public class PushNewOrder implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("HERSTELLER: Neue Bestellung pushen:");

        //CECon Instanz laden
        CECon cecon = CECon.getInstance();

        CeconDemoContract contract = (CeconDemoContract) cecon.getSmartContractFromInstance("hersteller");


        String productName = (String) execution.getVariable("product_name");
        long productID = getProductID(productName);
        long productCount = (long) execution.getVariable("product_count");
        String orderDetails = (String) execution.getVariable("details");

        cecon.callFunction(contract.addNewOrder(BigInteger.valueOf(productID), productName,
                BigInteger.valueOf(productCount), orderDetails));

        System.out.println("Neue Bestellung geloggt: \n" +
                "Produkt Name: " + productName + "\n" +
                "Produkt Anzahl: " + productCount + "\n" +
                "Bestelldetails: " + orderDetails);
    }

    private long getProductID(String productName) {
        if(productName.equals("kiefernholz"))
            return 0;
        else if(productName.equals("buchenholz"))
            return 1;
        else if(productName.equals("eschenholz"))
            return 2;
        else if(productName.equals("eichenholz"))
            return 3;
        else
            return 0;
    }
}
