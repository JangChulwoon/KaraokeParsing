package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.karaoke.domain.KaraokesWrapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class Parser {

    private static final int NUMBER_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int SINGER_INDEX = 2;
    private static final int CONNECT_TIMEOUT = 5000;

    public abstract KaraokesWrapper extract(Argument argument) throws IOException;

    protected KaraokesWrapper extractKaraokes(Elements elements) {
        List<Karaoke> list = elements.stream()
                .filter(e -> e.children().size() > 1) // if result is 0, not operate.
                .map(this::populateKaraoke)
                .collect(Collectors.toList());
        return new KaraokesWrapper(list, new DateTime());
    }

    private Karaoke populateKaraoke(Element e) {
        return new Karaoke()
                .setNumber(e.child(NUMBER_INDEX).text())
                .setTitle(e.child(TITLE_INDEX).text())
                .setSinger(e.child(SINGER_INDEX).text());
    }

    protected Elements fetchDOM(String str, String docQuery) {
        try {
            return Jsoup.connect(str).timeout(CONNECT_TIMEOUT).get().select(docQuery);
        } catch (IOException e) {
            log.error("Fetch Exception : {}", e);
            return null;
        }
    }
}
