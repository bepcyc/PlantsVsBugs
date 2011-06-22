package org.anddev.andengine.pvb.bug;

import org.anddev.andengine.pvb.singleton.GameData;

public class BugBeetle extends Bug {
	
	public BugBeetle(final float y) {
		super(y, GameData.getInstance().mBugBeetle);
		
		this.mLife = 10;
		this.mSpeed = 21f;
		this.mPoint = 10;
	}
	
}
