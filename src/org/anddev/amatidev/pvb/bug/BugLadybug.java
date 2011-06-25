package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;

public class BugLadybug extends Bug {
	
	public BugLadybug(final float y) {
		super(y, GameData.getInstance().mBugLadybug);
		
		this.mLife = 13;
		this.mSpeed = 15f;
		this.mPoint = 20;
		this.mAttack = 1.3f;
	}
	
}
