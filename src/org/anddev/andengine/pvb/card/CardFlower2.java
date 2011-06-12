package org.anddev.andengine.pvb.card;

import org.anddev.andengine.pvb.Game;
import org.anddev.andengine.pvb.plant.Plant;
import org.anddev.andengine.pvb.plant.PlantFlower2;
import org.anddev.andengine.pvb.singleton.GameData;

public class CardFlower2 extends Card {

	public CardFlower2(Game game, float pX, float pY) {
		super(game, pX, pY, GameData.getInstance().mCardFlower2);
		
		this.mRecharge = 20f;
		
		startRecharge();
	}
	
	public Plant getPlant() {
		return new PlantFlower2();
	}
	
}
