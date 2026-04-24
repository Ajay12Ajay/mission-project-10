package com.rays.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.DataExportLogDTO;
import com.rays.form.DataExportLogForm;
import com.rays.service.DataExportLogServiceInt;

@RestController
@RequestMapping(value = "DataExportLog")
public class DataExportLogCtl extends BaseCtl<DataExportLogDTO, DataExportLogForm, DataExportLogServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {

		ORSResponse res = new ORSResponse(true);

		DataExportLogDTO dto = new DataExportLogDTO();

		List<DropdownList> datalist = service.search(dto, userContext);

		res.addResult("datalist", datalist);

		return res;

	}

}