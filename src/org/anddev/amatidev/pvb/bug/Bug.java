package org.anddev.amatidev.pvb.bug;

import org.amatidev.util.AdEnviroment;
import org.anddev.amatidev.pvb.MainGame;
import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.plant.PlantMelon;
import org.anddev.amatidev.pvb.singleton.GameData;
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
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.SimplePreferences;
import org.anddev.andengine.util.modifier.IModifier;

import android.util.Log;

public abstract class Bug extends Entity {
	
	protected int mLife = 9;
	protected int mPoint = 2;
	protected float mSpeed = 12f;
	protected float mAttack = 1.5f; // danni a tempo di collisione
	
	private Path mPath;
	private boolean mCollide = true;
	
	public Bug(final float y, final TextureRegion pTexture) {
		Sprite shadow = new Sprite(2, 55, GameData.getInstance().mPlantShadow);
		shadow.setAlpha(0.4f);
		shadow.attachChild(new Sprite(0, -68, pTexture));
		attachChild(shadow);
		
		setPosition(705, y);
	}
	
	public void addHelm(final TextureRegion pHelm, final int pLife) {
		Sprite helm = new Sprite(0, 0, pHelm);
		getBody().attachChild(helm);
		
		this.mLife += pLife;
	}
	
	public void onAttached() {
		SimplePreferences.incrementAccessCount(AdEnviroment.getInstance().getContext(), "enemy");
		SimplePreferences.incrementAccessCount(AdEnviroment.getInstance().getContext(), "count" + Float.toString(this.mY));
		restart(); // move
		
		registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				AdEnviroment.getInstance().getEngine().runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						Bug.this.checkCollisionShot(); // controlla danni da colpi
						Bug.this.checkCollisionPlant(); // crea danni a piange se collide e se vince riparte
					}
				});
			}
			
			@Override
			public void reset() {
				
			}
		});
	}

	protected void death() {
		SimplePreferences.incrementAccessCount(AdEnviroment.getInstance().getContext(), "enemy_killed");
		SimplePreferences.incrementAccessCount(AdEnviroment.getInstance().getContext(), "count" + Float.toString(this.mY), -1);
		GameData.getInstance().mMyScore.addScore(this.mPoint);
		((MainGame) AdEnviroment.getInstance().getScene()).checkLevelFinish();
	}

	protected void pushDamage() {
		pushDamage(1);
	}

	protected IShape getBody() {
		return ((IShape) getFirstChild().getFirstChild());
	}

	protected void colorDamage() {
		getBody().setColor(3f, 3f, 3f);
		registerUpdateHandler(new TimerHandler(0.1f, false, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Bug.this.getBody().setColor(1f, 1f, 1f);
			}
		}));
	}

	protected void pushDamage(final int pDamage) {
		// chiamare solo da thread safe
		colorDamage();
		
		this.mLife -= pDamage;
		if (this.mLife <= 0) {
			clearUpdateHandlers();
			stop();
			
			bodyDetachSelf();
			getFirstChild().setAlpha(0f);
			
			getFirstChild().attachChild(new Sprite(-3, -63, GameData.getInstance().mBugRip));
			registerUpdateHandler(new TimerHandler(4f, false, new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					AdEnviroment.getInstance().safeDetachEntity(Bug.this);
				}
			}));
			
			death(); // score e check level
		}
	}

	protected void bodyDetachSelf() {
		getBody().detachSelf();		
	}

	public int getLife() {
		return this.mLife;
	}

	public void restart() {
		stop();
		this.mCollide = true;
		
		this.mPath = new Path(2).to(this.mX, this.mY).to(0, this.mY);
		float duration = this.mX / this.mSpeed;
		registerEntityModifier(new PathModifier(duration, this.mPath, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				AdEnviroment.getInstance().getEngine().runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						((MainGame) AdEnviroment.getInstance().getScene()).gameOver();
					}
				});
			}

			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
			
			}
		}));
	}

	public void stop() {
		this.mCollide = false;
		this.clearEntityModifiers();
	}

	protected void checkCollisionShot() {
		int y = (int) getY() / 77;
		
		// chiamare solo da thread safe
		IEntity shotLayer = AdEnviroment.getInstance().getScene().getChild(MainGame.PRESHOT_GAME_LAYER + y);
		for (int i = 0; i < shotLayer.getChildCount(); i++) {
			IShape body_bug = getBody();
			IShape body_shot = (IShape) shotLayer.getChild(i);
			if (this.mLife > 0 && body_bug.collidesWith(body_shot)) {
				GameData.getInstance().mSoundPop.play();
				pushDamage();
				body_shot.detachSelf();
				break;
			}
		}
	}
	
	protected void checkCollisionPlant() {
		if (getX() < 678) {
			int x = (int) (getX() - 25) / 71;
			int y = (int) getY() / 77 - 1;
			int i = (int) (y * 9 + x);
			
			IEntity field = AdEnviroment.getInstance().getScene().getChild(MainGame.GAME_LAYER).getChild(i);
			if (this.mCollide && getY() == field.getY() && this.mLife > 0 && 
					field.getChildCount() == 1 && field.getFirstChild() instanceof Plant && field.getFirstChild().getFirstChild().getChildCount() > 0) {
				final Plant plant = (Plant) field.getFirstChild();
				if (plant.getLife() > 0) {
					stop();
					if (plant instanceof PlantMelon) {
						pushDamage(this.mLife);
					} else {
						registerUpdateHandler(new TimerHandler(this.mAttack, false, new ITimerCallback() {
							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
								plant.pushDamage(Bug.this);
								Bug.this.mCollide = true;					
								Log.w("Game", "collision");
							}
						}));
					}
				} else {
					//restart();
					Log.w("Game", "plant 0 life");
				}
			}
		}
	}
	
}
