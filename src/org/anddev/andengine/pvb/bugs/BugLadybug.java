package org.anddev.andengine.pvb.bugs;

import org.anddev.andengine.pvb.singleton.GameData;

public class BugLadybug extends Bug {
	
	public BugLadybug(final float x, final float y) {
		super(x, y, GameData.getInstance().mPlantFlower2);
	}
	
}
