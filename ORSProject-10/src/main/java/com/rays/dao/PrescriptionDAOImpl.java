package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.PrescriptionDTO;

@Repository
public class PrescriptionDAOImpl extends BaseDAOImpl<PrescriptionDTO> implements PrescriptionDAOInt {

	@Override
	public Class<PrescriptionDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return PrescriptionDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(PrescriptionDTO dto, CriteriaBuilder builder,
			Root<PrescriptionDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getPrescriptionId())) {
			whereCondition.add(builder.like(qRoot.get("prescriptionId"), dto.getPrescriptionId() + "%"));
		}

		if (!isEmptyString(dto.getPatientName())) {
			whereCondition.add(builder.like(qRoot.get("patientName"), dto.getPatientName() + "%"));
		}

		if (!isEmptyString(dto.getDoctorName())) {
			whereCondition.add(builder.like(qRoot.get("doctorName"), dto.getDoctorName() + "%"));
		}

		if (isNotNull(dto.getPrescribedDate())) {
			whereCondition.add(builder.equal(qRoot.get("prescribedDate"), dto.getPrescribedDate()));
		}

		return whereCondition;
	}

}
