package org.karaoke.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.karaoke.domain.Karaoke;
import org.karaoke.parser.Parser;
import org.springframework.stereotype.Service;

@Service
public class KaraokeServiceImpl implements KaraokeService {
	Logger log = Logger.getLogger(this.getClass());
	
	public List<Karaoke> makeKaraokeNumber(String company, String type, String title) {
		// invalidate
		if (company == null || type == null || title == null 
				|| "".equals(company) || "".equals(type) || "".equals(title)) {
			return null;
		}
		List<Karaoke> list = null;
		Parser ms = Parser.initCompany(company);
		try {
			list = ms.checkType(type, title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.fatal("url connect error !");
		}
		return list;
	}

}
