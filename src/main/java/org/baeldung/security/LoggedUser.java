package org.baeldung.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.baeldung.web.dto.LoggedUserDto;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoggedUser implements HttpSessionBindingListener{
	
	String username;
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> users = LoggedUsers.getInstance().getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		 if (!users.contains(user.getUsername()))
		 {
			 users.add(user.getUsername());
		 }
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = LoggedUsers.getInstance().getUsers();
		LoggedUser user = (LoggedUser) event.getValue();
		 if (users.contains(user.getUsername()))
		 {
			 users.remove(user.getUsername());
		 }
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
