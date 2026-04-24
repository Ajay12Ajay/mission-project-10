package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_dataexportlog")
public class DataExportLogDTO extends BaseDTO {

    @Column(name = "exportLogCode", length = 50, unique = true)
    private String exportLogCode;

    @Column(name = "fileName", length = 100)
    private String fileName;

    @Column(name = "exportedBy", length = 100)
    private String exportedBy;

    @Column(name = "status", length = 50)
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

    // BaseDTO methods

    @Override
    public String getValue() {
        return fileName;
    }

    @Override
    public String getUniqueKey() {
        return "exportLogCode";
    }

    @Override
    public String getUniqueValue() {
        return exportLogCode;
    }

    @Override
    public String getLabel() {
        return "exportLogCode";
    }

    @Override
    public String getTableName() {
        return "DataExportLog";
    }
}