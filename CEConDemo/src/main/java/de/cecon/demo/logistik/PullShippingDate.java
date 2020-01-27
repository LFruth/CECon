package de.cecon.demo.logistik;

import de.cecon.CECon;
import de.cecon.CEConUtils;
import de.cecon.demo.ethereum.CeconDemoContract;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import rx.Subscription;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PullShippingDate implements JavaDelegate {
    private CECon cecon;
    private CeconDemoContract contract;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("___________________________________");
        System.out.println("LOGISTIK: Versanddatum pullen:");

        //CECon Instanz laden
        cecon = CECon.getInstance();

        contract = (CeconDemoContract) cecon.getSmartContractFromInstance("logistik");

        //Auf neueste Bestellung warten...
        CeconDemoContract.ShippingDetailsEventResponse shippingDetailsResponse = pullShippingDetails((long) delegateExecution.getVariable("orderid"));

        delegateExecution.setVariable("day", shippingDetailsResponse.day.longValue());
        delegateExecution.setVariable("month", shippingDetailsResponse.month.longValue());
        delegateExecution.setVariable("year", shippingDetailsResponse.year.longValue());

        System.out.println("Versanddatum erfolgreich empfangen: " + shippingDetailsResponse.day + "." + shippingDetailsResponse.month +
                "." + shippingDetailsResponse.year);
    }

    private CeconDemoContract.ShippingDetailsEventResponse pullShippingDetails(long orderid) {
        List<CeconDemoContract.ShippingDetailsEventResponse> shippingDetails = new ArrayList<>();

        Subscription shippingSub = contract.shippingDetailsEventObservable(CEConUtils.EARLIEST_BLOCK, CEConUtils.LATEST_BLOCK)
                .subscribe(orderResponse -> shippingDetails.add(orderResponse));

        CeconDemoContract.ShippingDetailsEventResponse details = null;

        while(details == null) {
            for(CeconDemoContract.ShippingDetailsEventResponse s : shippingDetails) {
                if (s.orderID.longValue() == orderid) {
                    details = s;
                }
            }
        }

        return details;
    }

}
