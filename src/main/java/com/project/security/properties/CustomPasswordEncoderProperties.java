package com.project.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "password-encoder")
public class CustomPasswordEncoderProperties {

	private String IV;

	private String encryptionKey;

	public String getIV() {
		return IV;
	}

	public void setIV(String iV) {
		IV = iV;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

}
