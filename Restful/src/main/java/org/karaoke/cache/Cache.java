package org.karaoke.cache;

import java.util.List;

import javax.annotation.Resource;

import org.karaoke.domain.KaraokeBuild;
import org.springframework.stereotype.Component;

public interface Cache {

	public void insertCached(String keyworld, String type, List<KaraokeBuild> list);
	
	default boolean isNotNullList(List<KaraokeBuild> list) {
		if(list == null || list.size() ==0) {
			return false;
		}
		return true;
	}

	public List<KaraokeBuild> getCached(String keyworld, String type);

	public boolean isHit(String keyworld, String type);
	
	public void destroyCache();
	
}
