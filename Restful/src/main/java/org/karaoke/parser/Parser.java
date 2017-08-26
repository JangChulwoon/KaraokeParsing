package org.karaoke.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.karaoke.domain.KaraokeBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("commonParaser")
public class Parser {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "TJ")
	Parser TJParser;
	@Resource(name = "KY")
	Parser KYParser;

	// 조금더 생각해 볼것.
	public Parser initCompany(String company) {
		if ("TJ".equals(company)) {
			return TJParser;
		} else if ("KY".equals(company)) {
			return KYParser;
		}
		return null;
	}

	public List<KaraokeBuild> checkType(String category, String name) throws IOException {
		// need cachedMap
		if ("song".equals(category)) {
			return this.parseTitle(name);
		} else if ("singer".equals(category)) {
			return this.parseSinger(name);
		} else {
			return null;
		}
	}

	protected List<KaraokeBuild> parseHtmlToText(String url, String selector, ParserCallback callback) {
		List<KaraokeBuild> list = new ArrayList<KaraokeBuild>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements tds = doc.select(selector);
			try {
				for (Element e : tds) {
					callback.HtmlToTextCallback(e, list);
				}
			} catch (IndexOutOfBoundsException exception) {
				return null;
			}
		} catch (IOException exception) {

			this.parseHtmlToText(url, selector, callback);
		}
		return list;
	}

	// abstract일 경우 bean으로 등록이 안되는 문제가 발생...
	public List<KaraokeBuild> parseSinger(String key) throws IOException {
		return null;
	}

	public List<KaraokeBuild> parseTitle(String key) throws IOException {
		return null;
	}
}
