package org.karaoke.parser;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class SpeedTest {

	@Resource(name = "commonParaser")
	Parser parser;
	
	@Test
	public void shouldSpeedTJ() throws IOException {
		// given
		String company = "TJ", type = "song";
		Parser returnParser = parser.initCompany(company);
		// one request spend 1 second ..
		// when
		for(int i =0; i<10; ++i) {
			returnParser.runParser(type, "29");
		}
	}
	
	
/*	@Test
	public void shouldSpeedKY() throws IOException {
		// given
		String company = "KY", type = "song";
		Parser returnParser = parser.initCompany(company);
		// one request spend 1 second ..
		// when
		for(int i =0; i<10; ++i) {
			returnParser.runParser(type, i+"");
		}
	}*/
}
