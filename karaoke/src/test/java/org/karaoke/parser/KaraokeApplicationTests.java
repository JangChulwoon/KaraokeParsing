package org.karaoke.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KaraokeApplicationTests {

	@Autowired
	KaraokeParser parser;

	@Test
	public void contextLoads() throws IOException {
		parser.parseKY("IU");
	}

}
