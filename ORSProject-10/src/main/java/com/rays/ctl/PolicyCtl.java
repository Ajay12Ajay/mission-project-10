package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.PolicyDTO;
import com.rays.form.PolicyForm;
import com.rays.service.PolicyServiceInt;

@RestController
@RequestMapping(value = "Policy")
public class PolicyCtl extends BaseCtl<PolicyDTO, PolicyForm, PolicyServiceInt> {

}