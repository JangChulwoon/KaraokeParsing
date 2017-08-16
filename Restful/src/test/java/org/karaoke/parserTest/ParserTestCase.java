package org.karaoke.parserTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.common.CacheTJ;
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

		Assert.assertNotNull(list);
	}

	@Test
	public void ParseKJ() throws IOException {
		List<Karaoke> list = karaokeService.makeKaraokeNumber("KY", "song", "첫눈");
		log.info(list.toString());
		Assert.assertNotNull(list);
	}

	@Test
	public void cachedTJSinger() throws UnsupportedEncodingException {
		String cachedSinger = URLEncoder.encode("아이유", "UTF-8");
		String uncachedSinger = URLEncoder.encode("10cm", "UTF-8");

		// should
		karaokeService.makeKaraokeNumber("TJ", "singer", "아이유");

		// then
		Assert.assertTrue(CacheTJ.isHit(cachedSinger, "singer"));
		Assert.assertFalse(CacheTJ.isHit(uncachedSinger, "singer"));

	}

	@Test
	public void cachedTJSong() throws UnsupportedEncodingException {
		String cachedSong = URLEncoder.encode("첫눈", "UTF-8");
		String uncachedSong = URLEncoder.encode("안아줘", "UTF-8");

		// should
		karaokeService.makeKaraokeNumber("TJ", "song", "첫눈");

		// then
		Assert.assertTrue(CacheTJ.isHit(cachedSong, "song"));
		Assert.assertFalse(CacheTJ.isHit(uncachedSong, "song"));
	}

}
