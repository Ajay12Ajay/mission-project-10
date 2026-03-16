package com.rays.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;

public class BaseCtl<T extends BaseDTO, F extends BaseForm, S extends BaseServiceInt<T>> {

	@Autowired
	protected S service;

	@Value("${page.size}")
	private int pageSize = 0;

	protected UserContext userContext = null;

	@ModelAttribute
	public void setUserContext(HttpSession session) {
		userContext = (UserContext) session.getAttribute("useContext");

		if (userContext == null) {
			UserDTO dto = new UserDTO();
			dto.setLoginId("ajay@gmail.com");
			userContext = new UserContext(dto);
		}
	}

	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {
			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});

			res.addInputError(errors);
		}

		return res;
	}

	@PostMapping(value = "save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);

		if (!res.success) {
			return res;
		}

		T dto = (T) form.getDto();

		if (dto.getUniqueKey() != null && !dto.getUniqueKey().equals("")) {

			T existsDTO = service.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);

			if (existsDTO != null && (dto.getId() == null || !existsDTO.getId().equals(dto.getId()))) {
				res.addMessage(dto.getLabel() + " already exists");
				res.setSuccess(false);
				return res;
			}
		}

		Long exId = dto.getId();

		System.out.println("User Context:" + userContext);

		long id = service.save(dto, userContext);

		if (id > 0 && exId == null) {
			res.addMessage("data added successfully");
			res.addData(dto);
		} else if (id == dto.getId()) {
			res.addMessage("data updated successfully");
			res.addData(dto);
		} else {
			res.addMessage("issue in adding");
			res.setSuccess(false);
		}

		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse(true);

		T dto = service.findById(id, userContext);

		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record Not Found");
		}

		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable String[] ids, @RequestParam("pageNo") String pageNo, @RequestBody F form) {

		ORSResponse res = new ORSResponse();

		try {
			for (String id : ids) {
				service.delete(Long.parseLong(id), userContext);
			}

			T dto = (T) form.getDto();

			List<T> list = service.search(dto, Integer.parseInt(pageNo), pageSize, userContext);

			List<T> nextList = service.search(dto, Integer.parseInt(pageNo + 1), pageSize, userContext);

			if (list.size() == 0) {
				res.setSuccess(false);
				res.addMessage("Record Not Found...!");
			} else {
				res.setSuccess(true);
				res.addMessage("Records deleted Successfully");
				res.addData(list);
				res.addResult("nextList", nextList.size());
			}

		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}

		return res;

	}

	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse(true);

		pageNo = (pageNo < 0) ? 0 : pageNo;

		T dto = (T) form.getDto();

		List<T> list = service.search(dto, pageNo, pageSize, userContext);

		List<T> nextList = service.search(dto, pageNo + 1, pageSize, userContext);

		if (list.size() == 0) {
			res.setSuccess(false);
			res.addMessage("Record not found..!!");
		} else {
			res.setSuccess(true);
			res.addData(list);
			res.addResult("nextListSize", nextList.size());
		}

		return res;

	}

}
