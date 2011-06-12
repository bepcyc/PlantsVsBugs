package org.anddev.andengine.pvb.plant;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.pvb.singleton.GameData;

public class PlantFlower2 extends Plant {
	
	public PlantFlower2() {
		super();
		getLastChild().attachChild(new Sprite(0, -119, GameData.getInstance().mPlantFlower2));
	}
	
}
