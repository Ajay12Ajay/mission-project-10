package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_policy")
public class PolicyDTO extends BaseDTO {

    @Column(name = "policyId", length = 50, unique = true)
    private String policyId;

    @Column(name = "policyName", length = 100)
    private String policyName;

    @Column(name = "premiumAmount")
    private Double premiumAmount;

    @Column(name = "startDate")
    private Date startDate;

    // Getters & Setters

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public Double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(Double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // BaseDTO methods

    @Override
    public String getValue() {
        return policyName;
    }

    @Override
    public String getUniqueKey() {
        return "policyId";
    }

    @Override
    public String getUniqueValue() {
        return policyId;
    }

    @Override
    public String getLabel() {
        return "policyId";
    }

    @Override
    public String getTableName() {
        return "Policy";
    }
}