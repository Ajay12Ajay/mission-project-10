package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_emi")
public class EMIDTO extends BaseDTO {

	@Column(name = "emiId", length = 50)
	private String emiId;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "dueDate")
	private Date dueDate;

	@Column(name = "status", length = 50)
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
	public String getValue() {

		return String.valueOf(amount);
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "emiId";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return emiId;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "emiId";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "EMI";
	}

}
