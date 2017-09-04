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



}
