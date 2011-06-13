package org.anddev.andengine.pvb.plant;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extra.Enviroment;
import org.anddev.andengine.extra.ExtraScene;
import org.anddev.andengine.pvb.singleton.GameData;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseSineInOut;

import android.util.Log;

public class PlantTomato extends Plant {
	
	public PlantTomato() {
		super();
		getLastChild().attachChild(new Sprite(0, -119, GameData.getInstance().mPlantTomato));
		
		Log.i("Game", Float.toString(this.getSceneCenterCoordinates()[1]));
		Log.i("Game", Float.toString(getLastChild().getSceneCenterCoordinates()[1]));
		Log.i("Game", Float.toString(getLastChild().getLastChild().getSceneCenterCoordinates()[1]));
		//Sprite shot = new Sprite(this.getSceneCenterCoordinates()[0], getY(), GameData.getInstance().mShot);
		//Path path = new Path(2).to(getParent().getX(), getParent().getY()).to(490, getParent().getY());
		/*shot.registerEntityModifier(new PathModifier(30, path, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				
			}}, EaseSineInOut.getInstance()));*/
		//shot.registerEntityModifier(new PathModifier(10, path, EaseSineInOut.getInstance()));
		//Enviroment.getInstance().getScene().getChild(ExtraScene.GAME_LAYER).attachChild(shot);
	}
	
}
