package org.karaoke.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.domain.KaraokeBuild;
import org.karaoke.service.KaraokeService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml" })
public class MusicControllerTest {

	private MockMvc mvc;

	MusicController controller;

	@Autowired
	WebApplicationContext context;

	@Mock
	KaraokeService service;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MusicController();
		controller.setKaraokeService(service);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void shouldServiceInjection() throws Exception {
		// given 
		String company = "TJ", type = "song", title = "title";
		String url = "/" + company + "/" + type + "/" + title;
		List<KaraokeBuild> list = new ArrayList<KaraokeBuild>();
		KaraokeBuild karaoke = new KaraokeBuild();
		karaoke.setNumber("number");
		list.add(karaoke);
		when(service.makeKaraokeNumber(company, type, title)).thenReturn(list);

		// than ? 
		mvc.perform(get(url)).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$",hasSize(1)))
				.andExpect(jsonPath("$[0].number").value("number"));
		verify(service,times(1)).makeKaraokeNumber(company, type, title);
	}
	
	
}
