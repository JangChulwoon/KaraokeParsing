package org.karaoke.parser;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.karaoke.domain.Karaoke;

public class TJParser extends Parser {

	Logger log = Logger.getLogger(this.getClass());

	public List<Karaoke> parseSinger(String key) throws IOException {
		key = URLEncoder.encode(key, "UTF-8");
		String text = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=2&strText=" + key
				+ "&strSize02=500";
		return parseHtmlToText(text ,"table.board_type1 tr:has(td)", (Element e, List<Karaoke> list) -> { makeKaraoke(e,list);});
	}

	public List<Karaoke> parseTitle(String key) throws IOException {
		key = URLEncoder.encode(key, "UTF-8");
		String text = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=1&strText=" + key
				+ "&strCond=0&strSize01=500";
		return parseHtmlToText(text ,"table.board_type1 tr:has(td)", (Element e, List<Karaoke> list) -> { makeKaraoke(e,list); });
	}
	
	private void makeKaraoke(Element e, List<Karaoke> list) {
		Karaoke karaoke = new Karaoke();
		karaoke.setNumber(e.child(0).text());
		karaoke.setTitle(e.child(1).text());
		karaoke.setSinger(e.child(2).text());
		karaoke.setLyricist(e.child(3).text());
		karaoke.setComposer(e.child(4).text());
		list.add(karaoke);
	}


}
