package org.anddev.amatidev.pvb.card;

import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantBag;
import org.anddev.amatidev.pvb.singleton.GameData;

public class CardBag extends Card {

	public CardBag() {
		super(GameData.getInstance().mCardBag);
		
		this.mRecharge = 7f;
		this.mPrice = 2;
	}
	
	public Plant getPlant() {
		return new PlantBag();
	}
	
}
