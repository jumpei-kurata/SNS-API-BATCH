package com.example.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class changePasswordAfterLoginForm {
	
	/**　論理ID */
	private String userLogicalId;

	/**　変更前パスワード	 */
	@Size(min = 8, max = 16, message = "パスワードは8文字以上16文字以内で入力してください")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]+$", message = "アルファベット（大文字小文字混在）と数字とを組み合わせて入力してください")
	private String beforePassword;

	/**　変更後パスワード	 */
	@Size(min = 8, max = 16, message = "パスワードは8文字以上16文字以内で入力してください")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]+$", message = "アルファベット（大文字小文字混在）と数字とを組み合わせて入力してください")
	private String afterPassword;

	public String getUserLogicalId() {
		return userLogicalId;
	}

	public void setUserLogicalId(String userLogicalId) {
		this.userLogicalId = userLogicalId;
	}

	public String getBeforePassword() {
		return beforePassword;
	}

	public void setBeforePassword(String beforePassword) {
		this.beforePassword = beforePassword;
	}

	public String getAfterPassword() {
		return afterPassword;
	}

	public void setAfterPassword(String afterPassword) {
		this.afterPassword = afterPassword;
	}

	@Override
	public String toString() {
		return "changePasswordAfterLoginForm [userLogicalId=" + userLogicalId + ", beforePassword=" + beforePassword
				+ ", afterPassword=" + afterPassword + "]";
	}

}
