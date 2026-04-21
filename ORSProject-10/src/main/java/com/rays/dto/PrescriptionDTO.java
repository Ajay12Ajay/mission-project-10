package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_prescription")
public class PrescriptionDTO extends BaseDTO {

	@Column(name = "prescriptionId", length = 50)
	private String prescriptionId;

	@Column(name = "patientName", length = 50)
	private String patientName;

	@Column(name = "doctorName", length = 50)
	private String doctorName;

	@Column(name = "prescribedDate", length = 50)
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
	public String getValue() {
		// TODO Auto-generated method stub
		return doctorName;
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "prescriptionId";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return prescriptionId;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Prescription Id";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Prescription";
	}

}
