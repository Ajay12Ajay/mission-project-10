package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Entity DTO mapped to {@code st_role}. Unique key: {@code name}. Used in
 * dropdown lists with {@code name} as the display value.
 *
 * @author Ajay Pratap Kerketta
 */
@Entity
@Table(name = "st_role")
public class RoleDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "DESCRIPTION", length = 50)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** @return {@code name} — used as dropdown display value. */
	@Override
	public String getValue() {
		return name;
	}

	/** @return {@code "name"} */
	@Override
	public String getUniqueKey() {
		return "name";
	}

	/** @return {@code name} */
	@Override
	public String getUniqueValue() {
		return name;
	}

	/** @return {@code "Role Name"} */
	@Override
	public String getLabel() {
		return "Role Name";
	}

	/** @return {@code "Role"} */
	@Override
	public String getTableName() {
		return "Role";
	}

}