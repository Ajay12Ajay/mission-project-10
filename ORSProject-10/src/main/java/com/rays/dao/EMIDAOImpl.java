package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.EMIDTO;

@Repository
public class EMIDAOImpl extends BaseDAOImpl<EMIDTO> implements EMIDAOInt {

	@Override
	public Class<EMIDTO> getDTOClass() {
		return EMIDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(EMIDTO dto, CriteriaBuilder builder, Root<EMIDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getEmiId())) {
			whereCondition.add(builder.like(qRoot.get("emiId"), dto.getEmiId() + "%"));
		}

		if (!isZeroNumber(dto.getAmount())) {
			whereCondition.add(builder.equal(qRoot.get("amount"), dto.getAmount()));
		}

		if (isNotNull(dto.getDueDate())) {
			whereCondition.add(builder.equal(qRoot.get("dueDate"), dto.getDueDate()));
		}

		if (!isEmptyString(dto.getStatus())) {
			whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus() + "%"));
		}
		return whereCondition;
	}

}
