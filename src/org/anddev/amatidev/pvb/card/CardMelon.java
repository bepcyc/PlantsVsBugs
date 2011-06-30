package org.anddev.amatidev.pvb.card;

import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantMelon;
import org.anddev.amatidev.pvb.singleton.GameData;

public class CardMelon extends Card {

	public CardMelon() {
		super(GameData.getInstance().mCardPotato);
		
		this.mRecharge = 1; //14f;
		this.mPrice = 1;
	}
	
	public Plant getPlant() {
		return new PlantMelon();
	}
	
}
