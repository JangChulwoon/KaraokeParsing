package org.karaoke.parser;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.karaoke.domain.Karaoke;

public abstract class Parser {
	private Parser parser = null;

	public Parser() {

	}

	public static Parser initCompany(String company) {
		// 회사 추출
		if ("TJ".equals(company)) {
			return new TJParser();
		} else if ("KJ".equals(company)) {
			return new KJParser();
		}
		return null;
	}

	public List<Karaoke> checkType(String category, String name) throws IOException {
		if ("song".equals(category)) {
			return this.parseTitle(name);
		} else if ("singer".equals(category)) {
			return this.parseSinger(name);
		} else {
			return null;
		}
	}

	public abstract List<Karaoke> parseSinger(String key) throws IOException;

	public abstract List<Karaoke> parseTitle(String key) throws IOException;
}
