package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.PrescriptionDAOInt;
import com.rays.dto.PrescriptionDTO;

@Service
@Transactional
public class PrescriptionServiceImpl extends BaseServiceImpl<PrescriptionDTO, PrescriptionDAOInt>
		implements PrescriptionServiceInt {

}
