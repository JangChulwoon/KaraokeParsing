package org.karaoke.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.karaoke.domain.Karaoke;


public final class CacheKY {
	private static final Map<String, List<Karaoke>> cachedSong = new HashMap<String, List<Karaoke>>();
	private static final Map<String, List<Karaoke>> cachedSinger = new HashMap<String, List<Karaoke>>();

	public static void insertCached(String keyworld, String type, List<Karaoke> list) {
		if ("singer".equals(type)) {
			cachedSinger.put(keyworld, list);
		} else {
			cachedSong.put(keyworld, list);
		}
	}

	public static List<Karaoke> getCached(String keyworld, String type) {
		if ("singer".equals(type)) {
			return cachedSinger.get(keyworld);
		}
		return cachedSong.get(keyworld);
	}

	public static boolean isHit(String keyworld, String type) {
		if ("singer".equals(type)) {
			return cachedSinger.containsKey(keyworld);
		}
		return cachedSong.containsKey(keyworld);
	}

}
