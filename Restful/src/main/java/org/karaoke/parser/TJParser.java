package org.karaoke.parser;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.karaoke.domain.Karaoke;

public class TJParser extends Parser {

	Logger log = Logger.getLogger(this.getClass());

	public List<Karaoke> parseSinger(String keyworld) throws IOException {
		String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=2&strText=" + keyworld
				+ "&strSize02=10";
		return buildCached(keyworld, "singer", url, (Element e, List<Karaoke> list) -> {
			makeKaraoke(e, list);
		});
	}

	public List<Karaoke> parseTitle(String keyworld) throws IOException {
		String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=1&strText=" + keyworld
				+ "&strCond=0&strSize01=10";
		return buildCached(keyworld, "song", url, (Element e, List<Karaoke> list) -> {
			makeKaraoke(e, list);
		});
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
