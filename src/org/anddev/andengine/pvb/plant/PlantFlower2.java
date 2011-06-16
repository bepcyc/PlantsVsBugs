package org.anddev.andengine.pvb.plant;

import org.anddev.andengine.pvb.singleton.GameData;

public class PlantFlower2 extends Plant {
	
	public PlantFlower2() {
		super(GameData.getInstance().mPlantFlower2);
		
		this.mShotDelay = 5f;
	}
	
}
