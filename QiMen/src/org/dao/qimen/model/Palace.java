package org.dao.qimen.model;

import org.dao.core.EarthlyBranches;
import org.dao.core.HeavenlyStems;
import org.dao.core.Palaces;

public class Palace {
	int order;
	
	Palaces palaces;
	HeavenlyStems heavenlySteams;
	EarthlyBranches earthlyBranches;
	
	Palace (int order) {
		this.order = order;
		this.palaces = Palaces.fromOrder(order);
	}
}
