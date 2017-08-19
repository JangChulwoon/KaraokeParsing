package org.karaoke.parser;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.karaoke.domain.Karaoke;

public class KYParser extends Parser {

	Logger log = Logger.getLogger(this.getClass());
	
	

	@Override
	public List<Karaoke> parseSinger(String keyworld) throws IOException {
		String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=7&sch_txt="
				+ URLEncoder.encode(keyworld, "euc-kr") + "&page=1";
		return buildCached(keyworld, "singer", url,(Element e, List<Karaoke> list) -> {
			makeKaraoke(e, list);
		});
	}
	

	@Override
	public List<Karaoke> parseTitle(String keyworld) throws IOException {
		String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=2&sch_txt="
				+ URLEncoder.encode(keyworld, "euc-kr") + "&page=1";
		return buildCached(keyworld, "song", url,(Element e, List<Karaoke> list) -> {
			makeKaraoke(e, list);
		});
	}
	
	

	private void makeKaraoke(Element e, List<Karaoke> list) {
		String tdLine[];
		Karaoke karaoke = new Karaoke();
		karaoke.setNumber(e.child(0).text());
		karaoke.setTitle(e.child(1).text());
		karaoke.setSinger(e.child(2).text());
		tdLine = e.child(3).text().split("작곡");
		karaoke.setLyricist(tdLine[1].replaceAll("작사", ""));
		karaoke.setComposer(tdLine[0]);
		list.add(karaoke);
	}

}
