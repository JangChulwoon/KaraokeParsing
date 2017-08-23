package org.karaoke.service;

import java.util.List;

import org.karaoke.domain.Karaoke;
import org.karaoke.parser.Parser;

public interface KaraokeService {

	List<Karaoke> makeKaraokeNumber(String company,String type,String title);
}
