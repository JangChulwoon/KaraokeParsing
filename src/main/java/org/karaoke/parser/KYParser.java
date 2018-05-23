package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Category;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.karaoke.domain.Category.*;

@Component("KY")
@Slf4j
public class KYParser extends Parser {

    private static final String URL = "http://www.ikaraoke.kr/isong/search_musictitle.asp?";
    private static final String DOC_QUERY = ".tbl_board tr:has(td)";
    private static final Map<Category, String> URLQuery = new HashMap<>();

    @PostConstruct
    public void setUp() {
        URLQuery.put(SINGER, "sch_sel=7");
        URLQuery.put(SONG, "sch_sel=2");
        URLQuery.put(NUMBER, "sch_sel=1");
    }

    public String getUrl(Argument argument) throws IOException {
        return new StringBuilder(URL)
                .append(URLQuery.get(argument.getCategory()))
                .append("&sch_txt=")
                .append(URLEncoder.encode(argument.getWord(), "euc-kr"))
                .append("&page=").append(argument.getPage()).toString();

    }

    @Override
    public String getQuery() {
        return DOC_QUERY;
    }

}
