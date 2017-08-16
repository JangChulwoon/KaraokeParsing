package org.karaoke.crontab;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	Logger log = Logger.getLogger(this.getClass());
	
	@Scheduled(cron = "0 0 * * * *")
	public void cronTest1() {
		log.info("호출");
	}


}
