package it.mantik.esquid.model;

import java.util.Comparator;

public class UserSurnameComparator implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {
		return u1.getSurname().compareTo(u2.getSurname());
	}

}
