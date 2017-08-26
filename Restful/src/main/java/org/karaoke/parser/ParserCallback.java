package org.karaoke.parser;

import java.util.List;

import org.jsoup.nodes.Element;
import org.karaoke.domain.KaraokeBuild;

@FunctionalInterface
public interface ParserCallback {
	void HtmlToTextCallback(Element e, List<KaraokeBuild> list);
}
