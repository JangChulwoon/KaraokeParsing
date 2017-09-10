package org.karaoke.cache;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.parser.Parser;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class CacheTest {

	@Resource(name = "commonParaser")
	Parser parser;

	@Resource(name = "TJCache")
	Cache CacheTJ;

	@Resource(name = "KYCache")
	Cache CacheKY;

	@Mock
	CacheTJ mockCachedTJ;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCachedTJSinger() throws IOException {
		// given
		String company = "TJ", type = "singer", keyworld = "아이유";
		String cachedSinger = URLEncoder.encode(keyworld, "UTF-8");
		String uncachedSinger = URLEncoder.encode("10cm", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.runParser(type, keyworld);

		// then
		Assert.assertTrue(CacheTJ.isHit(cachedSinger, "singer"));
		Assert.assertFalse(CacheTJ.isHit(uncachedSinger, "singer"));

	}

	@Test
	public void shouldCachedTJSong() throws IOException {
		// given
		String company = "TJ", type = "song", keyworld = "첫눈";
		String cachedSinger = URLEncoder.encode(keyworld, "UTF-8");
		String uncachedSinger = URLEncoder.encode("공감", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.runParser(type, keyworld);

		// then
		Assert.assertTrue(CacheTJ.isHit(cachedSinger, "song"));
		Assert.assertFalse(CacheTJ.isHit(uncachedSinger, "song"));
	}

	@Test
	public void shouldCachedKYSinger() throws IOException {
		// given
		String company = "KY", type = "singer", keyworld = "아이유";
		String cachedSinger = URLEncoder.encode(keyworld, "UTF-8");
		String uncachedSinger = URLEncoder.encode("10cm", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.runParser(type, keyworld);

		// then
		Assert.assertTrue(CacheKY.isHit(cachedSinger, "singer"));
		Assert.assertFalse(CacheKY.isHit(uncachedSinger, "singer"));

	}

	@Test
	public void shouldCachedKYSong() throws IOException {
		// given
		String company = "KY", type = "song", keyworld = "첫눈";
		String cachedSinger = URLEncoder.encode(keyworld, "UTF-8");
		String uncachedSinger = URLEncoder.encode("공감", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.runParser(type, keyworld);

		// then
		Assert.assertTrue(CacheKY.isHit(cachedSinger, "song"));
		Assert.assertFalse(CacheKY.isHit(uncachedSinger, "song"));

	}

	// How write this Test Case ..?
	@Test
	public void shouldPutByNoSearchKeyworld() throws IOException {
		// given
		String company = "TJ", type = "song", keyworld = "ㅈ";
		String cachedSinger = URLEncoder.encode(keyworld, "UTF-8");
		Parser returnParser = parser.initCompany(company);
		returnParser.setCache(mockCachedTJ);

		// when
		returnParser.runParser(type, keyworld);

		// then
		

	}
}
