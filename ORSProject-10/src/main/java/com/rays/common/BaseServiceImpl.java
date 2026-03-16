package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	protected D dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto, UserContext userContext) {
		long id = dao.add(dto, userContext);
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto, UserContext userContext) {

		T oldDto = dao.findByPk(dto.getId(), userContext);

		if (oldDto != null) {
			dto.setCreatedBy(oldDto.getCreatedBy());
			dto.setCreatedDatetime(oldDto.getCreatedDatetime());
		}

		dao.update(dto, userContext);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto, UserContext userContext) {
		Long id = dto.getId();

		if (id != null && id > 0) {
			update(dto, userContext);
		} else {
			id = add(dto, userContext);
		}
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T delete(long id, UserContext userContext) {
		T dto = findById(id, userContext);

		if (dto == null) {
			throw new RuntimeException("Record not found");
		}

		dao.delete(dto, userContext);
		return dto;
	}

	@Transactional(readOnly = true)
	public T findById(long id, UserContext userContext) {
		T dto = dao.findByPk(id, userContext);
		return dto;
	}

	@Transactional(readOnly = true)
	public T findByUniqueKey(String attribute, String val, UserContext userContext) {

		T dto = dao.findByUniqueKey(attribute, val, userContext);

		return dto;
	}

	@Transactional(readOnly = true)
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {

		return dao.search(dto, pageNo, pageSize, userContext);

	}

	@Transactional(readOnly = true)
	public List search(T dto, UserContext userContext) {

		return dao.search(dto, userContext);
	}

}
