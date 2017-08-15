package org.karaoke.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.karaoke.domain.Karaoke;

public class CacheTJ {
	
	private static final Map<String,List<Karaoke>> cachedSong = new HashMap<String, List<Karaoke>>();

	
	public static void insertCachedSong(String keyworld, List<Karaoke> list) {
		cachedSong.put(keyworld, list);
	}
	
	public static Map<String, List<Karaoke>> getCachedsong() {
		return cachedSong;
	}
	
	


}
