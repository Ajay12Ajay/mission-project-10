package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.OrderDTO;

public class OrderForm extends BaseForm {

    @NotEmpty(message = "orderCode is required")
    @Pattern(regexp = "^[A-Z]+-[0-9]+$")
    private String orderCode;

    @NotEmpty(message = "customerName is required")
    private String customerName;

    @NotNull(message = "totalAmount is required")
    private Double totalAmount;

    @NotEmpty(message = "status is required")
    private String status;

    // Getters & Setters

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public BaseDTO getDto() {

        OrderDTO dto = initDTO(new OrderDTO());

        dto.setOrderCode(orderCode);
        dto.setCustomerName(customerName);
        dto.setTotalAmount(totalAmount);
        dto.setStatus(status);

        return dto;
    }
}