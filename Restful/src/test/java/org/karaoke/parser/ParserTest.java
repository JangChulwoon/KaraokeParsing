package org.karaoke.parser;
import java.io.IOException;
import java.net.URLEncoder;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.cache.Cache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.instanceOf;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class ParserTest {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "commonParaser")
	Parser parser;

	@Resource(name = "TJCache")
	Cache CacheTJ;

	@Resource(name = "KYCache")
	Cache CacheKY;

	@Test
	public void shouldInitCompanyTJ() throws IOException {
		String company = "TJ";

		// when
		Parser returnParser = parser.initCompany(company);

		// then
		Assert.assertThat(returnParser, instanceOf(TJParser.class));

	}

	@Test
	public void shouldInitCompanyKY() throws IOException {
		String company = "KY";

		// when
		Parser returnParser = parser.initCompany(company);

		// then
		Assert.assertThat(returnParser, instanceOf(KYParser.class));

	}

	@Test
	public void shouldCachedTJSinger() throws IOException {
		// given
		String company = "TJ", type = "singer", title = "아이유";
		String cachedSinger = URLEncoder.encode(title, "UTF-8");
		String uncachedSinger = URLEncoder.encode("10cm", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.checkType(type, title);

		// then
		Assert.assertTrue(CacheTJ.isHit(cachedSinger, "singer"));
		Assert.assertFalse(CacheTJ.isHit(uncachedSinger, "singer"));

	}

	@Test
	public void shouldCachedTJSong() throws IOException {
		// given
		String company = "TJ", type = "song", title = "첫눈";
		String cachedSinger = URLEncoder.encode(title, "UTF-8");
		String uncachedSinger = URLEncoder.encode("공감", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.checkType(type, title);

		// then
		Assert.assertTrue(CacheTJ.isHit(cachedSinger, "song"));
		Assert.assertFalse(CacheTJ.isHit(uncachedSinger, "song"));
	}

	@Test
	public void shouldCachedKYSinger() throws IOException {
		// given
		String company = "KY", type = "singer", title = "아이유";
		String cachedSinger = URLEncoder.encode(title, "UTF-8");
		String uncachedSinger = URLEncoder.encode("10cm", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.checkType(type, title);

		// then
		Assert.assertTrue(CacheKY.isHit(cachedSinger, "singer"));
		Assert.assertFalse(CacheKY.isHit(uncachedSinger, "singer"));

	}

	@Test
	public void shouldCachedKYSong() throws IOException {
		// given
		String company = "KY", type = "song", title = "첫눈";
		String cachedSinger = URLEncoder.encode(title, "UTF-8");
		String uncachedSinger = URLEncoder.encode("공감", "UTF-8");
		Parser returnParser = parser.initCompany(company);

		// when
		returnParser.checkType(type, title);

		// then
		Assert.assertTrue(CacheKY.isHit(cachedSinger, "song"));
		Assert.assertFalse(CacheKY.isHit(uncachedSinger, "song"));

	}

}
