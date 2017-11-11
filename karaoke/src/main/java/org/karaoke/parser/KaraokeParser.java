package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class KaraokeParser {
    // 개발 시나리오
    // 1. TJ 파서만든다
    // 2. KY 파서 만든다
    // 3. 두개를 추상화한다 .
    private static final String singerUrl = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=7&sch_txt=";
    private static final String titleUrl = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=2&sch_txt=";


    public List<Karaoke> parseSingerKY(String singer) throws IOException {
        String requestUrl = singerUrl + URLEncoder.encode(singer, "euc-kr") + "&page=1";
        Document doc = Jsoup.connect(requestUrl).get();
        Elements elements = doc.select(".tbl_board tr:has(td)");
        List<Karaoke> list = new ArrayList<>();
        elements.forEach(element -> {
            Karaoke karaoke = new Karaoke()
                    .setNumber(element.child(0).text())
                    .setTitle(element.child(1).text())
                    .setSinger(element.child(2).text())
                    .setAdditionalInfo(element.child(3).text());
            list.add(karaoke);
        });
        return list;
    }

    public List<Karaoke> parseTitleKY(String title) throws IOException {
        String requestUrl = titleUrl + URLEncoder.encode(title, "euc-kr") + "&page=1";
        Document doc = Jsoup.connect(requestUrl).get();
        Elements elements = doc.select(".tbl_board tr:has(td)");
        List<Karaoke> list = new ArrayList<>();
        elements.forEach(element -> {
            Karaoke karaoke = new Karaoke()
                    .setNumber(element.child(0).text())
                    .setTitle(element.child(1).text())
                    .setSinger(element.child(2).text())
                    .setAdditionalInfo(element.child(3).text());
            list.add(karaoke);
        });
        return list;
    }
/*
    @Override
    public List<KaraokeBuild> parseTitle(String keyworld) throws IOException {
        String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=2&sch_txt="
                + URLEncoder.encode(keyworld, "euc-kr") + "&page=1";
        return buildCached(keyworld, "song", url);
    }

    @Override
    public List<KaraokeBuild> parseNumber(String keyworld) throws IOException {
        String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=1&sch_txt="
                + keyworld + "&page=1";
        return buildCached(keyworld, "number", url);
    }

    // KY 도 구현하고 나면 util로 만들 수 있지 않을까 ????
    private List<KaraokeBuild> buildCached(String keyworld, String cachedType, String url)
            throws UnsupportedEncodingException {
        log.info("URL :: " + url);
        keyworld = URLEncoder.encode(keyworld, "UTF-8");
        if (Cache.isHit(keyworld, cachedType)) {
            log.info("hit!!");
            return Cache.getCached(keyworld, cachedType);
        } else {
            log.info("key" + keyworld);
            Cache.insertCached(keyworld, cachedType,
                    parseHtmlToText(url, ".tbl_board tbody tr:has(td)", (Element e, List<KaraokeBuild> list) -> {
                        makeKaraoke(e, list);
                    }));
        }
        return Cache.getCached(keyworld, cachedType);
    }

    private void makeKaraoke(Element e, List<KaraokeBuild> list) {
        String tdLine[];
        KaraokeBuild karaoke = new KaraokeBuild();
        karaoke.setNumber(e.child(0).text());
        karaoke.setTitle(e.child(1).text());
        karaoke.setSinger(e.child(2).text());
        tdLine = e.child(3).text().split("작곡");
        karaoke.setLyricist(tdLine[1].replaceAll("작사", ""));
        karaoke.setComposer(tdLine[0]);
        list.add(karaoke);
    }*/
}
