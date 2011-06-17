package org.anddev.andengine.pvb.card;

import org.anddev.andengine.pvb.plant.Plant;
import org.anddev.andengine.pvb.plant.PlantFlower2;
import org.anddev.andengine.pvb.singleton.GameData;

public class CardFlower2 extends Card {

	public CardFlower2(final float pX, final float pY) {
		super(pX, pY, GameData.getInstance().mCardFlower2);
		
		this.mRecharge = 20f;
		
		startRecharge();
	}
	
	public Plant getPlant() {
		return new PlantFlower2();
	}
	
}
