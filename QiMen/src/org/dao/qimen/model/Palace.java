package org.dao.qimen.model;

import org.dao.core.EarthlyBranches;
import org.dao.core.HeavenlyStems;
import org.dao.core.Palaces;

import com.google.gson.JsonObject;

public class Palace {
	int order;
	
	Palaces palaces;
	HeavenlyStems heavenlySteams;
	EarthlyBranches earthlyBranch1;
	EarthlyBranches earthlyBranch2;
	
	Palace (int order, int ju) {
		this.order = order;
		this.palaces = Palaces.fromOrder(order);
		this.earthlyBranch1 = this.palaces.earthlyBranch1();
		this.earthlyBranch2 = this.palaces.earthlyBranch2();
		
		this.heavenlySteams = getHeavenlyStem(ju);
	}
	
	public int order() {
		return order;
	}

	public Palaces palaces() {
		return palaces;
	}

	public HeavenlyStems heavenlySteams() {
		return heavenlySteams;
	}

	public EarthlyBranches earthlyBranch1() {
		return earthlyBranch1;
	}

	public EarthlyBranches earthlyBranch2() {
		return earthlyBranch2;
	}

	private HeavenlyStems getHeavenlyStem(int ju) {
		int juNumber = Math.abs(ju);
		int idx = 0;
		
		if(ju > 0) {
			idx = (10+this.order-juNumber)%9==0 ? 9 : (10+this.order-juNumber)%9;
		}else{
			idx = (10-this.order+juNumber)%9==0 ? 9 : (10-this.order+juNumber)%9;
		}
		HeavenlyStems stem;
		
		switch (idx) {
			case 1 : stem = HeavenlyStems.WU;
					 break;
			case 2 : stem = HeavenlyStems.JI;
					 break;
			case 3 : stem = HeavenlyStems.GENG;
					 break;
			case 4 : stem = HeavenlyStems.XIN;
					 break;
			case 5 : stem = HeavenlyStems.REN;
				     break;
			case 6 : stem = HeavenlyStems.GUI;
					 break;
			case 7 : stem = HeavenlyStems.DING;
					 break;
			case 8 : stem = HeavenlyStems.BING;
					 break;
			case 9 : stem = HeavenlyStems.YI;
					 break;
			default : stem = null;
		}
		
		return stem;
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		
		json.addProperty("order", this.order);
		json.addProperty("palaces", this.palaces.gong());
		json.addProperty("heavenlyStems", this.heavenlySteams.tianGan());
		json.addProperty("EarthlyBranch1", earthlyBranch1 == null ? null : earthlyBranch1.diZhi());
		json.addProperty("EarthlyBranch2", earthlyBranch2 == null ? null : earthlyBranch2.diZhi());
		
		return json;
	}
}