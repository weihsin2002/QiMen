package org.dao.qimen.model;

public class Deities {
		
	public static enum YangDeities {
		
	    CHIEF (1, "Chief", "ZhiFu"),
	    SNAKE (2, "Snake", "TengShe"),
	    MOON (3, "Moon", "TaiYin"),
	    HARMONY (4, "Harmony", "LiuHe"),
	    HOOK (5, "Hook", "GouChen"),
	    PHOENIX (6, "Phoenix", "ZhuQue"),
	    EARTH (7, "Earth", "JiuDi"),
	    HEAVEN (8, "Heaven", "JiuTian");
	    
		private int order;
		private String deity;
		private String baShen;
		
		YangDeities (int order, String deity, String baShen) {
			this.order = order;
			this.deity = deity;
			this.baShen= baShen;
		}
	
		public int order() {
			return order;
		}

		public String deity() {
			return deity;
		}

		public String baShen() {
			return baShen;
		}
	}
	
	public static enum YinDeities {
		
	    CHIEF (1, "Chief", "ZhiFu"),
	    SNAKE (2, "Snake", "TengShe"),
	    MOON (3, "Moon", "TaiYin"),
	    HARMONY (4, "Harmony", "LiuHe"),
	    TIGER (5, "Tiger", "BaiHu"),
	    TORTOISE (6, "Tortoise", "XunWu"),
	    EARTH (7, "Earth", "JiuDi"),
	    HEAVEN (8, "Heaven", "JiuTian");
	    
		private int order;
		private String deity;
		private String baShen;
		
		YinDeities (int order, String deity, String baShen) {
			this.order = order;
			this.deity = deity;
			this.baShen= baShen;
		}
	
		public int order() {
			return order;
		}

		public String deity() {
			return deity;
		}

		public String baShen() {
			return baShen;
		}
	}	
}
