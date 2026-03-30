package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_order")
public class OrderDTO extends BaseDTO {

    @Column(name = "orderCode", length = 50, unique = true)
    private String orderCode;

    @Column(name = "customerName", length = 50)
    private String customerName;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Column(name = "status", length = 50)
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

    // BaseDTO methods

    @Override
    public String getValue() {
        return status;
    }

    @Override
    public String getUniqueKey() {
        return "orderCode";
    }

    @Override
    public String getUniqueValue() {
        return orderCode;
    }

    @Override
    public String getLabel() {
        return "orderCode";
    }

    @Override
    public String getTableName() {
        return "Order";
    }
}