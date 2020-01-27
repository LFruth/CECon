package de.cecon.demo.hersteller;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MSG_PaymentReleased implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("receive_payment")
                .processInstanceBusinessKey(delegateExecution.getBusinessKey())
                .correlate();

    }
}
