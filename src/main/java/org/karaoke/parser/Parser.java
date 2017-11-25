package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class Parser {

    public abstract List<Karaoke> parse(Argument argument, int page) throws IOException;

    protected List<Karaoke> buildKaraokeList(Elements elements) {
        return elements.stream()
                .filter(e -> e.children().size() > 1)
                .map(e ->
                     new Karaoke()
                            .setNumber(e.child(0).text())
                            .setTitle(e.child(1).text())
                            .setSinger(e.child(2).text())
                ).collect(Collectors.toList());
    }

    protected Elements selectElemetsFromOtherService(StringBuilder str, String cssQuery) throws IOException {
        Document doc = Jsoup.connect(str.toString()).timeout(5000).get();
        return doc.select(cssQuery);
    }
}
