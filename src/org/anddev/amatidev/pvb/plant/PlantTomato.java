package org.anddev.amatidev.pvb.plant;

import org.anddev.amatidev.pvb.singleton.GameData;

public class PlantTomato extends Plant {
	
	public PlantTomato() {
		super(GameData.getInstance().mPlantTomato);
		
		this.mLife = 3;
		this.mShotDelay = 1f;
	}
	
}
