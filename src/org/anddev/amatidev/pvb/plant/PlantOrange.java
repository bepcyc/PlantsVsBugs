package org.anddev.amatidev.pvb.plant;

import org.anddev.amatidev.pvb.singleton.GameData;

public class PlantOrange extends Plant {
	
	public PlantOrange() {
		super(GameData.getInstance().mPlantOrange);
		
		this.mLife = 6;
		this.mShotDelay = 1.5f;
		this.mShotDouble = true;
	}
	
}
