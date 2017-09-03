package org.karaoke.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.karaoke.domain.KaraokeBuild;
import org.springframework.stereotype.Component;

@Component("KYCache")
public class CacheKY implements Cache {

	// 동기화 문제가 없나 ? HashTable ? ConcurrentHashMap !
	private Map<String, List<KaraokeBuild>> cachedSong = new ConcurrentHashMap<String, List<KaraokeBuild>>();
	private Map<String, List<KaraokeBuild>> cachedSinger = new ConcurrentHashMap<String, List<KaraokeBuild>>();
	private Map<String, List<KaraokeBuild>> cachedNumber = new ConcurrentHashMap<String, List<KaraokeBuild>>();

	public void insertCached(String keyworld, String type, List<KaraokeBuild> list) {
		Cache.super.insertCached(keyworld, type, list);
		if ("singer".equals(type)) {
			cachedSinger.put(keyworld, list);
		}else if("number".equals(type)) {
			cachedNumber.put(keyworld,list);
		}else {
			cachedSong.put(keyworld, list);
		}
	}

	public List<KaraokeBuild> getCached(String keyworld, String type) {
		if ("singer".equals(type)) {
			return cachedSinger.get(keyworld);
		} else if ("number".equals(type)) {
			return cachedNumber.get(keyworld);
		} else {
			return cachedSong.get(keyworld);
		}
	}

	public boolean isHit(String keyworld, String type) {
		if ("singer".equals(type)) {
			return cachedSinger.containsKey(keyworld);
		}else if ("number".equals(type)) {
			return cachedNumber.containsKey(keyworld);
		}
		return cachedSong.containsKey(keyworld);
	}

	public void destroyCache() {
		cachedSong.clear();
		cachedSinger.clear();
		cachedNumber.clear();
	}

}
