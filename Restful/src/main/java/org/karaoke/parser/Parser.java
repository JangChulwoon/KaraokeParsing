package org.karaoke.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.karaoke.domain.Karaoke;

public abstract class Parser {

	public static Parser initCompany(String company) {
		if ("TJ".equals(company)) {
			return new TJParser();
		} else if ("KY".equals(company)) {
			return new KYParser();
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
	
	
	protected List<Karaoke> parseHtmlToText(String text,String selector ,ParserCallback callback) throws IOException {
		List<Karaoke> list = new ArrayList<Karaoke>();
		Document doc = Jsoup.connect(text).get();
		Elements tds = doc.select(selector);
		try {
			for (Element e : tds) {
				callback.HtmlToTextCallback(e, list);
			}
		} catch (IndexOutOfBoundsException exception) {
			return null;
		}
		return list;
	}

	public abstract List<Karaoke> parseSinger(String key) throws IOException;

	public abstract List<Karaoke> parseTitle(String key) throws IOException;
}
