package org.anddev.amatidev.pvb.plant;

import org.amatidev.scene.AdScene;
import org.amatidev.util.AdEnviroment;
import org.anddev.amatidev.pvb.bug.Bug;
import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseSineInOut;

public class PlantMelon extends Plant {
	
	public PlantMelon() {
		super(null);
		
		//this.getFirstChild().getFirstChild().setPosition(0, -300);
		
		this.mLife = 1;
		this.mShotDelay = 0f;
	}
	
	public void onAttached() {
		final Sprite plant = new Sprite(getParent().getX() + 2, getParent().getY() - 300, GameData.getInstance().mPlantPotato);
		AdEnviroment.getInstance().getScene().getChild(AdScene.GAME_LAYER).attachChild(plant);
		
		Path path = new Path(2).to(getParent().getX() + 2, getParent().getY() - 300).to(getParent().getX() + 2,  getParent().getY() - 13);
		plant.registerEntityModifier(new PathModifier(2, path, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				AdEnviroment.getInstance().getEngine().runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						plant.setPosition(0, -68);
						PlantMelon.this.getFirstChild().attachChild(plant);
						
						PlantMelon.this.registerUpdateHandler(new TimerHandler(1, true, new ITimerCallback() {
							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
								AdEnviroment.getInstance().getEngine().runOnUpdateThread(new Runnable() {
									@Override
									public void run() {
										PlantMelon.this.detachSelf();
									}
								});
							}
						}));
					}
				});
			}

			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
			
			}
		}, EaseSineInOut.getInstance()));
	}
	
	public void pushDamage(final Bug pBug) {
		
	}
	
}
