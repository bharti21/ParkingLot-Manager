package com.practice.consumer;

public enum PersonTypeEnum {

	ELDERLY("elderly"),YOUNG("young");
	
	private PersonTypeEnum(String personType) {
		this.personType = personType;
	}

	private String personType;

	public String getPersonType() {
		return personType;
	}
	
}
