package org.dao.qimen.model;

import java.util.GregorianCalendar;

import org.boc.db.qm.QiMen2;
import org.dao.qimen.config.Configurator;

public class Chart {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	private int ng;  //输入的时间、当前时间、八字的年干
	private int nz;  //输入的时间、当前时间、八字的年支
	private int yg;  //输入的时间、当前时间、八字的的月干
	private int yz;
	private int rg;
	private int rz;
	private int sg;
	private int sz;
	private boolean isYin;  //是否阴历
	private boolean isYun;  //是否闰月
	
	private boolean iszf;		//转飞盘，转为true
	private boolean istd; 	//小值符随天还是地，天盘为true
	private boolean iscy;   //拆补还是置闰，拆补为true
	private int jigong;	
	private int ysTgong;			//用神落宫，或用神天盘落宫
	private int ysDgong;			//用神地盘落宫
	private int ysGan;				//用神天干
	private int ysZi;					//用神地支
	private int mtpgong;			//年命天盘落宫
	private int mdpgong; 			//年命地盘落宫
	private int mingdz,mingtg;  //年命天干、地支
	
	public Chart () {
		setTime();
		setQiMen();
	  	setYongShen();
	}
	
	private void setTime () {
	    GregorianCalendar clr = new GregorianCalendar();

		this.year = clr.get(java.util.Calendar.YEAR);
		this.month = clr.get(java.util.Calendar.MONTH)+1;
		this.day = clr.get(java.util.Calendar.DAY_OF_MONTH);
		this.hour = clr.get(java.util.Calendar.HOUR_OF_DAY);
		this.minute = clr.get(java.util.Calendar.MINUTE);
		this.second = clr.get(java.util.Calendar.SECOND);		
	}

	private void setQiMen () {
	  	this.iszf = Configurator.zf();  //转盘为1为true
	  	this.istd = Configurator.td(); //小值符使，默认随天盘1为true
	  	this.iscy = Configurator.rb(); //设拆捕
	  	this.jigong = Configurator.kg() ? 2 : 8; // 寄宫数, 缺省值为2，寄坤二宫为2，寄艮八宫为8
  
  	
	}
	
	private void setYongShen() {
		String[] ysinfo = getYShenInfo(yshen, 0, 0);		
		ysTgong = Integer.valueOf(ysinfo[1]);  //用神天盘落宫
  	ysGan = Integer.valueOf(ysinfo[2]);
  	ysZi = Integer.valueOf(ysinfo[3]);
  	ysDgong = getDiGong(ysGan, ysZi);
	}

}
