package org.karaoke.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.karaoke.domain.KaraokeBuild;
import org.springframework.stereotype.Component;

@Component("TJCache")
public class CacheTJ implements Cache {
	private final Logger logger = Logger.getLogger(this.getClass());
	private Map<String, List<KaraokeBuild>> cachedSong = new HashMap<String, List<KaraokeBuild>>();
	private Map<String, List<KaraokeBuild>> cachedSinger = new HashMap<String, List<KaraokeBuild>>();

	public void insertCached(String keyworld, String type, List<KaraokeBuild> list) {	
		if ("singer".equals(type)) {
			cachedSinger.put(keyworld, list);
		} else {
			cachedSong.put(keyworld, list);
		}
	}

	public List<KaraokeBuild> getCached(String keyworld, String type) {
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
	
	public void destroyCache() {
		cachedSong.clear();
		cachedSinger.clear();
	}

}
