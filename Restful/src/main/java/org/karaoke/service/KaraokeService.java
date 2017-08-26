package org.karaoke.service;

import java.util.List;

import org.karaoke.domain.KaraokeBuild;
import org.karaoke.parser.Parser;

public interface KaraokeService {

	void setParser(Parser parser);
	List<KaraokeBuild> makeKaraokeNumber(String company,String type,String title);
}
