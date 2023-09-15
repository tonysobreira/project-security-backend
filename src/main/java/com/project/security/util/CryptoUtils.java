package com.project.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.security.config.password.CustomPasswordEncoder;

@Component
public class CryptoUtils {

	@Autowired
	private CustomPasswordEncoder passwordEncoder;

	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	public String decode(CharSequence encryptedPassword) {
		return passwordEncoder.decode(encryptedPassword);
	}

	public Boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	public Boolean upgradeEncoding(String encodedPassword) {
		return passwordEncoder.upgradeEncoding(encodedPassword);
	}

}
