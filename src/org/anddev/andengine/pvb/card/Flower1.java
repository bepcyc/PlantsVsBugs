package org.anddev.andengine.pvb.card;

import org.anddev.andengine.pvb.Game;
import org.anddev.andengine.pvb.singleton.GameData;

public class Flower1 extends Card {

	public Flower1(Game game, float pX, float pY) {
		super(game, pX, pY, GameData.getInstance().mFlower1);
		
		this.mObjectTexure = GameData.getInstance().mObjectFlower1;
		this.mRecharge = 10f;
		
		startRecharge();
	}
	
}
