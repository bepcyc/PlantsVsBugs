package org.anddev.amatidev.pvb.bug;

import org.amatidev.util.AdEnviroment;
import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.sprite.Sprite;

public class Rip extends Entity {
	
	public Rip(final float x, final float y) {
		attachChild(new Sprite(0, 0, GameData.getInstance().mBugRip));
		setPosition(x, y);
	}
	
	public void onAttached() {
		registerUpdateHandler(new TimerHandler(3f, false, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				AdEnviroment.getInstance().safeDetachEntity(Rip.this);
			}
		}));
	}
	
}
