package com.rays.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.TransportDTO;
import com.rays.form.TransportForm;
import com.rays.service.TransportServiceInt;

@RestController
@RequestMapping(value = "Transport")
public class TransportCtl extends BaseCtl<TransportDTO, TransportForm, TransportServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {

		TransportDTO dto = new TransportDTO();

		ORSResponse res = new ORSResponse(true);

		List<DropdownList> transportList = service.search(dto, userContext);
		res.addResult("transportList", transportList);		
		

		return res;

	}

}