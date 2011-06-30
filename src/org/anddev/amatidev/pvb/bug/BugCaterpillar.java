package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;

public class BugCaterpillar extends Bug {
	
	public BugCaterpillar(final float y) {
		super(y, GameData.getInstance().mBugCaterpillar);
		
		this.mLife = 20;
		this.mSpeed = 11f;
		this.mPoint = 6;
		this.mAttack = 2f;
	}
	
}
