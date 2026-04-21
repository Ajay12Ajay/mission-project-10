package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.EMIDTO;
import com.rays.form.EMIForm;
import com.rays.service.EMIServiceInt;

@RestController
@RequestMapping(value = "EMI")
public class EMICtl extends BaseCtl<EMIDTO, EMIForm, EMIServiceInt> {

	@GetMapping("preload")
	public ORSResponse preload() {

		EMIDTO dto = new EMIDTO();

		ORSResponse res = new ORSResponse(true);

		List<DropdownList> emiList = service.search(dto, userContext);

		res.addResult("emiList", emiList);

		return res;

	}

}
