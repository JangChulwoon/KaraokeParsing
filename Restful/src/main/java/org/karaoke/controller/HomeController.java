package org.karaoke.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	     @RequestMapping(value="/", method=RequestMethod.GET)
	     public String view(Model model) {
	          logger.info("homecontroller In ");
	          return "home";
	     }
	
	
}
