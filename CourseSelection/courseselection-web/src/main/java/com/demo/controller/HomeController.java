package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/admin/login.html",method=RequestMethod.GET)
	public String  login(){
		return "login";
	}
	
	@RequestMapping(value="/admin/index.html",method=RequestMethod.GET)
	public ModelAndView   index(){
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("msg", "hello world");
 
		return model;
	}
	
	@RequestMapping(value="/admin/schools.html",method=RequestMethod.GET)
	public String  schools(){
		return "schools";
	}
	
	@RequestMapping(value="/admin/school_add.html",method=RequestMethod.GET)
	public String  schoolAdd(){
		return "school_add";
	}
	
}
