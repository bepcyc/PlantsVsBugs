package org.anddev.amatidev.pvb.bug;

import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;

public class BugBeetle extends Bug {
	
	public BugBeetle(final float y) {
		super(y, GameData.getInstance().mBugBeetle);
		
		AnimatedSprite leg = new AnimatedSprite(23, 57, GameData.getInstance().mBugLeg);
		getFirstChild().getFirstChild().attachChild(leg);
		leg.animate(400);
		
		this.mLife = 9;
		this.mSpeed = 12f;
		this.mPoint = 2;
		this.mAttack = 1.5f;
	}
	
	protected void colorDamage() {
		getFirstChild().getFirstChild().getChild(0).setColor(3f, 3f, 3f);
		getBody().setColor(3f, 3f, 3f);
		registerUpdateHandler(new TimerHandler(0.1f, false, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				BugBeetle.this.getFirstChild().getFirstChild().getChild(0).setColor(1f, 1f, 1f);
				BugBeetle.this.getBody().setColor(1f, 1f, 1f);
			}
		}));
	}
	
}
