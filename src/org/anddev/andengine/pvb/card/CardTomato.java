package org.anddev.andengine.pvb.card;

import org.anddev.andengine.pvb.Game;
import org.anddev.andengine.pvb.plant.Plant;
import org.anddev.andengine.pvb.plant.PlantTomato;
import org.anddev.andengine.pvb.singleton.GameData;

public class CardTomato extends Card {

	public CardTomato(final Game game, final float pX, final float pY) {
		super(game, pX, pY, GameData.getInstance().mCardTomato);
		
		this.mRecharge = 10f;
		
		startRecharge();
	}
	
	public Plant getPlant() {
		return new PlantTomato();
	}
	
}
