package de.cecon.demo.logistik;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MSG_SendShippingConfirmation implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("receive_shipping_confirmation")
                .processInstanceBusinessKey(delegateExecution.getBusinessKey())
                .correlate();
    }
}
