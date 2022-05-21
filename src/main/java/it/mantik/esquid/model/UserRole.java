package it.mantik.esquid.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum UserRole {
	
	PLAYER("Player"), OWNER("Owner");
	
	private String name;

}
