package com.resful.parser;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TJParser extends Parser {

	public TJParser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Map<String, String>> parseSinger(String key) throws IOException {
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		key = URLEncoder.encode(key, "UTF-8");
		String text = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=2&strText=" + key
				+ "&strSize02=500";

		doc = Jsoup.connect(text).get();
		Elements tds = doc.select("#BoardType1 > table > tbody > tr > td");
		for (Element e : tds) {
			if (count / 5 == 1) {
				count = 0;
				list.add(map);
				map = new HashMap<String, String>();
			} else {
				switch (count) {
				case 0:
					map.put("number", e.text());
					break;
				case 1:
					map.put("title", e.text());
					break;
				case 2:
					map.put("singer", e.text());
					break;
				case 3:
					map.put("lyricist", e.text());
					break;
				case 4:
					map.put("composer", e.text());
					break;
				default:
					break;

				}
				++count;
			}
		}
		return list;
	}

	public List<Map<String, String>> parseTitle(String key) throws IOException {
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		// 인코딩을 안해두면 망함
		key = URLEncoder.encode(key, "UTF-8");
		String text = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=1&strText=" + key
				+ "&strCond=0&strSize01=500";
		doc = Jsoup.connect(text).get();
		Elements tds = doc.select("#BoardType1 > table > tbody > tr > td");
		for (Element e : tds) {
			if (count / 5 == 1) {
				count = 0;
				list.add(map);
				map = new HashMap<String, String>();
			} else {
				switch (count) {
				case 0:
					map.put("number", e.text());
					break;
				case 1:
					map.put("title", e.text());
					break;
				case 2:
					map.put("singer", e.text());
					break;
				case 3:
					map.put("lyricist", e.text());
					break;
				case 4:
					map.put("composer", e.text());
					break;
				default:
					break;

				}
				++count;
			}
		}
		return list;
	}
}
