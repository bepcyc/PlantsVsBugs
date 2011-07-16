package org.anddev.amatidev.pvb.plant;

import org.amatidev.scene.AdScene;
import org.amatidev.util.AdEnviroment;
import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.modifier.IModifier;

public class PlantBag extends Plant {
	
	public PlantBag() {
		super(GameData.getInstance().mPlantBag);
		
		this.mLife = 3;
		this.mShotDelay = 0f;
	}
	
	public void onAttached() {
		super.onAttached();
		
		registerUpdateHandler(new TimerHandler(10, true, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				PlantBag.this.genSeed();
			}
		}));
	}
	
	private void genSeed() {
		// plant animation
		getFirstChild().registerEntityModifier(
				new SequenceEntityModifier(
						new ScaleModifier(0.3f, 1f, 1.1f),
						new ScaleModifier(0.3f, 1.1f, 1f)
				)
		);
		
		// creazione shot
		Sprite seed = new Sprite(getParent().getX() + 11, getParent().getY() + 5, GameData.getInstance().mSeed2);
		Path path = new Path(2).to(getParent().getX() + 11, getParent().getY() + 5).to(getParent().getX() + 11, getParent().getY() - 40);
		seed.registerEntityModifier(new PathModifier(1f, path, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				GameData.getInstance().mSoundSeed.play();
				GameData.getInstance().mMySeed.addScore(1);
				AdEnviroment.getInstance().safeDetachEntity(pItem);
			}

			@Override
			public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
				
			}
		}));
		
		AdEnviroment.getInstance().getScene().getChild(AdScene.EXTRA_GAME_LAYER).attachChild(seed);
	}
	
}
