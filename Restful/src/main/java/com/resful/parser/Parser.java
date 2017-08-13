package com.resful.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class Parser {
	private Parser parser = null;

	public Parser() {

	}

	public static Parser initCompany(String info) {
		// 회사 추출
		if (info.equals("TJ")) {
			return new TJParser();
		} else {
			return null;
		}
	}

	public List<Map<String, String>> checkType(String category, String name) throws IOException {

		if (category.equals("music")) {
			return this.parseTitle(name);
		} else if (category.equals("singer")) {
			return this.parseSinger(name);
		} else {
			return null;
		}
	}

	public abstract List<Map<String, String>> parseSinger(String key) throws IOException;

	public abstract List<Map<String, String>> parseTitle(String key) throws IOException;
}
