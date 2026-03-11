package com.rays.common;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseCtl<T extends BaseDTO, F extends BaseForm, S extends BaseServiceInt<T>> {

	@Autowired
	protected S service;

}
