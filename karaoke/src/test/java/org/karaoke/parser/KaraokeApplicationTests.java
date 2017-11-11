package org.karaoke.parser;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaoke.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KaraokeApplicationTests {

	@Autowired
	KaraokeParser parser;

	@Test
	public void shouldParseSingerKY() throws IOException {
		parser.parseKY(Category.SONG,"IU",1);
	}

}
