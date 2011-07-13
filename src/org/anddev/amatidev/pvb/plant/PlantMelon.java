package org.anddev.amatidev.pvb.plant;

import org.amatidev.scene.AdScene;
import org.amatidev.util.AdEnviroment;
import org.amatidev.util.AdVibration;
import org.anddev.amatidev.pvb.Game;
import org.anddev.amatidev.pvb.bug.Bug;
import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.AlphaModifier;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseSineInOut;

public class PlantMelon extends Plant {
	
	public PlantMelon() {
		super(null);
		
		this.mLife = 1;
		this.mShotDelay = 0f;
	}
	
	public void onAttached() {
		final Sprite plant = new Sprite(getParent().getX() + 2, getParent().getY() - 300, GameData.getInstance().mPlantMelon);
		AdEnviroment.getInstance().getScene().getChild(Game.EXTRA2_GAME_LAYER).attachChild(plant);
		
		Path path = new Path(2).to(getParent().getX() + 2, getParent().getY() - 300).to(getParent().getX() + 2,  getParent().getY() - 13);
		plant.registerEntityModifier(new PathModifier(1.5f, path, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				AdEnviroment.getInstance().getEngine().runOnUpdateThread(new Runnable() {
					@Override
					public void run() {
						PlantMelon.this.getFirstChild().attachChild(new Entity());
						AdVibration.duration(300);
						
						PlantMelon.this.registerUpdateHandler(new TimerHandler(0.4f, false, new ITimerCallback() {
							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
								PlantMelon.this.mLife = 0;
								plant.registerEntityModifier(
										new LoopEntityModifier(
												new IEntityModifierListener() {
													@Override
													public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
														
													}

													@Override
													public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
														plant.detachSelf();
														PlantMelon.this.detachSelf();
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
