package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.AllowListDAOInt;
import com.rays.dto.AllowListDTO;

@Service
@Transactional
public class AllowListServiceImpl extends BaseServiceImpl<AllowListDTO, AllowListDAOInt>
		implements AllowListServiceInt {

	/*
	 * @Transactional(readOnly = false)
	 * 
	 * @Override public long add(AllowListDTO dto, UserContext userContext) { long
	 * pk = dao.add(dto, userContext); System.out.println("AllowList Record =====>"
	 * + dao.findByPk(pk, userContext)); if (true) { throw new RuntimeException(); }
	 * return pk; }
	 */

}