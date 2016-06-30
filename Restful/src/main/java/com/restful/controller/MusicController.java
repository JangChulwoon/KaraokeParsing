package com.restful.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.resful.parser.Parser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MusicController {
	private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

	// pass라는 파라미터가 있으면 이거 실행
	
	@RequestMapping(value = "/get/{company}/{category}/{title}", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> TitleView(@PathVariable String company, @PathVariable String category, @PathVariable String title,
			Model model) throws IOException {
		List<Map<String, String>> list;
		Parser ms = Parser.Init(company);
		list = ms.Check(category, title);
	
		return list;
	}

	// title 라는 파라미터가 없을때 실행
	@RequestMapping(value = "/template/get/{company}/{category}", method = RequestMethod.GET)
	public String categoryView(@PathVariable String company, @PathVariable String category, Model model) {
		model.addAttribute("templateModel", "test");

		return "home";
	}

	// title 라는 파라미터가 없을때 실행
	@RequestMapping(value = "/template/get/{company}", method = RequestMethod.GET)
	public String CompanyView(@PathVariable String company, Model model) {
		model.addAttribute("templateModel", "test");

		return "home";
	}

}
