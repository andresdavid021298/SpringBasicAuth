package com.adac.projectBasicAuth.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NewUserRequest {

	@NotEmpty(message = "Field 'name' cannot be null or empty")
	private String name;
	private String address;
	@NotNull(message = "Field 'age' cannot be null")
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
