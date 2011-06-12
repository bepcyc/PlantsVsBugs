package org.anddev.andengine.pvb.plant;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.pvb.singleton.GameData;

public abstract class Plant extends Entity {
	
	public Plant() {
		Sprite shadow = new Sprite(2, 55, GameData.getInstance().mPlantShadow);
		shadow.setAlpha(0.4f);
		attachChild(shadow);
	}
	
}
