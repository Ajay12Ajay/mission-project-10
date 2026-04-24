package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.DataExportLogDTO;

public class DataExportLogForm extends BaseForm {

	@NotEmpty(message = "exportLogCode is required")
	private String exportLogCode;

	@NotEmpty(message = "fileName is required")
	private String fileName;

	@NotEmpty(message = "exportedBy is required")
	private String exportedBy;

	@NotEmpty(message = "status is required")
	private String status;

	// Getters & Setters

	public String getExportLogCode() {
		return exportLogCode;
	}

	public void setExportLogCode(String exportLogCode) {
		this.exportLogCode = exportLogCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExportedBy() {
		return exportedBy;
	}

	public void setExportedBy(String exportedBy) {
		this.exportedBy = exportedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public BaseDTO getDto() {

		DataExportLogDTO dto = initDTO(new DataExportLogDTO());

		dto.setExportLogCode(exportLogCode);
		dto.setFileName(fileName);
		dto.setExportedBy(exportedBy);
		dto.setStatus(status);

		return dto;
	}
}