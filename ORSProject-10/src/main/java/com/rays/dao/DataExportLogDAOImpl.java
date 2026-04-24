package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.DataExportLogDTO;

@Repository
public class DataExportLogDAOImpl extends BaseDAOImpl<DataExportLogDTO> implements DataExportLogDAOInt {

	@Override
	public Class<DataExportLogDTO> getDTOClass() {
		return DataExportLogDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(DataExportLogDTO dto, CriteriaBuilder builder,
			Root<DataExportLogDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<>();

		if (!isEmptyString(dto.getExportLogCode())) {
			whereCondition.add(builder.equal(qRoot.get("exportLogCode"), dto.getExportLogCode()));
		}

		if (!isEmptyString(dto.getFileName())) {
			whereCondition.add(builder.like(qRoot.get("fileName"), dto.getFileName() + "%"));
		}

		if (!isEmptyString(dto.getExportedBy())) {
			whereCondition.add(builder.like(qRoot.get("exportedBy"), dto.getExportedBy() + "%"));
		}

		if (!isEmptyString(dto.getStatus())) {
			whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus() + "%"));
		}

		return whereCondition;
	}
}