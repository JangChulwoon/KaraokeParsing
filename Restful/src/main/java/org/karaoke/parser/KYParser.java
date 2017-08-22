package org.karaoke.parser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.karaoke.cache.Cache;
import org.karaoke.cache.CacheKY;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

@Component("KY")
public class KYParser extends Parser {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "KYCache")
	Cache Cache;
	
	@Override
	public List<Karaoke> parseSinger(String keyworld) throws IOException {
		String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=7&sch_txt="
				+ URLEncoder.encode(keyworld, "euc-kr") + "&page=1";
		return buildCached(keyworld, "singer", url);
	}

	@Override
	public List<Karaoke> parseTitle(String keyworld) throws IOException {
		String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=2&sch_txt="
				+ URLEncoder.encode(keyworld, "euc-kr") + "&page=1";
		return buildCached(keyworld, "song", url);
	}

	// KY 도 구현하고 나면 util로 만들 수 있지 않을까 ????
	private List<Karaoke> buildCached(String keyworld, String cachedType, String url)
			throws UnsupportedEncodingException {
		log.info("URL :: " + url);
		keyworld = URLEncoder.encode(keyworld, "UTF-8");
		if (Cache.isHit(keyworld, cachedType)) {
			log.info("hit!!");
			return Cache.getCached(keyworld, cachedType);
		} else {
			log.info("key" + keyworld);
			Cache.insertCached(keyworld, cachedType,
					parseHtmlToText(url, ".tbl_board tbody tr:has(td)", (Element e, List<Karaoke> list) -> {
						makeKaraoke(e, list);
					}));
		}
		return Cache.getCached(keyworld, cachedType);
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
