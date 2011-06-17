package org.anddev.andengine.pvb;

import java.util.LinkedList;

import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extra.Enviroment;
import org.anddev.andengine.extra.ExtraScene;
import org.anddev.andengine.extra.Resource;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.pvb.bug.Bug;
import org.anddev.andengine.pvb.bug.BugLadybug;
import org.anddev.andengine.pvb.card.Card;
import org.anddev.andengine.pvb.card.CardTomato;
import org.anddev.andengine.pvb.card.CardFlower2;
import org.anddev.andengine.pvb.plant.Plant;
import org.anddev.andengine.pvb.singleton.GameData;
import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.util.SimplePreferences;

import android.util.Log;

public class Game extends ExtraScene {

	private TextureRegion mBackground;
	private Card mSelect;

	@Override
	public void createScene() {
		SimplePreferences.setValue(Enviroment.getInstance().getContext(), "count96.0", 0);
		SimplePreferences.setValue(Enviroment.getInstance().getContext(), "count173.0", 0);
		SimplePreferences.setValue(Enviroment.getInstance().getContext(), "count250.0", 0);
		SimplePreferences.setValue(Enviroment.getInstance().getContext(), "count327.0", 0);
		SimplePreferences.setValue(Enviroment.getInstance().getContext(), "count404.0", 0);
		
		this.mBackground = Resource.getTexture(1024, 512, "back");
		Sprite back = new Sprite(0, 0, this.mBackground);
		Sprite table = new Sprite(0, 0, GameData.getInstance().mTable);
		getChild(BACKGROUND_LAYER).attachChild(back);
		getChild(EXTRA_GAME_LAYER).attachChild(table);
		
		for (int i = 0; i < 45; i++) {
			int x = i % 9;
			int y = (int)(i / 9);
			Rectangle field = new Rectangle(0, 0, 68, 74);
			field.setColor(0f, 0f, 0f);
			field.setAlpha(0.05f);
			field.setPosition(42 + x * 71,  96 + y * 77);
			getChild(GAME_LAYER).attachChild(field);
			
			registerTouchArea(field);
		}
	}

	@Override
	public void startScene() {
		LinkedList<Card> cards = GameData.getInstance().getCards();
		cards.add(new CardTomato(0, 0));
		cards.add(new CardFlower2(0, 0));
		cards.add(new CardTomato(0, 0));
		cards.add(new CardTomato(0, 0));
		cards.add(new CardFlower2(0, 0));
		cards.add(new CardTomato(0, 0));
		
		int start_x = 106;
		for (int i = 0; i < cards.size(); i++) {
			Card c = cards.get(i);
			c.setPosition(start_x + i * 69, 7);
			getChild(EXTRA_GAME_LAYER).attachChild(c);
		}
		
		this.getEnemy(5); // first a 3 sec
		
		registerUpdateHandler(new TimerHandler(15f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Game.this.getEnemy(MathUtils.random(3, 13));
			}
		}));
	}

	@Override
	public void endScene() {
		
	}

	@Override
	public void manageAreaTouch(ITouchArea pTouchArea) {
		if (pTouchArea instanceof Card) {
			Log.i("Game", "card");
			
			Card c = (Card) pTouchArea;
			this.mSelect = c.makeSelect();
		} else {
			if (this.mSelect != null && this.mSelect.isReady()) { // aggiungere controllo costo
				IEntity field = (IEntity) pTouchArea;
				if (field.getChildCount() == 0) {
					Log.i("Game", "recharge/object");
					
					this.mSelect.startRecharge();
					Plant plant = this.mSelect.getPlant();
					field.attachChild(plant);
				}
			}
		}
	}

	@Override
	public void manageSceneTouch(TouchEvent pSceneTouchEvent) {
		
	}

	@Override
	public MenuScene createMenu() {
		return null;
	}

	private void getEnemy(int pDelay) {
		int y = MathUtils.random(0, 4);
		final Bug e = new BugLadybug(96 + y * 77); // stessa altezza dei fields
		
		registerUpdateHandler(new TimerHandler(pDelay, false, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Game.this.getChild(Game.GAME_LAYER).attachChild(e);
			}
		}));
	}

}
