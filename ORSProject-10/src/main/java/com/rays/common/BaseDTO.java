package com.rays.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseDTO implements DropdownList {

	@Id
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	protected long id;

	@Column(name = "CREATED_BY", length = 50)
	protected String createdBy;

	@Column(name = "MODIFIED_BY", length = 50)
	protected String modifiedBy;

	@Column(name = "CREATED_DATETIME")
	protected Timestamp createdDatetime;

	@Column(name = "MODIFIED_DATETIME")
	protected Timestamp modifiedDatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public abstract String getUniqueKey();

	public abstract String getUniqueValue();

	public abstract String getLabel();

	public abstract String getTableName();

	@Override
	public String getKey() {

		return String.valueOf(id);
	}

}
