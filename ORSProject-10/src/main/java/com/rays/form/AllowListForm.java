package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.AllowListDTO;

public class AllowListForm extends BaseForm {

    @NotEmpty(message = "allowCode is required")
    private String allowCode;

    @NotEmpty(message = "userName is required")
    private String userName;

    @NotEmpty(message = "source is required")
    private String source;

    @NotEmpty(message = "status is required")
    private String status;

    // Getters & Setters

    public String getAllowCode() {
        return allowCode;
    }

    public void setAllowCode(String allowCode) {
        this.allowCode = allowCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public BaseDTO getDto() {

        AllowListDTO dto = initDTO(new AllowListDTO());

        dto.setAllowCode(allowCode);
        dto.setUserName(userName);
        dto.setSource(source);
        dto.setStatus(status);

        return dto;
    }
}