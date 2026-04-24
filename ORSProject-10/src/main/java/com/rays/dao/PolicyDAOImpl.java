package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.PolicyDTO;

@Repository
public class PolicyDAOImpl extends BaseDAOImpl<PolicyDTO> implements PolicyDAOInt {

	@Override
	public Class<PolicyDTO> getDTOClass() {
		return PolicyDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(PolicyDTO dto, CriteriaBuilder builder, Root<PolicyDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<>();

		if (!isEmptyString(dto.getPolicyId())) {
			whereCondition.add(builder.equal(qRoot.get("policyId"), dto.getPolicyId()));
		}

		if (!isEmptyString(dto.getPolicyName())) {
			whereCondition.add(builder.like(qRoot.get("policyName"), dto.getPolicyName() + "%"));
		}

		if (dto.getPremiumAmount() != null) {
			whereCondition.add(builder.equal(qRoot.get("premiumAmount"), dto.getPremiumAmount()));
		}

		if (isNotNull(dto.getStartDate())) {
			whereCondition.add(builder.equal(qRoot.get("startDate"), dto.getStartDate()));
		}

		return whereCondition;
	}
}