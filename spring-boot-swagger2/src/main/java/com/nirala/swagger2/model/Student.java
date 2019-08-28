package com.nirala.swagger2.model;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class Student {
	@ApiModelProperty(notes = "Name of the Student",name="name",required=true,value="test name")
	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;
	@ApiModelProperty(notes = "Class of the Student",name="cls",required=true,value="test class")
	private String cls;
	@ApiModelProperty(notes = "Country of the Student",name="country",required=true,value="test country")
	private String country;

	public Student(String name, String cls, String country) {
		super();
		this.name = name;
		this.cls = cls;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public String getCls() {
		return cls;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", cls=" + cls + ", country=" + country + "]";
	}

}
