package com.bandhan.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("validateOrder")
public class ValidateOrderDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String itemId = (String) execution.getVariable("itemId");
        Integer noOfItems = (Integer) execution.getVariable("noOfItems");
        String customerType = (String) execution.getVariable("customerType");
        boolean isValidOrder = itemId != null && !itemId.isEmpty() && noOfItems > 0 && customerType != null;
        execution.setVariable("isValidOrder", isValidOrder);
    }
}
