package com.project.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.security.config.jwt.JwtUtils;
import com.project.security.payload.request.PasswordRequest;
import com.project.security.payload.response.MessageResponse;
import com.project.security.util.CryptoUtils;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	private CryptoUtils cryptoUtils;

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/all")
	public ResponseEntity<?> allAccess() {
		return ResponseEntity.ok().body(new MessageResponse("Public Content."));
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> userAccess() {
		return ResponseEntity.ok().body(new MessageResponse("User Content."));
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public ResponseEntity<?> moderatorAccess() {
		return ResponseEntity.ok().body(new MessageResponse("Moderator Board."));
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> adminAccess() {
		return ResponseEntity.ok().body(new MessageResponse("Admin Board."));
	}

	// TEST
	@PostMapping("/encode")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> encodePassword(@RequestBody PasswordRequest passwordRequest) {
		return ResponseEntity.ok().body(new MessageResponse(cryptoUtils.encode(passwordRequest.getRawPassword())));
	}

	@PostMapping("/decode")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> decodePassword(@RequestBody PasswordRequest passwordRequest) {
		return ResponseEntity.ok().body(new MessageResponse(cryptoUtils.decode(passwordRequest.getEncodedPassword())));
	}

	@PostMapping("/upgrade-encoding")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> upgradeEncoding(@RequestBody PasswordRequest passwordRequest) {
		return ResponseEntity.ok().body(new MessageResponse(cryptoUtils.upgradeEncoding(passwordRequest.getEncodedPassword()).toString()));
	}

	@PostMapping("/username")
	public ResponseEntity<?> username(@RequestBody String token) {
		return ResponseEntity.ok().body(new MessageResponse(jwtUtils.getUserNameFromJwtToken(token)));
	}
	// TEST

}