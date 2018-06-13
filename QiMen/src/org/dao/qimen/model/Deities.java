package org.dao.qimen.model;

import org.dao.core.FiveElements;

public class Deities {
		
	public static enum YangDeities {
		
	    CHIEF (1, "Chief", "ZhiFu", FiveElements.EARTH),
	    SNAKE (2, "Snake", "TengShe", FiveElements.FIRE),
	    MOON (3, "Moon", "TaiYin", FiveElements.METAL),
	    HARMONY (4, "Harmony", "LiuHe", FiveElements.WOOD),
	    HOOK (5, "Hook", "GouChen", FiveElements.METAL),
	    PHOENIX (6, "Phoenix", "ZhuQue", FiveElements.WATER),
	    EARTH (7, "Earth", "JiuDi", FiveElements.EARTH),
	    HEAVEN (8, "Heaven", "JiuTian", FiveElements.METAL);
	    
		private int order;
		private String deity;
		private String baShen;
		private FiveElements fiveElements;
		
		YangDeities (int order, String deity, String baShen, FiveElements fiveElements) {
			this.order = order;
			this.deity = deity;
			this.baShen= baShen;
			this.fiveElements = fiveElements;
		}
	
		public int order() {
			return this.order;
		}

		public String deity() {
			return this.deity;
		}

		public String baShen() {
			return this.baShen;
		}
		
		public FiveElements fiveElements () {
			return this.fiveElements;
		}
	}
	
	public static enum YinDeities {
		
	    CHIEF (1, "Chief", "ZhiFu", FiveElements.EARTH),
	    SNAKE (2, "Snake", "TengShe", FiveElements.FIRE),
	    MOON (3, "Moon", "TaiYin", FiveElements.METAL),
	    HARMONY (4, "Harmony", "LiuHe", FiveElements.WOOD),
	    TIGER (5, "Tiger", "BaiHu", FiveElements.METAL),
	    TORTOISE (6, "Tortoise", "XunWu", FiveElements.WATER),
	    EARTH (7, "Earth", "JiuDi", FiveElements.EARTH),
	    HEAVEN (8, "Heaven", "JiuTian", FiveElements.METAL);
	    
		private int order;
		private String deity;
		private String baShen;
		private FiveElements fiveElements;
		
		YinDeities (int order, String deity, String baShen, FiveElements fiveElements) {
			this.order = order;
			this.deity = deity;
			this.baShen= baShen;
			this.fiveElements = fiveElements;
		}
	
		public int order() {
			return this.order;
		}

		public String deity() {
			return this.deity;
		}

		public String baShen() {
			return this.baShen;
		}
		
		public FiveElements fiveElements () {
			return this.fiveElements;
		}
	}
}
