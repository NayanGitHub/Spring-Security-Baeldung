package org.baeldung.security;

import java.util.ArrayList;
import java.util.List;

import org.baeldung.web.dto.LoggedUserDto;

public class LoggedUsers {

	private static LoggedUsers instance = null;

	public static List<String> users;

	private LoggedUsers() {
		users = new ArrayList<String>();
	}

	public static LoggedUsers getInstance() {
		if (instance == null) {
			instance = new LoggedUsers();
		}
		return instance;
	}

	public static List<String> getUsers() {
		return users;
	}

	public static void setUsers(List<String> users) {
		LoggedUsers.users = users;
	}
}
