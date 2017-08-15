package org.karaoke.service;

import java.util.List;

import org.karaoke.domain.Karaoke;

public interface KaraokeService {

	List<Karaoke> makeKaraokeNumber(String company,String type,String title);
}
