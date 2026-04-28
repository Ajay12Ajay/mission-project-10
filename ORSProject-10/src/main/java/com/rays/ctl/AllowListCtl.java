package com.rays.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.AllowListDTO;
import com.rays.form.AllowListForm;
import com.rays.service.AllowListServiceInt;

@RestController
@RequestMapping(value = "AllowList")
public class AllowListCtl extends BaseCtl<AllowListDTO, AllowListForm, AllowListServiceInt> {
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		
		AllowListDTO dto = new AllowListDTO();
		
		ORSResponse res=new ORSResponse(true);
		
		List<DropdownList> allowList=service.search(dto, userContext);
		
		res.addResult("allowList", allowList);
		
		return res;
		
		
	}

}