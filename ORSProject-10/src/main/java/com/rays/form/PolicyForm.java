package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.PolicyDTO;

public class PolicyForm extends BaseForm {

    @NotEmpty(message = "policyId is required")
    private String policyId;

    @NotEmpty(message = "policyName is required")
    private String policyName;

    @NotNull(message = "premiumAmount is required")
    private Double premiumAmount;

    @NotNull(message = "startDate is required")
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

    @Override
    public BaseDTO getDto() {

        PolicyDTO dto = initDTO(new PolicyDTO());

        dto.setPolicyId(policyId);
        dto.setPolicyName(policyName);
        dto.setPremiumAmount(premiumAmount);
        dto.setStartDate(startDate);

        return dto;
    }
}