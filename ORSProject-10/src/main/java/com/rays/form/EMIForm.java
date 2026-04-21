package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.EMIDTO;

public class EMIForm extends BaseForm {

	@NotEmpty(message = "emiId is required")
	private String emiId;

	@NotNull(message = "amount is required")
	private Double amount;

	@NotNull(message = "dueDate is required")
	private Date dueDate;

	@NotEmpty(message = "status is required")
	private String status;

	public String getEmiId() {
		return emiId;
	}

	public void setEmiId(String emiId) {
		this.emiId = emiId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public BaseDTO getDto() {

		EMIDTO dto = initDTO(new EMIDTO());

		dto.setEmiId(emiId);
		dto.setAmount(amount);
		dto.setDueDate(dueDate);
		dto.setStatus(status);

		return dto;
	}

}
