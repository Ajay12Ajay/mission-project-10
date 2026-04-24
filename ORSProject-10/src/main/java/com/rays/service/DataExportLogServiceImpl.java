package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.DataExportLogDAOInt;
import com.rays.dto.DataExportLogDTO;

@Service
@Transactional
public class DataExportLogServiceImpl extends BaseServiceImpl<DataExportLogDTO, DataExportLogDAOInt>
		implements DataExportLogServiceInt {

}