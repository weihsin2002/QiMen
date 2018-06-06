package org.dao.qimen.model;

import org.dao.core.FiveElements;

public enum Stars {
    //蓬、芮、冲、辅、禽、心、柱、任、英，9星对应：1、2、3、4、5、6、7、8、9； 天禽心即禽芮
    //９星的排列次序：蓬、任、冲、辅、英、芮（禽）、柱、心，依次环排８宫；

//	public static final String[] jx1 = { "", "蓬", "芮", "冲", "辅", "禽", "心", "柱", "任", "英"};
//    public static final int[] jx2 = {0,1,8,3,4,9,2,7,6};
//    public static final int[] jx3 = {0, YiJing.SHUI,YiJing.TU,YiJing.MU,YiJing.MU,YiJing.TU,YiJing.JIN,YiJing.JIN,YiJing.TU,YiJing.HUO};
//    public static final int[] dpjx4 = {0,1,8,3,4,5,9,2,7,6}; //地盘对应九星顺序
//    public static final int[] jxjx = {0,-1,-1,0,1,1,1,-1,1,0}; //九星吉凶,1吉0平-1凶
    
    PENG (1, "Grass", "TianPeng", FiveElements.WATER),
    RUI(8, "Grain", "TianRui", FiveElements.EARTH),
    CHONG (3, "Destructor", "TianChong", FiveElements.WOOD),
    FU (4, "Assitant", "TianFu", FiveElements.WOOD),
    QIN (5, "", "TianQin", FiveElements.EARTH),
    XIN (9, "Heart", "TianXin", FiveElements.METAL),
    ZHU (2, "Pillar", "TianZhu", FiveElements.METAL),
    REN (7, "Ambassador", "TianRen", FiveElements.EARTH),
    YING (6, "Hero", "TianYing", FiveElements.FIRE);
    
	private int order;
	private String star;
	private String tianXing;
	private FiveElements fiveElements;
	
	Stars (int order, String star, String tianXing, FiveElements fiveElements) {
		this.order = order;
		this.star = star;
		this.tianXing= tianXing;
		this.fiveElements = fiveElements;
	}

	public int order() {
		return order;
	}

	public String star() {
		return star;
	}

	public String tianXing() {
		return tianXing;
	}
	
	public FiveElements fiveElements() {
		return fiveElements;
	}
}