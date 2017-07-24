package bgroup.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	USER("USER"),
	DBA("MONEY"),
	LOGIN("LOGIN"),
	SERVICELIST("SERVICELIST"),
	ADMIN("ADMIN");


	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
