package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.TransportDTO;

public class TransportForm extends BaseForm {

	@NotEmpty(message = "transportId is required")
	@Pattern(regexp = "^[A-Z]+-[0-9]+$")
	private String transportId;

	@NotEmpty(message = "vehicleType is required")
	private String vehicleType;

	@NotEmpty(message = "driverName is required")
	private String driverName;

	@NotNull(message = "charges is required")
	private Double charges;

	// Getters & Setters

	public String getTransportId() {
		return transportId;
	}

	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	@Override
	public BaseDTO getDto() {

		TransportDTO dto = initDTO(new TransportDTO());

		dto.setTransportId(transportId);
		dto.setVehicleType(vehicleType);
		dto.setDriverName(driverName);
		dto.setCharges(charges);

		return dto;
	}
}