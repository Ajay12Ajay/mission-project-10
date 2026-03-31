// ==================== ForgetPasswordForm.java ====================
package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseForm;

/**
 * Form bean for the forgot-password request. Requires a non-empty
 * {@code loginId} to look up the user's account.
 *
 * @author Ajay Pratap Kerketta
 */
public class ForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "Login Id is required")
	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}