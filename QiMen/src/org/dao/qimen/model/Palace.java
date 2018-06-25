package org.dao.qimen.model;

import org.dao.core.EarthlyBranches;
import org.dao.core.HeavenlyStems;
import org.dao.core.Palaces;

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
	
	private int getHeavenlyStemOrder(int ju) {
		ju = Math.abs(ju);
		if(ju > 0) {
			return (10+this.order-ju)%9==0 ? 9 : (10+this.order-ju)%9;
		}else{
			return (10-this.order+ju)%9==0 ? 9 : (10-this.order+ju)%9;
		}
	}	
}