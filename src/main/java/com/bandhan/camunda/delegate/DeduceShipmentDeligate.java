package com.bandhan.camunda.delegate;

import com.bandhan.camunda.constants.CustomerType;
import com.bandhan.camunda.constants.ItemType;
import com.bandhan.camunda.constants.ShipmentType;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("deduceShipment")
public class DeduceShipmentDeligate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String customerType = (String) execution.getVariable("customerType");
        String itemType = (String) execution.getVariable("itemType");
        if (CustomerType.VIP.equals(CustomerType.valueOf(customerType))) {
            execution.setVariable("shipmentType", ShipmentType.EXPRESS.name());
        } else if (ItemType.Electronics.name().equals(itemType)) {
            execution.setVariable("shipmentType", ShipmentType.EXPRESS.name());
        } else {
            execution.setVariable("shipmentType", ShipmentType.NORMAL.name());
        }
    }
}
