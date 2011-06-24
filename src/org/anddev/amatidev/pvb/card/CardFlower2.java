package org.anddev.amatidev.pvb.card;

import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantFlower2;
import org.anddev.amatidev.pvb.singleton.GameData;

public class CardFlower2 extends Card {

	public CardFlower2() {
		super(GameData.getInstance().mCardFlower2);
		
		this.mRecharge = 7f;
		this.mPrice = 2;
	}
	
	public Plant getPlant() {
		return new PlantFlower2();
	}
	
}
