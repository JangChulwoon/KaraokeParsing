package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

import static org.karaoke.domain.Category.singer;
import static org.karaoke.domain.Category.song;

@Component
@Slf4j
public class KaraokeParser {
    // 개발 시나리오
    // 1. TJ 파서만든다
    // 2. KY 파서 만든다
    // 3. 두개를 추상화한다 .
    private static final String url = "http://www.ikaraoke.kr/isong/search_musictitle.asp?";
    private static final String singerUrl = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=7&sch_txt=";
    private static final String titleUrl = "http://www.ikaraoke.kr/isong/search_musictitle.asp?sch_sel=2&sch_txt=";

    private Map<Category, String> argument;

    @PostConstruct
    public void setUp() {
        argument = new HashMap<>();
        argument.put(singer, "sch_sel=7");
        argument.put(song, "sch_sel=2");

    }

    public List<Karaoke> parseKY(Category category, String word, int page) throws IOException {
        StringBuilder str = new StringBuilder(url);
        str.append(argument.get(category)).append("&sch_txt=").append(URLEncoder.encode(word, "euc-kr")).append("&page=").append(page);
        log.info("url : {}", str.toString());
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
}
