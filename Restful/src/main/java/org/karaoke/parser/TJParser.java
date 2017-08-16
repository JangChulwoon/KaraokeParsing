package org.karaoke.parser;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.karaoke.common.CacheTJ;
import org.karaoke.domain.Karaoke;

public class TJParser extends Parser {

	Logger log = Logger.getLogger(this.getClass());

	public List<Karaoke> parseSinger(String keyworld) throws IOException {
		keyworld = URLEncoder.encode(keyworld, "UTF-8");
		if (CacheTJ.isHitSinger(keyworld)) {
			log.info("hit!!!");
			return CacheTJ.getCachedSinger(keyworld);
		} else {
			String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=2&strText=" + keyworld
					+ "&strSize02=10";
			log.info("check");
			CacheTJ.insertCachedSinger(keyworld,
					parseHtmlToText(url, "table.board_type1 tr:has(td)", (Element e, List<Karaoke> list) -> {
						makeKaraoke(e, list);
					}));
		}
		return CacheTJ.getCachedSinger(keyworld);
	}

	public List<Karaoke> parseTitle(String keyworld) throws IOException {
		keyworld = URLEncoder.encode(keyworld, "UTF-8");
		if (CacheTJ.isHitSong(keyworld)) {
			log.info("hit!!!");
			return CacheTJ.getCachedSong(keyworld);
		} else {
			String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=1&strText=" + keyworld
					+ "&strCond=0&strSize01=10";
			CacheTJ.insertCachedSong(keyworld,
					parseHtmlToText(url, "table.board_type1 tr:has(td)", (Element e, List<Karaoke> list) -> {
						makeKaraoke(e, list);
					}));
		}
		return CacheTJ.getCachedSong(keyworld);
	}

	private void makeKaraoke(Element e, List<Karaoke> list) {
		log.info("no cached");
		Karaoke karaoke = new Karaoke();
		karaoke.setNumber(e.child(0).text());
		karaoke.setTitle(e.child(1).text());
		karaoke.setSinger(e.child(2).text());
		karaoke.setLyricist(e.child(3).text());
		karaoke.setComposer(e.child(4).text());
		list.add(karaoke);
	}

}
