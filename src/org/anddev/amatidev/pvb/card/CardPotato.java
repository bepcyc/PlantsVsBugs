package org.anddev.amatidev.pvb.card;

import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantPotato;
import org.anddev.amatidev.pvb.singleton.GameData;

public class CardPotato extends Card {

	public CardPotato() {
		super(GameData.getInstance().mCardPotato);
		
		this.mRecharge = 21f;
		this.mPrice = 2;
	}
	
	public Plant getPlant() {
		return new PlantPotato();
	}
	
}
