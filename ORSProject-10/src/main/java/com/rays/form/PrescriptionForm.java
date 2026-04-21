package com.rays.form;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.PrescriptionDTO;

public class PrescriptionForm extends BaseForm {

	@NotEmpty(message = "prescriptionId is required")
	private String prescriptionId;

	@NotEmpty(message = "patientName is required")
	private String patientName;

	@NotEmpty(message = "doctorName is required")
	private String doctorName;

	@NotNull(message = "prescribedDate is required")
	private Date prescribedDate;

	public String getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getPrescribedDate() {
		return prescribedDate;
	}

	public void setPrescribedDate(Date prescribedDate) {
		this.prescribedDate = prescribedDate;
	}

	@Override
	public BaseDTO getDto() {

		PrescriptionDTO dto = initDTO(new PrescriptionDTO());

		dto.setPrescriptionId(prescriptionId);
		dto.setPatientName(patientName);
		dto.setDoctorName(doctorName);
		dto.setPrescribedDate(prescribedDate);

		return dto;
	}

}
