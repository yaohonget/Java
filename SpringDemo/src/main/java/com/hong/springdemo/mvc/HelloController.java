package com.hong.springdemo.mvc;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		Logger logger = Logger.getLogger("testLog");
		 try {
			FileHandler fileHandler = new FileHandler("D:/testloghelloweb.log");
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} 
		 
		model.addAttribute("message", "Hello, Spring MVC Framework!");
		logger.log(Level.WARNING, "do view");
		return "HelloWeb1";
	}

}
