package org.karaoke.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

public  class CacheKY {
	private static Map<String, List<Karaoke>> cachedSong = new HashMap<String, List<Karaoke>>();
	private static Map<String, List<Karaoke>> cachedSinger = new HashMap<String, List<Karaoke>>();

	public static void insertCached(String keyworld, String type, List<Karaoke> list) {
		if ("singer".equals(type)) {
			cachedSinger.put(keyworld, list);
		} else {
			cachedSong.put(keyworld, list);
		}
	}

	public static  List<Karaoke> getCached(String keyworld, String type) {
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
