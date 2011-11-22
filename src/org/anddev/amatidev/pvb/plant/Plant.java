package org.anddev.amatidev.pvb.plant;

import org.amatidev.util.AdEnviroment;
import org.amatidev.util.AdVibration;
import org.anddev.amatidev.pvb.MainGame;
import org.anddev.amatidev.pvb.bug.Bug;
import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.SimplePreferences;
import org.anddev.andengine.util.modifier.IModifier;

import android.util.Log;

public abstract class Plant extends Entity {
	
	protected int mLife = 3;
	protected float mShotHeight = 28f;
	protected float mShotSpeed = 250f;
	protected float mShotDelay = 4f;
	protected boolean mShotDouble = false;
	
	public Plant(final TextureRegion pTexture) {
		Sprite shadow = new Sprite(2, 55, GameData.getInstance().mPlantShadow);
		shadow.setAlpha(0.4f);
		if (pTexture != null)
			shadow.attachChild(new Sprite(0, -68, pTexture));
		attachChild(shadow);
	}
	
	public void onAttached() {
		GameData.getInstance().mSoundPlanting.play();
		if (this.mShotDelay > 0) {
			registerUpdateHandler(new TimerHandler(this.mShotDelay, true, new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					Log.i("Game", "shot");
					if (Plant.this.canShot()) {
						Plant.this.shot(true);
						if (Plant.this.mShotDouble) {
							registerUpdateHandler(new TimerHandler(0.3f, false, new ITimerCallback() {
								@Override
								public void onTimePassed(TimerHandler pTimerHandler) {
									Log.i("Game", "2shot");
									Plant.this.shot(false);
								}
							}));
						}
					}
				}
			}));
		}
	}

	protected boolean canShot() {
		String y = Float.toString(getParent().getY());
		if (SimplePreferences.getAccessCount(AdEnviroment.getInstance().getContext(), "count" + y) > 0)
			return true;
		else
			return false;
	}

	protected void colorDamage() {
		getFirstChild().getFirstChild().setColor(3f, 3f, 3f);
		AdVibration.duration(100);
		registerUpdateHandler(new TimerHandler(0.1f, false, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Plant.this.getFirstChild().getFirstChild().setColor(1f, 1f, 1f);
			}
		}));
	}
	
	public void pushDamage(final Bug pBug) {
		colorDamage();
		
		this.mLife--;
		if (this.mLife <= 0) {
			clearUpdateHandlers();
			pBug.restart();
			getFirstChild().getFirstChild().registerEntityModifier(
					new LoopEntityModifier(
							new IEntityModifierListener() {
							@Override
								public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
									
								}
								
								@Override
								public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
									Plant.this.detachSelf();
								}
							}, 
							3, 
							null,
							new SequenceEntityModifier(
									new AlphaModifier(0.2f, 1f, 0.5f),
									new AlphaModifier(0.2f, 0.5f, 1f)
							)
					));
		}
	}
	
	public int getLife() {
		return this.mLife;
	}

	protected void shot(boolean pAnim) {
		// plant animation
		if (pAnim) {
			getFirstChild().registerEntityModifier(
					new SequenceEntityModifier(
							new ScaleModifier(0.3f, 1f, 1.1f),
							new ScaleModifier(0.3f, 1.1f, 1f)
					)
			);
		}
		
		// creazione shot
		float duration = (680 - getParent().getX() -  45) / this.mShotSpeed;
		Sprite shot = new Sprite(getParent().getX() + 45, getParent().getY() + this.mShotHeight, GameData.getInstance().mShot);
		Sprite shadow = new Sprite(0, 21, GameData.getInstance().mShotShadow);
		shadow.setAlpha(0.4f);
		shot.attachChild(shadow);
		
		Path path = new Path(2).to(getParent().getX() + 45, getParent().getY() + this.mShotHeight).to(680, getParent().getY() + this.mShotHeight);
		shot.registerEntityModifier(new PathModifier(duration, path, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				AdEnviroment.getInstance().safeDetachEntity(pItem);
			}

			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
				
			}
		}));
		
		int y = (int) getParent().getY() / 77;
		AdEnviroment.getInstance().getScene().getChild(MainGame.PRESHOT_GAME_LAYER + y).attachChild(shot);
	}
	
}
