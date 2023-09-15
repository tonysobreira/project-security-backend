package com.project.security.config.password;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.security.properties.CustomPasswordEncoderProperties;

@Service
public class CustomPasswordEncoder implements PasswordEncoder {

	@Autowired
	private CustomPasswordEncoderProperties properties;

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			SecretKeySpec key = new SecretKeySpec(properties.getEncryptionKey().getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(properties.getIV().getBytes("UTF-8")));
			final byte[] encodedPasswordByte = cipher.doFinal(rawPassword.toString().getBytes("UTF-8"));
			String encodedPassword = Base64.getEncoder().encodeToString(encodedPasswordByte).trim();
			return encodedPassword;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (rawPassword != null && encode(rawPassword).equals(encodedPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean upgradeEncoding(String encodedPassword) {
		// TODO Auto-generated method stub
		return PasswordEncoder.super.upgradeEncoding(encodedPassword);
	}

	public String decode(CharSequence encryptedPassword) {
		try {
			SecretKeySpec key = new SecretKeySpec(properties.getEncryptionKey().getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(properties.getIV().getBytes("UTF-8")));
			final byte[] encryptedPasswordByte = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword.toString().getBytes("UTF-8")));
			String decryptedPassword = new String(encryptedPasswordByte, "UTF-8");
			return decryptedPassword;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}