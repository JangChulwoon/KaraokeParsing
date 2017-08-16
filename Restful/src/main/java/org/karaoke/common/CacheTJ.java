package org.karaoke.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.karaoke.domain.Karaoke;

public final class CacheTJ {
	private static final Map<String, List<Karaoke>> cachedSong = new HashMap<String, List<Karaoke>>();
	private static final Map<String, List<Karaoke>> cachedSinger = new HashMap<String, List<Karaoke>>();

	public static void insertCachedSong(String keyworld, List<Karaoke> list) {
		cachedSong.put(keyworld, list);
	}

	public static List<Karaoke> getCachedSong(String keyworld) {
		return cachedSong.get(keyworld);
	}

	public static void insertCachedSinger(String keyworld, List<Karaoke> list) {
		cachedSinger.put(keyworld, list);
	}

	public static List<Karaoke> getCachedSinger(String keyworld) {
		return cachedSinger.get(keyworld);
	}
	
	public static boolean isHitSong(String keyworld) {
		return cachedSong.containsKey(keyworld);
	}
	
	public static boolean isHitSinger(String keyworld) {
		return cachedSinger.containsKey(keyworld);
	}
	

}
