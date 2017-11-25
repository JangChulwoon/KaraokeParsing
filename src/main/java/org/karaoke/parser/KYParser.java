package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.karaoke.domain.Category.NUMBER;
import static org.karaoke.domain.Category.SINGER;
import static org.karaoke.domain.Category.SONG;

@Component("KY")
@Slf4j
public class KYParser extends   Parser{

    private static final String URL = "http://www.ikaraoke.kr/isong/search_musictitle.asp?";
    private Map<Category, String> URLQuery;

    @PostConstruct
    public void setUp() {
        URLQuery = new HashMap<>();
        URLQuery.put(SINGER, "sch_sel=7");
        URLQuery.put(SONG, "sch_sel=2");
        URLQuery.put(NUMBER, "sch_sel=1");
    }

    public List<Karaoke> parse(Argument argument, int page) throws IOException {
        StringBuilder str = new StringBuilder(URL);
        str.append(URLQuery.get(argument.getCategory()))
                .append("&sch_txt=")
                .append(URLEncoder.encode(argument.getWord(), "euc-kr"))
                .append("&page=").append(page);
        Elements elements = getDOMIntoJsoup(str,".tbl_board tr:has(td)");
        return buildKaraokes(elements);
    }


}
