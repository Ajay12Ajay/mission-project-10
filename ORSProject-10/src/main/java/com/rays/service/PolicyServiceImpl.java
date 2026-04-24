package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.PolicyDAOInt;
import com.rays.dto.PolicyDTO;

@Service
@Transactional
public class PolicyServiceImpl extends BaseServiceImpl<PolicyDTO, PolicyDAOInt> implements PolicyServiceInt {

}