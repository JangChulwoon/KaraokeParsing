package com.resful.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class Parser {
	private Parser parser = null;
	
	public Parser(){
		
	}
	public  static Parser Init(String info) {
		// 회사 추출
		if (info.equals("TJ")) {
			return new TJParser();
		} else {
			return null;
		}
	}

	public  List<Map<String,String>> Check(String category, String name) throws IOException {
		
		if (category.equals("music")) {
			return this.TitleParser(name);
		} else if (category.equals("singer")) {
			return this.SingerParser(name);
		} else {
			return null;
		}
	}

	public abstract  List<Map<String,String>> SingerParser(String key) throws IOException;

	public abstract  List<Map<String,String>> TitleParser(String key) throws IOException;
}
