package org.karaoke.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.karaoke.domain.Karaoke;

public final class CacheTJ {
	private static final Map<String, List<Karaoke>> cachedSong = new HashMap<String, List<Karaoke>>();

	public static void insertCachedSong(String keyworld, List<Karaoke> list) {
		cachedSong.put(keyworld, list);
	}

	public static List<Karaoke> getCachedsong(String keyworld) {
		return cachedSong.get(keyworld);
	}

	public static boolean isHit(String keyworld) {
		return cachedSong.containsKey(keyworld);
	}

}
