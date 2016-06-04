package org.baeldung.web.controller;

import java.util.Locale;

import org.baeldung.security.LoggedUsers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsersController {

	@RequestMapping(value = "/loggedUsers", method = RequestMethod.GET)
	public String getLoggedUsers(final Locale locale, final Model model) {
		model.addAttribute("users", LoggedUsers.getInstance().getUsers());
		System.out.println(LoggedUsers.getInstance().getUsers().size());
		for (String user:LoggedUsers.getInstance().getUsers()){
			System.out.println("logged in user:"+user);
		}
		return "redirect:/users.html";
	}

}
