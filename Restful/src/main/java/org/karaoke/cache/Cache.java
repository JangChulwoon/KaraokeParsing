package org.karaoke.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.karaoke.domain.KaraokeBuild;
import org.springframework.stereotype.Component;

public interface Cache {

	void insertCached(String keyworld, String type, List<KaraokeBuild> list);
	
	public List<KaraokeBuild> getCached(String keyworld, String type);

	public boolean isHit(String keyworld, String type);
	
	public void destroyCache();
	
}
