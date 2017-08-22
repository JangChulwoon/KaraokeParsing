package org.karaoke.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

@Component("TJCache")
public class CacheTJ implements Cache {
	private Map<String, List<Karaoke>> cachedSong = new HashMap<String, List<Karaoke>>();
	private Map<String, List<Karaoke>> cachedSinger = new HashMap<String, List<Karaoke>>();

	public void insertCached(String keyworld, String type, List<Karaoke> list) {
		if ("singer".equals(type)) {
			cachedSinger.put(keyworld, list);
		} else {
			cachedSong.put(keyworld, list);
		}
	}

	public List<Karaoke> getCached(String keyworld, String type) {
		if ("singer".equals(type)) {
			return cachedSinger.get(keyworld);
		}
		return cachedSong.get(keyworld);
	}

	public boolean isHit(String keyworld, String type) {
		if ("singer".equals(type)) {
			return cachedSinger.containsKey(keyworld);
		}
		return cachedSong.containsKey(keyworld);
	}

}
