package org.dao.qimen.utils;

import org.dao.calendar.config.Configurator;

public class Utils {
	
	public static int getJieQiOrder (String jieQi) {
		for (int i = 0 ; i< 12 ; i++) {
			if (jieQi.equalsIgnoreCase(Configurator.jieQi()[i])) {
			return i+1;
			}
		}
		return -1;
	}
	
	public static int getDivisionOrder (String division) {
		for (int i = 0 ; i< 3 ; i++) {
			if (division.equalsIgnoreCase(Configurator.qiDivision()[i])) {
			return i+1;
			}
		}
		return -1;
	}
}
