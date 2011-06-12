package org.anddev.andengine.pvb.card;

import org.anddev.andengine.pvb.Game;
import org.anddev.andengine.pvb.singleton.GameData;

public class Flower2 extends Card {

	public Flower2(Game game, float pX, float pY) {
		super(game, pX, pY, GameData.getInstance().mFlower2);
		
		this.mObjectTexure = GameData.getInstance().mObjectFlower2;
		this.mRecharge = 20f;
		
		startRecharge();
	}
	
}
