package de.cecon.demo.lieferant;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MSG_SendShippingDate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("shipping_date")
                .processInstanceBusinessKey(delegateExecution.getBusinessKey())
                .correlate();
    }
}
