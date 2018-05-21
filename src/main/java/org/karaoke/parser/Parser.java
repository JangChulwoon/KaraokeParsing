package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.karaoke.domain.Argument;
import org.karaoke.domain.Karaoke;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class Parser {

    private static final int NUMBER_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int SINGER_INDEX = 2;
    private static final int CONNECT_TIMEOUT = 5000;

    public abstract List<Karaoke> tryToExtract(Argument argument) throws IOException;

    protected List<Karaoke> extractKaraokes(Elements elements) {
        return elements.stream()
                .filter(e -> e.children().size() > 1) // if result is 0, not operate.
                .map(this::populateKaraoke)
                .collect(Collectors.toList());
    }

    private Karaoke populateKaraoke(Element e) {
        return new Karaoke()
                .setNumber(e.child(NUMBER_INDEX).text())
                .setTitle(e.child(TITLE_INDEX).text())
                .setSinger(e.child(SINGER_INDEX).text());
    }

    /**
     * TODO 2018.05.01
     * If resource is not present, It is occur the connect time out.
     * @param str
     * @param docQuery
     * @return
     * @throws IOException
     */
    protected Elements fetchDOM(String str, String docQuery) throws IOException {
       /* HeadMethod head = new HeadMethod(str);
        Header[] headers = head.getResponseHeaders();
        String contentLength = head.getResponseHeader("Content-Length").getValue();
        if (contentLength.equals("0")){
            return null;
        }*/

        // If resource is not present, It is occur the connect time out.
        // also Head method occur it...
        // I might find the other way to connet "KY" url...
        return Jsoup.connect(str).timeout(CONNECT_TIMEOUT).get().select(docQuery);
    }
}
