package org.karaoke.parserTest;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.domain.Karaoke;
import org.karaoke.parser.KYParser;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class ParserTestCase {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	KaraokeService karaokeService;

	@Test
	public void ParseTJ() throws IOException {
		List<Karaoke> list = karaokeService.makeKaraokeNumber("TJ", "singer", "아이유");
		karaokeService.makeKaraokeNumber("TJ", "singer", "아이유");
		karaokeService.makeKaraokeNumber("TJ", "singer", "아이유");
		Assert.assertNotNull(list);
	}
	
	@Test
	public void ParseKJ() throws IOException {
		List<Karaoke> list = karaokeService.makeKaraokeNumber("KY", "song", "첫눈");
		Assert.assertNotNull(list);
	}

}
