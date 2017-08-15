package org.karaoke.parser;

import java.util.List;

import org.jsoup.nodes.Element;
import org.karaoke.domain.Karaoke;

public interface ParserCallback {
	void HtmlToTextCallback(Element e, List<Karaoke> list);
}
