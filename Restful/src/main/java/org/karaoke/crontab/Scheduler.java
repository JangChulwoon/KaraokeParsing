package org.karaoke.crontab;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.karaoke.cache.Cache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name="TJCache")
	Cache TJCache;
	
	@Resource(name = "KYCache")
	Cache KYCache;
	
	
	@Scheduled(cron = "10 * * * * *")
	public void cronTest1() {
		logger.info("Cache Clear");
		TJCache.destroyCache();
		KYCache.destroyCache();
	}


}
