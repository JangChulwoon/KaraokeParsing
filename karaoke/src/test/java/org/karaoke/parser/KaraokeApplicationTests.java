package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KaraokeApplicationTests {

	@Autowired
	KaraokeService parser;

	@Test
	public void shouldParseSingerKY() throws IOException {
		// Test를 어떻게 할것인가 ?
		//parser.parseKY(Category.SONG,"IU",1);
	}

}
