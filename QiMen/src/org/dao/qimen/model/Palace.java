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
		
		this.heavenlySteams = HeavenlyStems.heavenlyStems(getHeavenlyStemOrder(ju));
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

	private int getHeavenlyStemOrder(int ju) {
		ju = Math.abs(ju);
		if(ju > 0) {
			return (10+this.order-ju)%9==0 ? 9 : (10+this.order-ju)%9;
		}else{
			return (10-this.order+ju)%9==0 ? 9 : (10-this.order+ju)%9;
		}
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		
		json.addProperty("order", this.order);
		json.addProperty("palaces", this.palaces.gong());
		json.addProperty("heavenlyStems", this.heavenlySteams.tianGan());
		json.addProperty("EarthlyBranch1", earthlyBranch1.diZhi());
		json.addProperty("EarthlyBranch2", earthlyBranch2.diZhi());
		
		return json;
	}
}