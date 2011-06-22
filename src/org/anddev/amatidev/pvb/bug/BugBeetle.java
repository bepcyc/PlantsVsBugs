package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;

public class BugBeetle extends Bug {
	
	public BugBeetle(final float y) {
		super(y, GameData.getInstance().mBugBeetle);
		
		this.mLife = 10;
		this.mSpeed = 21f;
		this.mPoint = 10;
	}
	
}
