package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;

@Repository
public class MarksheetDAOImpl extends BaseDAOImpl<MarksheetDTO> implements MarksheetDAOInt {

	@Autowired
	StudentDAOInt studentDao = null;

	@Override
	public Class<MarksheetDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return MarksheetDTO.class;
	}

	@Override
	protected void populate(MarksheetDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub

		if (dto.getStudentId() != null) {
			StudentDTO studentDto = studentDao.findByPk(dto.getStudentId(), userContext);
			if (studentDto != null) {
				dto.setName(studentDto.getFirstName() + " " + studentDto.getLastName());
			}
		}
	}

	@Override
	protected List<Predicate> getWhereClause(MarksheetDTO dto, CriteriaBuilder builder, Root<MarksheetDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getRollNo())) {

			whereCondition.add(builder.like(qRoot.get("rollNo"), dto.getRollNo() + "%"));
		}

		if (!isZeroNumber(dto.getStudentId())) {

			whereCondition.add(builder.equal(qRoot.get("studentId"), dto.getStudentId()));
		}

		return whereCondition;
	}

	@Override
	public List<MarksheetDTO> getMeritList() {
		List list = super.marksheetMeritList("from MarksheetDTO order by (physics+chemistry+maths) desc", null);
		return list;
	}

}
