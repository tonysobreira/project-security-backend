package com.project.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username") 
})
public class User {

	@Id
	@SequenceGenerator(name = "userIdGenerator", sequenceName = "SEQ_USERS", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
	private Long id;

	@NotBlank
	@Size(max = 255)
	private String username;

//	@NotBlank
	@Size(max = 255)
	@Email
	private String email;

	@NotBlank
	@Size(max = 255)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLES", 
		joinColumns = @JoinColumn(name = "USER_ID"), 
		inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}