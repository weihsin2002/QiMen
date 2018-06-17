package org.dao.qimen.model;

import java.util.Date;

import org.apache.log4j.Logger;
import org.dao.calendar.model.Division;
import org.dao.calendar.model.JieQi;
import org.dao.qimen.config.Configurator;
import org.dao.qimen.test.HeaderTest;

public class Chart {
	private static final Logger logger = Logger.getLogger(Chart.class.getName());
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	private String jieqiname;
	private int whichJie;
	private String yuan;
	private int whichYuan;
	
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

	private int jie;
	
	private boolean YANG = true;    //阴盘还是阳盘，默认是阳盘
	
	private int whichJu;
	
	private String [] bs;
	
	private int zhifugong;
	
	private Header header;
	
	private int[][][] gInt = new int[5][10][16];
	
	public Chart () {
		Configurator c = new Configurator();
		
		header = new Header(new Date());
		setTime();
		setQiMen();
		setOrder();
		setZhifuGong();
		setBaShen();
		setGlobalInfo();
	}
	
	private void setTime () {
		logger.info("Set Time");
		this.year = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueSolarDate().year() : header.daoCalendar().solarDate().year();
		this.month = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueSolarDate().month() : header.daoCalendar().solarDate().month();
		this.day = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueSolarDate().day() : header.daoCalendar().solarDate().day();
		this.hour = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueSolarDate().hour() : header.daoCalendar().solarDate().hour();
		this.minute = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueSolarDate().min() : header.daoCalendar().solarDate().min();
		this.second = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueSolarDate().sec() : header.daoCalendar().solarDate().sec();
		
		this.jieqiname = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueQiTerm().currentTerm() : header.daoCalendar().qiTerm().currentTerm();
		this.yuan = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueQiDivision().division() : header.daoCalendar().qiDivision().division();
		
		this.ng = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueFourPillars().ng() : header.daoCalendar().fourPillars().ng();
		this.nz = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueFourPillars().nz() : header.daoCalendar().fourPillars().nz();
		this.yg = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueFourPillars().yg() : header.daoCalendar().fourPillars().yg();
		this.yz = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueFourPillars().yz() : header.daoCalendar().fourPillars().yz();
		this.sg = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueFourPillars().sg() : header.daoCalendar().fourPillars().sg();
		this.sz = header.daoCalendar().trueSolarDate() != null ? header.daoCalendar().trueFourPillars().sz() : header.daoCalendar().fourPillars().sz();
	}

	private void setQiMen () {
		logger.info("Set QiMen");
	  	this.iszf = Configurator.zf();  //转盘为1为true
	  	this.istd = Configurator.td(); //小值符使，默认随天盘1为true
	  	this.iscy = Configurator.rb(); //设拆捕
	  	this.jigong = Configurator.kg() ? 2 : 8; // 寄宫数, 缺省值为2，寄坤二宫为2，寄艮八宫为8
	}
	
	  //定阴或阳盘的局数
	  private void setOrder() {
		  logger.info("Set Order");
		  this.whichJie = JieQi.fromJieQi(this.jieqiname);
		  this.whichYuan = Division.fromYuan(this.yuan);
		  this.whichJu = Configurator.yydun()[this.whichJie][this.whichYuan];
	  }
	  
	  private void setBaShen() {
		  logger.info("Set BaShen");
		  this.bs =  whichJu>0? Configurator.bs1():Configurator.bs2();
	  }
	
	  private void setZhifuGong() {  //小值符随地盘不能在这改，一改天盘奇仪都变了，因为是从值符宫推出来的
		  logger.info("Set ZhifuGong");
	  	//为随天盘值符
	    if(sg == 1) {
	      int g2 = getZhiFuShi();
	      this.zhifugong = g2;
	    }

	    int g = 0;
	    if(this.whichJu>0) {
	      g = (this.whichJu-1+Configurator.sjly2()[this.sg]+90)%9 ;
	    }else{
	      g = ((0-this.whichJu)+1-Configurator.sjly2()[this.sg]+90)%9;
	    }

	    this.zhifugong = g==0?9:g;
	  }

	  /**
	   * 得到直符直使序数
	   * 阳遁：直符直使序数=所用局数+时辰所在旬序数 – 1
	   * 阴遁：直符直使序数=１＋所用局数－时辰所在旬序数
	   */
	  private int getZhiFuShi() {
	    int xs = getXunShu();
	    int zfs = 0;
	    if(this.whichJu>0) {
	      zfs = (whichJu+xs-1+90)%9;
	    }else{
	      zfs = ((0-whichJu)-xs+1+90)%9;
	    }
	    return zfs==0?9:zfs;
	  }
	  
	  /**
	   * 得到旬序数 为 旬数对应数=地支数 － 天干数 再对应一次
	   * int[] xunshu = {1, 0, 6, 0, 5, 0, 4, 0, 3, 0, 2};
	   * xunname = {"","甲子(戊)","甲戌(己)","甲申(庚)","甲午(辛)","甲辰(壬)","甲寅(癸)"};
	   * 如甲子=0,则xunshu[0]=xunname[1]为甲子戊；
	   *   甲戌=10，则为xunshu[10]=xunname[2]为甲戌(己)；
	   *   旬数+4即可得到所对应的天干顺序号了；
	   */
	  private int getXunShu() {
	    return org.dao.calendar.config.Configurator.xunshu()[(this.sz-this.sg+120)%12];
	  }
	  
	  private void setGlobalInfo() {
		  logger.info("Set GlobalInfo");
		    this.gInt[0][0] = new int[]{0, this.ng, this.nz, this.yg, this.yz, this.rg, this.rz, this.sg, this.sz, whichJie, whichYuan, whichJu, getZhiFuShi(), getZhifuGong(), getZhishiGong(),1};

		    for(int i=1; i<=9; i++) { //随天盘值符  
		    	this.gInt[1][1][i] = getGongShenOfZhuan(i);
		    }
		    
		    for(int i=1; i<=9; i++) { // Five Elements
		    	this.gInt[1][2][i] = Configurator.bs3()[this.gInt[1][1][i]];
		    }

		    /**
		     * 天盘
		     */
		    for(int i=1; i<=9; i++) {
		    	this.gInt[2][1][i] = getGongXingOfZhuan(i);
		    }
		    
		    for(int i=1; i<=9; i++) {
		    	this.gInt[2][2][i] = Configurator.jx3()[this.gInt[2][1][i]];
		    }
		    
		      for(int i=1; i<=9; i++) {
		    	  this.gInt[2][3][i] = getTianpanJiyiOfZhuan(i);
		      }
		      for(int i=1; i<=9; i++) {
		    	  this.gInt[2][5][i] = Configurator.sjly4()[this.gInt[2][3][i]];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[2][3][i] = Configurator.sjly5()[this.gInt[2][3][i]];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[2][4][i] = Configurator.tianganwh()[this.gInt[2][3][i]];
		      }
		      

		      /**
		       * 人盘
		       */
		      for(int i=1; i<=9; i++) {
		        this.gInt[3][1][i] = getGongMenOfZhuan(i);  
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[3][2][i] = Configurator.bm3()[this.gInt[3][1][i]];
		      }

		      /**
		       * 地盘
		       */
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][1][i] = Configurator.jgbg()[i];  //YiJing.JingGuaName[]
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][2][i] = Configurator.jgwh()[i];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][3][i] = Configurator.dpjx4()[i];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][4][i] = Configurator.dpbm4()[i];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][5][i] = getDipanJiyi(i);
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][7][i] = Configurator.sjly4()[this.gInt[4][5][i]];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][5][i] = Configurator.sjly5()[this.gInt[4][5][i]];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][6][i] = Configurator.tianganwh()[this.gInt[4][5][i]];
		      }
		      for(int i=1; i<=9; i++) {
		        this.gInt[4][8][i] = Configurator.jgdz()[i];
		      }
	  }
	  
	  /**
	   * 得到地盘奇仪序数
	   * 阳遁：1+何宫-阳遁几局 大于小于9或0要加减9或置9
	   * 阴遁：1-何宫+阴遁几局
	   * @param i 地盘何宫
	   */
	  public int getDipanJiyi(int i) {
	    int ju = Math.abs(whichJu);
	    if(whichJu > 0) {
	      return (10+i-ju)%9==0 ? 9 : (10+i-ju)%9;
	    }else{
	      return (10-i+ju)%9==0 ? 9 : (10-i+ju)%9;
	    }
	  }
	  
	  /**
	   * 得到指定宫的门序数
	   * 九星不分阴阳遁局，永远顺时针依次环排八宫
	   * 这里改中五宫寄宫没有任何影响，因为从来不会直接取第5宫对应的星
	   * simon 2011-12-23
	   */
	  public int getGongMenOfZhuan(int gong) {
	    int zfsxs = getZhiFuShi(); //值使为某门的序号
	    int zslg = getZhishiGong(); //值使落宫数
	    if(zslg == 5)  //落中5宫寄坤2宫
	      zslg = 2;
	    if(zfsxs == 5) //为5则为2，需寄坤二宫，否则，5中门后下一门上一门是?
	      zfsxs = 2;
	    int i=1;
	    int j=0;
	    int k=0;

	    for(; i<Configurator.bm2().length; i++) {
	      if(zfsxs == Configurator.bm2()[i])  break;  //zfsxs=2,8--> i=6,2
	    }
	    for(; j<Configurator.jgxh().length; j++) {
	      if(zslg==Configurator.jgxh()[j])  break;  //zslg=2,8--> j=5,1
	    }
	    for(; k<Configurator.jgxh().length; k++) {
	      if(gong==Configurator.jgxh()[k])  break;  //gong=5, k=0
	    }
	    if(gong==5)
	      return 0;
	    return Configurator.bm2()[(i + k - j + 8)%8==0?8:(i + k - j + 8)%8];  //返回什不会出现5，因为只在8门中搜索的
	  }
	  
	  /**
	   * 转盘法天盘奇仪公式：
	   * 阳遁：天盘奇仪对应数字＝天盘星对应数字－局数+1（如果结果小于1那么将结果加上9即为奇仪对应的数字）；
	   * 阴遁：天盘奇仪对应数字＝局数－天盘星对应数字+1（如果结果小于1那么将结果加上9即为奇仪对应的数字）；
	   */
	  public int getTianpanJiyiOfZhuan(int gong) {
	    if(gong==5)
	      return 0;
	    int x = getGongXingOfZhuan(gong);
	    int g = 0;

	    if(this.whichJu>0) {
	      g =  (x - whichJu + 1 + 90)%9;
	    }else{
	      g =  ((0 - whichJu) - x + 1 + 90)%9;
	    }

	    return g==0?9:g;
	  }
	  	  
	  /**
	   * 得到指定宫的星序数
	   * 九星不分阴阳遁局，永远顺时针依次环排八宫
	   * 这里改中五宫寄宫没有任何影响，因为从来不会直接取第5宫对应的星
	   */
	  public int getGongXingOfZhuan(int gong) {
	    int zfsxs = getZhiFuShi(); //值符为某星的序号
	    int zflg = getZhifuGong(); //值符落宫数
	    if(zflg == 5)  //落中5宫寄坤2宫
	      zflg = 2;  //2原值为2
	    if(zfsxs == 5) //为5则为2，需寄坤二宫，否则，5中宫后下一星上一星是?
	      zfsxs = 2;  //2原值为2
	    int i=1;
	    int j=0;
	    int k=0;

	    for(; i<Configurator.jx2().length; i++) {
	      if(zfsxs == Configurator.jx2()[i])  break;
	    }
	    for(; j<Configurator.jgxh().length; j++) {
	      if(zflg==Configurator.jgxh()[j])  break;
	    }
	    for(; k<Configurator.jgxh().length; k++) {
	      if(gong==Configurator.jgxh()[k])  break;
	    }
	    if(gong==5)
	      return 0;

	    int x = (i + k - j + 8)%8==0?8:(i + k - j + 8)%8;
	    //x = x == 5? 2:x;
	    return Configurator.jx2()[x];  ////返回什不会出现5，因为只在8星中搜索的
	  }
	  
	  public int getGongShenOfZhuan(int gong) {
		    int zflg = getZhifuGong(); //值符落宫数
		    
		    //(二)中五宫寄何宫，阳遁寄igJigong指定的宫，阴遁永寄2宫，这是第二处改动
		    if(zflg == 5) {
		    	//zflg = (this.whichJu>0)? (igJigong==0?2:igJigong) : 2;
		    	zflg = 2;
		    }
		    int j=0;
		    int k=0;

		    for(; j<Configurator.jgxh().length; j++) {
		      if(zflg==Configurator.jgxh()[j])  break;
		    }
		    for(; k<Configurator.jgxh().length; k++) {
		      if(gong==Configurator.jgxh()[k])  break;
		    }

		    if(gong==5)
		      return 0;
		    if(whichJu>0)
		      return (1 + k - j + 8)%8==0?8:(1 + k - j + 8)%8;
		    else
		      return (1 - k + j + 8)%8==0?8:(1 - k + j + 8)%8;
		  }
	  
	  public int getZhifuGong() {  //小值符随地盘不能在这改，一改天盘奇仪都变了，因为是从值符宫推出来的
	  	
	  	//为随天盘值符
	    if(sg == 1) {
	      int g2 = getZhiFuShi();
	      return g2;
	    }

	    int g = 0;
	    if(this.whichJu>0) {
	      g = (whichJu-1+Configurator.sjly2()[this.sg]+90)%9 ;
	    }else{
	      g = ((0-whichJu)+1-Configurator.sjly2()[this.sg]+90)%9;
	    }

//	    /**
//	     * (1)中五宫寄何宫，阳遁寄igJigong指定的宫，阴遁永寄2宫，这是第一处改动
//	     */
//	    g = g!=5 ? g : (this.getJu()>0)? (this.igJigong==0?2:igJigong) : 2;

	    return g==0?9:g;
	  }
	  
	  /**
	   * 求直使落宫
	   * 阳遁时的求法：时干甲１、乙２、丙３，直使落宫＝直使序数＋时干在十天干中序数－１
	   * 阴遁时的求法：时干甲１0、乙９、丙８，直使落宫＝直使序数＋时干在十天干中序数－１
	   * 以上不分阴阳遁，如果直使落中５宫皆寄坤２宫。
	   */
		public int getZhishiGong() {
	    int zfsxs = getZhiFuShi();
	    int g = 0;
	    if(this.whichJu>0) {
	      g =  (zfsxs + sg -1 +90)%9;
	    }else{
	      g =  (zfsxs + (11 - sg) -1 +90)%9;
	      
	    }

//	    /**
//	     * (2)中五宫寄何宫，阳遁寄igJigong指定的宫，阴遁永寄2宫，这是第二处改动
//	     */
//	    g = g!=5 ? g : (this.getJu()>0)? (this.igJigong==0?2:igJigong) : 2;

	    return g==0?9:g;
	  }
		
		public String toString() {
			String output = System.lineSeparator();
			for (int i=0; i<5; i++) {
				for (int j=0; j<10; j++) {
					for (int k=0; k<16; k++) {
						output = output + this.gInt[i][j][k] + " ";
					}
					output = output + System.lineSeparator();
				}
			}
			return output;
		}
}
