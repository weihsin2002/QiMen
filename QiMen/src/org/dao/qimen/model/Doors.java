package org.dao.qimen.model;

import org.dao.core.FiveElements;

public enum Doors {
		
    REST (1, "Rest", "Xiu", FiveElements.WATER),
    DEATH (8, "Death", "Si", FiveElements.EARTH),
    HARM (3, "Harm", "Shang", FiveElements.WOOD),
    DELUSION (4, "Delusion", "Du", FiveElements.WOOD),
    CENTER (5, "Center", "Zhong", FiveElements.EARTH),
    OPEN (9, "Open", "Kai", FiveElements.METAL),
    FEAR (2, "Fear", "Jing", FiveElements.METAL),
    LIFE (7, "Life", "Sheng", FiveElements.EARTH),
    SCENERY (6, "Scenery", "Jing", FiveElements.FIRE);
    
	private int order;
	private String door;
	private String baMen;
	private FiveElements fiveElements;
		
	Doors (int order, String door, String baMen, FiveElements fiveElements) {
		this.order = order;
		this.door = door;
		this.baMen= baMen;
		this.fiveElements = fiveElements;
	}
	
	public int order() {
		return this.order;
	}
			
	public String door() {
		return this.door;
	}
		public String baMen() {
			return this.baMen;
	}
	
	public FiveElements fiveElements () {
			return this.fiveElements;
	}
}
