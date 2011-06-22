package org.anddev.amatidev.pvb.card;

import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantTomato;
import org.anddev.amatidev.pvb.singleton.GameData;

public class CardTomato extends Card {

	public CardTomato(final float pX, final float pY) {
		super(pX, pY, GameData.getInstance().mCardTomato);
		
		this.mRecharge = 7f;
		this.mPrice = 4;
	}
	
	public Plant getPlant() {
		return new PlantTomato();
	}
	
}
