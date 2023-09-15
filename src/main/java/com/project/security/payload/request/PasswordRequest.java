package com.project.security.payload.request;

public class PasswordRequest {

	private String rawPassword;

	private String encodedPassword;

	public PasswordRequest() {
	}

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

}
