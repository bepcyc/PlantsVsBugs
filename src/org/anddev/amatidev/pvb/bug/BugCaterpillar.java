package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;

public class BugCaterpillar extends Bug {
	
	public BugCaterpillar(final float y) {
		super(y, GameData.getInstance().mBugCaterpillar);
		
		this.mLife = 20;
		this.mSpeed = 15f;
		this.mPoint = 8;
		this.mAttack = 2.5f;
	}
	
}
