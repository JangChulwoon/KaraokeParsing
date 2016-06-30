package com.restful.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.resful.parser.Parser;

public class ParserTestCase {
	static Parser ms;
	 List<Map<String,String>> list;

	@Test
	public void test() throws IOException {
		ms = Parser.Init("TJ");
		list = ms.Check("singer", "10cm");
		
	
		
	}

}
