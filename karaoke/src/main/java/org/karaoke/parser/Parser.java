package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class Parser {

   public abstract List<Karaoke> parse(Argument argument, int page) throws IOException;

   protected List<Karaoke> buildKaraokeList(Elements elements) {
        List<Karaoke> list = new ArrayList<>();
        try {
            elements.forEach(element -> {
                Karaoke karaoke = new Karaoke()
                        .setNumber(element.child(0).text())
                        .setTitle(element.child(1).text())
                        .setSinger(element.child(2).text());
                list.add(karaoke);
            });
        }catch (IndexOutOfBoundsException e){
            log.error("Cause : {} , Message : {}",e.getCause(),e.getMessage());
        }
        return list;
    }

    protected Elements selectElemetsFromOtherService(StringBuilder str,String cssQuery) throws IOException {
        Document doc = Jsoup.connect(str.toString()).timeout(5000).get();
        return doc.select(cssQuery);
    }
}
