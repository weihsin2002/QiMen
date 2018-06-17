package org.dao.qimen.test;

import org.apache.log4j.Logger;
import org.dao.qimen.config.Configurator;
import org.dao.qimen.model.Chart;

public class ChartTest {
	private static final Logger logger = Logger.getLogger(ChartTest.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chart chart = new Chart();
		
		logger.info(chart.toString());
	}

}
