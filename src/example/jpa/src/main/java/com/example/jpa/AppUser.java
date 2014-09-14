package com.example.jpa;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class AppUser {

	private String firstName;
	private String lastName;
	private String gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String idType;
	private String idNo;
	private String email;
	private String status;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDisabled() {
		return UserStatus.DISABLED.toString().equals(status);
	}

	public void setDisabled(boolean disabled) {
		if (disabled) {
			status = UserStatus.DISABLED.toString();
		} else {
			status = UserStatus.ENABLED.toString();
		}
	}

}
