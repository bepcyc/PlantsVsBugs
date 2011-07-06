package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;

public class BugSnail extends Bug {
	
	public BugSnail(final float y) {
		super(y, GameData.getInstance().mBugSnail);
		
		this.mLife = 20;
		this.mSpeed = 8f;
		this.mPoint = 12;
		this.mAttack = 1.5f;
	}
	
}
