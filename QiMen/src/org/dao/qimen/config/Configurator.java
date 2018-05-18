package org.dao.qimen.config;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Configurator {
	//public static boolean ALL = false;  //全部显示隐藏
	public static boolean SJ = false;   //十二神将
	public static boolean JXGE = false;
	public static boolean SGKY = false;
	public static boolean JTXMS = false;
	public static boolean WSXQ = false;
	public static boolean XCHM = false;
	public static boolean HUO = false;	
	public static boolean MA = true;	
	//public static boolean HEAD = true;  //是否显示隐藏头部日期信息
	//public static boolean CALENDAR = true;  //是否显示时间面板
	
	public static boolean YANG = true;    //阴盘还是阳盘，默认是阳盘
	public static boolean ZF = true;    //转盘还是飞盘，默认是转
	public static boolean RB = false;		//置闰还是拆补，默认是拆补法
	public static boolean TD = true;		//小值符随天还是地，默认随天
	public static boolean KG = true;		//永寄坤宫还是艮，默认是坤宫
//	public static boolean TIP = true;		//关闭显示提示信息，默认关闭
	public static int XMHW = 0;  //星门换位，0是复位，100是72变
	
//	public static boolean TOOL = true;  //是否显示工具栏，默认显示
//	public static boolean INPUT = false;  //是否显示高级面板，默认显示
	public static boolean IO = true;  //显示内置还是自定义的提示信息、规则引擎、格局定制。默认为内置	



	private final Logger logger = Logger.getLogger(Configurator.class);

	public Configurator () {
		
		logger.info("Initializing QiMen Configuration");

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = factory.newDocumentBuilder();

			if(!(new File("properties/chart.ini").exists())) {
				logger.error("Configuration Not Found!");
				return;				
			}

			logger.info("load qimen configuration");

			Document doc = parser.parse(new File("properties/chart.ini"));	
			Node element = doc.getDocumentElement();			
			NodeList child = element.getChildNodes();  
			for (int i = 0; i < child.getLength(); i++) {			
				Node node2 = child.item(i);
				String text = node2.getTextContent();
				if(text.trim().equals("")) continue;
				String key = node2.getNodeName();
				int val = Integer.valueOf(text); 								
				System.out.println(key+"="+val);
				if(key.equals("ma")) this.MA = val==1;				
				else if(key.equals("zf")) this.ZF = val==1;
				else if(key.equals("jxge")) this.JXGE = val==1;
				else if(key.equals("rb")) this.RB = val==0;
				else if(key.equals("sgky")) this.SGKY = val==1;
				else if(key.equals("td")) this.TD = val==1;
				else if(key.equals("wsxq")) this.WSXQ = val==1;
				else if(key.equals("kg")) this.KG = val==1;
				else if(key.equals("xchm")) this.XCHM = val==1;
//				else if(key.equals("tip")) this.TIP = val==1;
				else if(key.equals("jtxms")) this.JTXMS = val==1;
				else if(key.equals("xmhw")) this.XMHW = val==1? 0 : 1;
				else if(key.equals("huo")) this.HUO = val==1;
//				else if(key.equals("tool")) this.TOOL = val==1;
				else if(key.equals("sj")) this.SJ = val==1;
//				else if(key.equals("input")) this.INPUT = val==1;
//				else if(key.equals("head")) this..HEAD = val==1;
				else if(key.equals("io")) this.IO = val==1;
//				else if(key.equals("left")) this.LEFT = val==1 ? QiMen2.LEFTMAX : QiMen2.LEFTMIN;
//				else if(key.equals("calendar")) this.CALENDAR = val==1;
				else if(key.equals("yang")) this.YANG = val==1;
			}
		} catch (Exception e) {
			logger.error("Error Loading Configuration", e);
		}
	}


}
