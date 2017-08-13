package com.restful.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.resful.parser.Parser;

public class ParserTestCase {
	static Parser ms;
	List<Map<String,String>> list;

	@Test
	public void test() throws IOException {
		ms = Parser.initCompany("TJ");
		list = ms.checkType("singer", "10cm");
		Assert.assertNotNull(list);
	}

}
