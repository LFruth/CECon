package de.cecon.demo.hersteller;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class MSG_SendNewOrderToSupplier implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> variables = new HashMap();
        variables.put("productname", delegateExecution.getVariable("product_name"));
        variables.put("productcount", delegateExecution.getVariable("product_count"));
        variables.put("details", delegateExecution.getVariable("details"));

        delegateExecution.getProcessEngineServices().getRuntimeService().
                startProcessInstanceByMessage("new_order_supplier", delegateExecution.getBusinessKey(), variables);
    }
}
