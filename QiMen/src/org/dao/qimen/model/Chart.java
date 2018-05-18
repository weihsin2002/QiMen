package org.dao.qimen.model;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.boc.db.qm.QiMen2;

public class Chart {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	private boolean isYun = true; 
	private boolean isYin = false; 

	yydunNum=0; 
	ysNum = 3; 
  isheng=-1; 
  ishi=-1; 
  ng=nz=yg=yz=rg=rz=sg=sz=0;
	mzText="";	

	public Chart () {
	    GregorianCalendar clr = new GregorianCalendar();

		this.year = clr.get(java.util.Calendar.YEAR);
		this.month = clr.get(java.util.Calendar.MONTH)+1;
		this.day = clr.get(java.util.Calendar.DAY_OF_MONTH);
		this.hour = clr.get(java.util.Calendar.HOUR_OF_DAY);
		this.minute = clr.get(java.util.Calendar.MINUTE);
		this.second = clr.get(java.util.Calendar.SECOND);
	}


	
}
