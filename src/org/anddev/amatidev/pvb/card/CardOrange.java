package org.anddev.amatidev.pvb.card;

import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantOrange;
import org.anddev.amatidev.pvb.singleton.GameData;

public class CardOrange extends Card {

	public CardOrange() {
		super(GameData.getInstance().mCardOrange);
		
		this.mRecharge = 14f;
		this.mPrice = 8;
	}
	
	public Plant getPlant() {
		return new PlantOrange();
	}
	
}
