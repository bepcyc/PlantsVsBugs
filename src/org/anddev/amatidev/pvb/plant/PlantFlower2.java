package org.anddev.amatidev.pvb.plant;

import org.anddev.amatidev.pvb.singleton.GameData;

public class PlantFlower2 extends Plant {
	
	public PlantFlower2() {
		super(GameData.getInstance().mPlantFlower2);
		
		this.mShotDelay = 0f;
	}
	
}
