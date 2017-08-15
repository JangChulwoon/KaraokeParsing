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

public class KYParser extends Parser{

	Logger log = Logger.getLogger(this.getClass());

	// 2가 제목 
	public List<Karaoke> parseSinger(String key) throws IOException {
		key = URLEncoder.encode(key, "euc-kr");
		String text = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=7&sch_txt="
				+ key
				+ "&page=1";
		return parseHtmlToText(text ,".tbl_board tbody tr:has(td)", callback);
	}

	@Override
	public List<Karaoke> parseTitle(String key) throws IOException {
		key = URLEncoder.encode(key, "euc-kr");
		String text = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=2&sch_txt="
				+ key
				+ "&page=1";
		return parseHtmlToText(text ,".tbl_board tbody tr:has(td)", callback);
	}
	

	private static ParserCallback callback = new ParserCallback() {
		private String tdLine[];
		public void HtmlToTextCallback(Element e, List<Karaoke> list) {
			// TODO Auto-generated method stub
			Karaoke karaoke = new Karaoke();
			karaoke.setNumber(e.child(0).text());
			karaoke.setTitle(e.child(1).text());
			karaoke.setSinger(e.child(2).text());
			tdLine = e.child(3).text().split("작곡");
			karaoke.setLyricist(tdLine[1].replaceAll("작사", ""));
			karaoke.setComposer(tdLine[0]);
			list.add(karaoke);
		}
	};
}
