package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.TransportDTO;

@Repository
public class TransportDAOImpl extends BaseDAOImpl<TransportDTO> implements TransportDAOInt {

	@Override
	public Class<TransportDTO> getDTOClass() {
		return TransportDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(TransportDTO dto, CriteriaBuilder builder, Root<TransportDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<>();

		if (!isEmptyString(dto.getTransportId())) {
			whereCondition.add(builder.equal(qRoot.get("transportId"), dto.getTransportId()));
		}

		if (!isEmptyString(dto.getVehicleType())) {
			whereCondition.add(builder.like(qRoot.get("vehicleType"), dto.getVehicleType() + "%"));
		}

		if (!isEmptyString(dto.getDriverName())) {
			whereCondition.add(builder.like(qRoot.get("driverName"), dto.getDriverName() + "%"));
		}

		if (dto.getCharges() != null) {
			whereCondition.add(builder.equal(qRoot.get("charges"), dto.getCharges()));
		}

		return whereCondition;
	}
}