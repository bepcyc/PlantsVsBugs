package org.anddev.amatidev.pvb.plant;

import org.anddev.amatidev.pvb.singleton.GameData;

public class PlantPotato extends Plant {
	
	public PlantPotato() {
		super(GameData.getInstance().mPlantPotato);
		
		this.mLife = 10;
		this.mShotDelay = 0f;
	}
	
}
