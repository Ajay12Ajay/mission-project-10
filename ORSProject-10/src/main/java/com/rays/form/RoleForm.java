package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.RoleDTO;

/**
 * Form bean for Role add/update operations. Both {@code name} and
 * {@code description} are required.
 *
 * @author Ajay Pratap Kerketta
 */
public class RoleForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Description is required")
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

	/** @return a populated {@link RoleDTO} built from this form's fields. */
	@Override
	public BaseDTO getDto() {
		// TODO Auto-generated method stub
		RoleDTO dto = initDTO(new RoleDTO());
		dto.setName(name);
		dto.setDescription(description);
		return dto;
	}

}