package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.EMIDAOInt;
import com.rays.dto.EMIDTO;

@Service
@Transactional
public class EMIServiceImpl extends BaseServiceImpl<EMIDTO, EMIDAOInt> implements EMIServiceInt {

}
