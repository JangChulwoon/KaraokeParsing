package org.karaoke.parserTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class BeanTest {
	
	@Autowired()
	Parser KYparser;
	
	@Test
	public void shouldBeanTest() {
		Assert.assertNotNull(KYparser);
	}

}
