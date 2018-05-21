package org.dao.qimen.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.qimen.model.Header;

public class HeaderTest {
	private static final Logger logger = Logger.getLogger(HeaderTest.class);

	public static void main(String[] args) {
		Header header = new Header(new Date());
		logger.info(header.toJson().toString());
	}
}
