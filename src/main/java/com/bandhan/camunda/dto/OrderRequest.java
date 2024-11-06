package com.bandhan.camunda.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRequest implements Serializable {
    private int itemId;
    private CustomerDetails customerDetails;
    private int noOfItems;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CustomerDetails implements Serializable {
        private String pincode;
        private String mobileNumber;
        private String name;
        private String customerType;
    }
}