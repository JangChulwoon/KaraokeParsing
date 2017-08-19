package org.karaoke.bean;

import org.karaoke.parser.KYParser;
import org.karaoke.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ParserBean {

	@Bean(name ="KYparser")
	public Parser getParser() {
		return new KYParser();
	}
}
