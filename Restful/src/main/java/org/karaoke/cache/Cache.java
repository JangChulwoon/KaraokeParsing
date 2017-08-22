package org.karaoke.cache;

import java.util.List;

import javax.annotation.Resource;

import org.karaoke.domain.Karaoke;
import org.springframework.stereotype.Component;

public interface Cache {

	public void insertCached(String keyworld, String type, List<Karaoke> list);

	public List<Karaoke> getCached(String keyworld, String type);

	public boolean isHit(String keyworld, String type);
}
