package org.karaoke.parser;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.karaoke.domain.Karaoke;

public class KJParser extends Parser{

	Logger log = Logger.getLogger(this.getClass());

	// 2가 제목 
	public List<Karaoke> parseSinger(String key) throws IOException {
		List<Karaoke> list = new ArrayList<Karaoke>();
		key = URLEncoder.encode(key, "euc-kr");
		String text = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=7&sch_txt="
				+ key
				+ "&page=1";
		parseHtmlToText(list, text);
		return list;
	}

	@Override
	public List<Karaoke> parseTitle(String key) throws IOException {
		List<Karaoke> list = new ArrayList<Karaoke>();
		key = URLEncoder.encode(key, "euc-kr");
		String text = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=2&sch_txt="
				+ key
				+ "&page=1";
		log.info(text);
		parseHtmlToText(list, text);
		return list;
	}
	
	private void parseHtmlToText(List<Karaoke> list, String text) throws IOException {
		Document doc = Jsoup.connect(text).get();
		Elements tds = doc.select(".tbl_board tbody tr:has(td)");
		// 비용이 조금 들려나 ?
		String[] tdLine = null;
		try {
			for (Element e : tds) {
				Karaoke karaoke = new Karaoke();
				karaoke.setNumber(e.child(0).text());
				karaoke.setTitle(e.child(1).text());
				karaoke.setSinger(e.child(2).text());
				tdLine = e.child(3).html().split("<br />");
				karaoke.setLyricist(tdLine[1]);
				karaoke.setComposer(tdLine[0]);
				list.add(karaoke);
			}
		} catch (IndexOutOfBoundsException exception) {
			list = null;
			return;
		}
	}
}
