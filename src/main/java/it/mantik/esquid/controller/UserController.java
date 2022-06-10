package it.mantik.esquid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.mantik.esquid.model.User;
import it.mantik.esquid.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin/user/{userId}/enable")
	public String enableUser(@PathVariable("userId") Long userId) {
		
		User user = userService.findById(userId);
		
		user.setEnabled(true);
		
		userService.save(user);
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/user/{userId}/disable")
	public String disableUser(@PathVariable("userId") Long userId) {
		
		User user = userService.findById(userId);
		
		user.setEnabled(false);
		
		userService.save(user);
		
		/* TODO: remove the associated user from events etc.;
		 * logout the associated user;
		 */
		
		return "redirect:/admin";
		
	}

}
