package org.anddev.andengine.pvb.plant;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.pvb.singleton.GameData;

public class PlantTomato extends Plant {
	
	public PlantTomato() {
		super();
		getLastChild().attachChild(new Sprite(0, -119, GameData.getInstance().mPlantTomato));
	}
	
}
