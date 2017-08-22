package org.karaoke.parserTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.cache.Cache;
import org.karaoke.cache.CacheKY;
import org.karaoke.cache.CacheTJ;
import org.karaoke.domain.Karaoke;
import org.karaoke.parser.Parser;
import org.karaoke.service.KaraokeService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import static org.mockito.Mockito.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class ParserTestCase {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	KaraokeService karaokeService;

	@Resource(name = "TJCache")
	Cache CacheTJ;
	
	@Resource(name = "KYCache")
	Cache CacheKY;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
	}
	
	
	@Test
	public void ParseTJ() throws IOException {
		List<Karaoke> list = karaokeService.makeKaraokeNumber("TJ", "singer", "아이유");

		Assert.assertNotNull(list);
	}

	@Test
	public void ParseKJ() throws IOException {
		List<Karaoke> list = karaokeService.makeKaraokeNumber("KY", "song", "첫눈");
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

	@Test
	public void cachedKYSinger() throws UnsupportedEncodingException {
		String cachedSinger = URLEncoder.encode("아이유", "UTF-8");
		String uncachedSinger = URLEncoder.encode("10cm", "UTF-8");

		// should
		List<Karaoke> list = karaokeService.makeKaraokeNumber("KY", "singer", "아이유");

		// then
		Assert.assertTrue(CacheKY.isHit(cachedSinger, "singer"));
		Assert.assertFalse(CacheKY.isHit(uncachedSinger, "singer"));

	}

	@Test
	public void cachedKYSong() throws UnsupportedEncodingException {
		String cachedSong = URLEncoder.encode("첫눈", "UTF-8");
		String uncachedSong = URLEncoder.encode("안아줘", "UTF-8");

		// should
		karaokeService.makeKaraokeNumber("KY", "song", "첫눈");

		// then
		Assert.assertTrue(CacheKY.isHit(cachedSong, "song"));
		Assert.assertFalse(CacheKY.isHit(uncachedSong, "song"));
	}

}
