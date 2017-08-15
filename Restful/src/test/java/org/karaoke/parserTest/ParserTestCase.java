package org.karaoke.parserTest;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.domain.Karaoke;
import org.karaoke.parser.KJParser;
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
		List<Karaoke> list = karaokeService.makeKaraokeNumber("TJ", "song", "¾È¾ÆÁà");
		Assert.assertNotNull(list);
	}
	
	@Test
	public void ParseKJ() throws IOException {
		KJParser kj  = new KJParser();
		List<Karaoke> list = kj.parseTitle("Ã¹´«");
		log.info(list.toString());
	}

}
