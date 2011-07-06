package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.entity.sprite.AnimatedSprite;

public class BugLadybug extends Bug {
	
	public BugLadybug(final float y) {
		super(y, GameData.getInstance().mBugLadybug);
		
		AnimatedSprite leg = new AnimatedSprite(23, 57, GameData.getInstance().mBugLeg);
		getFirstChild().getFirstChild().attachChild(leg);
		leg.animate(400);
		
		this.mLife = 13;
		this.mSpeed = 15f;
		this.mPoint = 4;
		this.mAttack = 1.3f;
	}
	
}
