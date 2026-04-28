package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_transport")
public class TransportDTO extends BaseDTO {

    @Column(name = "transportId", length = 50, unique = true)
    private String transportId;

    @Column(name = "vehicleType", length = 100)
    private String vehicleType;

    @Column(name = "driverName", length = 100)
    private String driverName;

    @Column(name = "charges")
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

    // BaseDTO methods

    @Override
    public String getValue() {
        return vehicleType;
    }

    @Override
    public String getUniqueKey() {
        return "transportId";
    }

    @Override
    public String getUniqueValue() {
        return transportId;
    }

    @Override
    public String getLabel() {
        return "transportId";
    }

    @Override
    public String getTableName() {
        return "Transport";
    }
}