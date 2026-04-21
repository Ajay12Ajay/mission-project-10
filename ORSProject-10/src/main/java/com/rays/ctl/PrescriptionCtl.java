package com.rays.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.PrescriptionDTO;
import com.rays.form.PrescriptionForm;
import com.rays.service.PrescriptionServiceInt;

@RestController
@RequestMapping(value = "Prescription")
public class PrescriptionCtl extends BaseCtl<PrescriptionDTO, PrescriptionForm, PrescriptionServiceInt> {

	@GetMapping("preload")
	public ORSResponse preload() {

		PrescriptionDTO dto = new PrescriptionDTO();

		ORSResponse res = new ORSResponse(true);

		List<DropdownList> prescriptionList =(List<DropdownList>) service.search(dto, userContext);

		res.addResult("prescriptionList", prescriptionList);

		return res;

	}

}
