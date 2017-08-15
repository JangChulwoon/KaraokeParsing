package org.karaoke.parserTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.domain.Karaoke;
import org.karaoke.parser.Parser;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
		List<Karaoke> list = karaokeService.makeKaraokeNumber("TJ", "song", "æ»æ∆¡‡");
		Assert.assertNotNull(list);
	}

}
