package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;

public class BugBeetle extends Bug {
	
	public BugBeetle(final float y) {
		super(y, GameData.getInstance().mBugBeetle);
		
		this.mLife = 10;
		this.mSpeed = 13f;
		this.mPoint = 10;
		this.mAttack = 1.5f;
	}
	
}
