package com.restful.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.resful.parser.Parser;

/**
 * Handles requests for the application home page.
 */
@RestController()
public class MusicController {
	private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

	
	@GetMapping(value = "/{company}/{category}/{title}")
	public List<?> TitleView(@PathVariable String company, @PathVariable String category, @PathVariable String title,
			Model model) throws IOException {
		List<?> list = null;
		Parser ms = Parser.initCompany(company);
		list = ms.checkType(category, title);
		logger.info("?");
		return list;
	}


}
