package com.bandhan.camunda.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private int itemId;
    private CustomerDetails customerDetails;
    private int noOfItems;

    @Data
    public static class CustomerDetails {
        private String pincode;
        private String mobileNumber;
        private String name;
        private String customerType;
    }
}