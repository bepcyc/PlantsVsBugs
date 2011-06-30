package org.anddev.amatidev.pvb.card;

import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantMelon;
import org.anddev.amatidev.pvb.singleton.GameData;

public class CardMelon extends Card {

	public CardMelon() {
		super(GameData.getInstance().mCardMelon);
		
		this.mRecharge = 18f;
		this.mPrice = 10;
	}
	
	public Plant getPlant() {
		return new PlantMelon();
	}
	
}
