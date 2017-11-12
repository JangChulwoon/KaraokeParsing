package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.karaoke.domain.Category.SINGER;
import static org.karaoke.domain.Category.SONG;

@Component
@Slf4j
public class KaraokeParser {
    // 개발 시나리오
    // 1. TJ 파서만든다
    // 2. KY 파서 만든다
    // 3. 두개를 추상화한다 .
    private static final String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?";

    private Map<Category, String> URLQuery;
    private Map<Category, String> TJURLQuery;
    @PostConstruct
    public void setUp() {
        URLQuery = new HashMap<>();
        TJURLQuery = new HashMap<>();

        URLQuery.put(SINGER, "sch_sel=7");
        URLQuery.put(SONG, "sch_sel=2");

        TJURLQuery.put(SINGER,"strType=2");
        TJURLQuery.put(SONG,"strType=1");

    }
    // KY
    public List<Karaoke> parseKY(Argument argument, int page) throws IOException {
        StringBuilder str = new StringBuilder(url);
        str.append(URLQuery.get(argument.getCategory()))
                .append("&sch_txt=")
                .append(URLEncoder.encode(argument.getWord(), "euc-kr"))
                .append("&page=").append(page);
        Document doc = Jsoup.connect(str.toString()).get();
        Elements elements = doc.select(".tbl_board tr:has(td)");
        return buildKaraokeList(elements);
    }

    private List<Karaoke> buildKaraokeList(Elements elements) {
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

    private static final String TJURL = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?";
     // 예외처리 해야됨 -

    public List<Karaoke> parseTJ(Argument argument, int page) throws IOException {
        StringBuilder str = new StringBuilder(TJURL);
        str.append(TJURLQuery.get(argument.getCategory()))
                .append("&strText=")
                .append(argument.getWord())
                .append("&strSize02=10");
        log.info(str.toString());
        Document doc = Jsoup.connect(str.toString()).get();
        Elements elements = doc.select("table.board_type1 tr:has(td)");
        return TJbuildKaraokeList(elements);
    }


    private List<Karaoke> TJbuildKaraokeList(Elements elements) {
        List<Karaoke> list = new ArrayList<>();
        try{
            elements.forEach(element -> {
                Karaoke karaoke = new Karaoke();
                karaoke.setNumber(element.child(0).text());
                karaoke.setTitle(element.child(1).text());
                karaoke.setSinger(element.child(2).text());
                karaoke.setAdditionalInfo(element.child(3).text() + "  "+element.child(4).text());
                list.add(karaoke);
            });
        }catch (IndexOutOfBoundsException e){
            log.error("Error Cause {} , Massage {}",e.getCause(),e.getMessage());
        }
        return list;
    }
    /**
     *
     * public List<KaraokeBuild> parseSinger(String keyworld) throws IOException {
     String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=2&strText=" + keyworld
     + "&strSize02=10";
     log.info("TJ singer"+url);
     return buildCached(keyworld, "singer", url);
     }

     public List<KaraokeBuild> parseTitle(String keyworld) throws IOException {
     String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?strType=1&strText=" + keyworld
     + "&strCond=0&strSize01=10";
     log.info("TJ title   "+url);
     return buildCached(keyworld, "song", url);
     }

     public List<KaraokeBuild> parseNumber(String keyworld) throws IOException {
     String url = "https://www.tjmedia.co.kr/tjsong/song_search_list.asp?searchOrderItem=&searchOrderType=&strCond=1&strType=16&strWord=1&strText=" + keyworld;
     log.info("TJ number"+url);
     return buildCached(keyworld, "number", url);
     }

     // KY 도 구현하고 나면 util로 만들 수 있지 않을까 ????
     private List<KaraokeBuild> buildCached(String keyworld, String cachedType, String url)
     throws UnsupportedEncodingException {
     keyworld = URLEncoder.encode(keyworld, "UTF-8");
     if (Cache.isHit(keyworld, cachedType)) {
     log.info("hit!!");
     return Cache.getCached(keyworld, cachedType);
     } else {
     Cache.insertCached(keyworld, cachedType,
     parseHtmlToText(url, "table.board_type1 tr:has(td)", (Element e, List<KaraokeBuild> list) -> {
     makeKaraoke(e, list);
     }));
     }
     return Cache.getCached(keyworld, cachedType);
     }

     private void makeKaraoke(Element e, List<KaraokeBuild> list) {
     KaraokeBuild karaoke = new KaraokeBuild();
     karaoke.setNumber(e.child(0).text());
     karaoke.setTitle(e.child(1).text());
     karaoke.setSinger(e.child(2).text());
     karaoke.setLyricist(e.child(3).text());
     karaoke.setComposer(e.child(4).text());
     list.add(karaoke);
     }
     */
}
