package org.karaoke.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.controller.MusicController;
import org.karaoke.domain.Karaoke;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class KaraokeServiceTest {
	
	@Inject
	KaraokeService service;
	
	@Test
	public void shouldParseKaraoke() {
		// when
		List<Karaoke> karaoke = service.makeKaraokeNumber("KY", "song", "첫눈");
		
		// then
		Assert.assertNotNull(karaoke);
		
	}

}
