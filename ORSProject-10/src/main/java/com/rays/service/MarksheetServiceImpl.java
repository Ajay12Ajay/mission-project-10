package com.rays.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.MarksheetDAOInt;
import com.rays.dto.MarksheetDTO;

@Service
@Transactional
public class MarksheetServiceImpl extends BaseServiceImpl<MarksheetDTO, MarksheetDAOInt>
		implements MarksheetServiceInt {

	@Override
	public MarksheetDTO findByName(String name, UserContext userContext) {
		// TODO Auto-generated method stub
		return dao.findByUniqueKey("name", name, userContext);
	}

	@Override
	public MarksheetDTO findByRollNo(String rollNo, UserContext userContext) {
		// TODO Auto-generated method stub
		return dao.findByUniqueKey("rollNo", rollNo, userContext);
	}

	@Override
	public List<MarksheetDTO> getMeritList(UserContext userContext) {
		// TODO Auto-generated method stub
		return dao.getMeritList();
	}

}
