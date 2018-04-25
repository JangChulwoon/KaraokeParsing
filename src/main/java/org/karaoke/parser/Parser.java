package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.karaoke.domain.KaraokesWrapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class Parser {

    public abstract KaraokesWrapper parse(Argument argument) throws IOException;

    protected KaraokesWrapper buildKaraokes(Elements elements) {
        List<Karaoke> list =  elements.stream()
                .filter(e -> e.children().size() > 1)
                .map(e ->
                     new Karaoke()
                            .setNumber(e.child(0).text())
                            .setTitle(e.child(1).text())
                            .setSinger(e.child(2).text())
                ).collect(Collectors.toList());
        return new KaraokesWrapper(list,new DateTime());
    }

    protected Elements fetchDOM(String str, String cssQuery) {
        try {
            return Jsoup.connect(str).timeout(5000).get().select(cssQuery);
        } catch (IOException e) {
            log.error("Fetch Exception : {}",e);
            return null;
        }
    }
}
