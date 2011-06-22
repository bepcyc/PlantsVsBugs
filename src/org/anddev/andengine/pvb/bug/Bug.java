package org.anddev.andengine.pvb.bug;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extra.Enviroment;
import org.anddev.andengine.extra.ExtraScene;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.pvb.Game;
import org.anddev.andengine.pvb.plant.Plant;
import org.anddev.andengine.pvb.singleton.GameData;
import org.anddev.andengine.util.SimplePreferences;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseSineInOut;

import android.util.Log;

public abstract class Bug extends Entity {
	
	protected int mLife = 3;
	protected int mPoint = 10;
	protected float mDuration = 33f;
	private Path mPath;
	private boolean mCollide = true;
	
	public Bug(final float y, final TextureRegion pTexture) {
		Sprite shadow = new Sprite(2, 55, GameData.getInstance().mPlantShadow);
		shadow.setAlpha(0.4f);
		shadow.attachChild(new Sprite(0, -68, pTexture));
		attachChild(shadow);
		
		setPosition(705, y);
		this.mPath = new Path(2).to(this.mX, this.mY).to(0, this.mY);
	}
	
	public void onDetached() {
		SimplePreferences.incrementAccessCount(Enviroment.getInstance().getContext(), "count" + Float.toString(this.mY), -1);
		GameData.getInstance().mScoring.addScore(this.mPoint);
	}
	
	public void onAttached() {
		SimplePreferences.incrementAccessCount(Enviroment.getInstance().getContext(), "count" + Float.toString(this.mY));
		
		start();
		
		registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				Enviroment.getInstance().getEngine().runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						Bug.this.checkAndRemove();
						Bug.this.checkAndRestart();
					}
				});
			}
			
			@Override
			public void reset() {
				
			}
		});
	}

	private void checkAndRestart() {
		for (int i = 0; i < 45; i++) {
			final IEntity field = Enviroment.getInstance().getScene().getChild(Game.GAME_LAYER).getChild(i);
			if (field.getChildCount() == 1 && field.getFirstChild() instanceof Plant) {
				final Sprite obj = (Sprite) field.getFirstChild().getFirstChild().getFirstChild();
				if (((Sprite) getFirstChild().getFirstChild()).collidesWith(obj) && this.mCollide) {
					Log.i("Game", "collision");
					this.mCollide = false;			
					stop();
					for (int j = 1; j <= 3; j++) {
						registerUpdateHandler(new TimerHandler(1.5f * j, false, new ITimerCallback() {
							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
								try {
									((Plant) field.getFirstChild()).pushDamage();
								} catch (Exception e) {
									Log.i("Game", "Error");
								}
							}
						}));
					}
				}
			}
		}
	}

	private void start() {
		this.mCollide = true;
		
		registerEntityModifier(new PathModifier(this.mDuration, this.mPath, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				// game over
			}

			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
			
			}
		}, EaseSineInOut.getInstance()));
	}

	private void stop() {
		this.clearEntityModifiers();
		this.mPath = new Path(2).to(this.mX, this.mY).to(0, this.mY);
	}

	private void checkAndRemove() {
		final IEntity shotLayer = Enviroment.getInstance().getScene().getChild(ExtraScene.EXTRA_GAME_LAYER);
		
		for (int i = 0; i < shotLayer.getChildCount(); i++) {
			IShape shot = (IShape) shotLayer.getChild(i);
			if (((Sprite) getFirstChild().getFirstChild()).collidesWith(shot)) {
				this.mLife--;
				if (this.mLife <= 0)
					this.detachSelf();
				shot.detachSelf();
				break;
			}
		}
	}
	
}
