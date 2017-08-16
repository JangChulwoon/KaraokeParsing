package org.karaoke.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.karaoke.common.CacheTJ;
import org.karaoke.domain.Karaoke;

public class TJParser extends Parser {

	Logger log = Logger.getLogger(this.getClass());

	public List<Karaoke> parseSinger(String keyworld) throws IOException {
		String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=2&strText=" + keyworld
				+ "&strSize02=10";
		return buildCached(keyworld,"singer",url);
	}
	
	public List<Karaoke> parseTitle(String keyworld) throws IOException {
		String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=1&strText=" + keyworld
				+ "&strCond=0&strSize01=10";
		return buildCached(keyworld, "song", url);
	}

	// KY 도 구현하고 나면 util로 만들 수 있지 않을까 ???? 
	private List<Karaoke> buildCached(String keyworld,String cachedType,String url) throws UnsupportedEncodingException {
		keyworld = URLEncoder.encode(keyworld, "UTF-8");
		if (CacheTJ.isHit(keyworld,cachedType)) {
			log.info("hit!!");
			return CacheTJ.getCached(keyworld,cachedType);
		} else {
			CacheTJ.insertCached(keyworld,cachedType,
					parseHtmlToText(url, "table.board_type1 tr:has(td)", (Element e, List<Karaoke> list) -> {
						makeKaraoke(e, list);
					}));
		}
		return CacheTJ.getCached(keyworld,cachedType);
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
