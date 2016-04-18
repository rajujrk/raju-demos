package com.raj.springcrud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author rajkumarj
 *
 */
@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping(method=RequestMethod.GET)
	public String getHomePage(){
		return "BookStore";
	}
	
}
