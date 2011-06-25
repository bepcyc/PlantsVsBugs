package org.anddev.amatidev.pvb;

import java.util.LinkedList;

import org.amatidev.AdEnviroment;
import org.amatidev.AdScene;
import org.anddev.amatidev.pvb.bug.Bug;
import org.anddev.amatidev.pvb.bug.BugBeetle;
import org.anddev.amatidev.pvb.bug.BugLadybug;
import org.anddev.amatidev.pvb.card.Card;
import org.anddev.amatidev.pvb.card.CardTomato;
import org.anddev.amatidev.pvb.plant.Plant;
import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.util.SimplePreferences;

public class Game extends AdScene {

	private Card mSelect;
	private boolean mGameOver = false;
	private boolean mLevelFinish = false;

	@Override
	public void createScene() {
		// sfondo e tabellone
		Sprite back = new Sprite(0, 0, GameData.getInstance().mBackground);
		Sprite table = new Sprite(0, 0, GameData.getInstance().mTable);
		getChild(BACKGROUND_LAYER).attachChild(back);
		getChild(GUI_LAYER).attachChild(table);
		
		Sprite seed = new Sprite(25, 14, GameData.getInstance().mSeed);
		table.attachChild(seed);
		table.attachChild(GameData.getInstance().mMySeed);
		
		getChild(GUI_LAYER).attachChild(GameData.getInstance().mScoring);
		
		// field position
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
		
		setOnSceneTouchListener(null);
	}

	private void initLevel() {
		// contatori per individuare se in una riga c'e' un nemico
		SimplePreferences.resetAccessCount(AdEnviroment.getInstance().getContext(), "count96.0");
		SimplePreferences.resetAccessCount(AdEnviroment.getInstance().getContext(), "count173.0");
		SimplePreferences.resetAccessCount(AdEnviroment.getInstance().getContext(), "count250.0");
		SimplePreferences.resetAccessCount(AdEnviroment.getInstance().getContext(), "count327.0");
		SimplePreferences.resetAccessCount(AdEnviroment.getInstance().getContext(), "count404.0");
		
		LinkedList<Card> cards = GameData.getInstance().mCards;
		cards.clear();
		cards.add(new CardTomato());
		if (GameData.getInstance().mLevel > 1)
			cards.add(new CardTomato());
	}

	@Override
	public void startScene() {
		initLevel();
		
		// add card
		LinkedList<Card> cards = GameData.getInstance().mCards;
		int start_x = 106;
		for (int i = 0; i < cards.size(); i++) {
			Card c = cards.get(i);
			c.setPosition(start_x + i * 69, 7);
			getChild(GUI_LAYER).attachChild(c);
		}
		
		registerUpdateHandler(new TimerHandler(9f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Game.this.createEnemy();
			}
		}));
		
		registerUpdateHandler(new TimerHandler(5f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				Game.this.createSeed();
			}
		}));
	}

	public void checkLevelFinish() {
		if (GameData.getInstance().mScoring.getScore() >= GameData.getInstance().mLevel * 100) {
			Text level = new Text(0, 0, GameData.getInstance().mFontEvent, "Level Complete");
			level.setColor(1.0f, 0.3f, 0.3f);
			level.registerEntityModifier(new ScaleModifier(0.7f, 0f, 1.0f));
			level.setPosition(AdEnviroment.getInstance().getScreenWidth() / 2 - level.getWidthScaled() / 2, 
							  AdEnviroment.getInstance().getScreenHeight() / 2 - level.getHeightScaled() / 2);
			getChild(GUI_LAYER).attachChild(level);
			
			setOnAreaTouchListener(null);
			setOnSceneTouchListener(this);
			
			clearScene();
			
			this.mLevelFinish = true;
			GameData.getInstance().mLevel++;
		}
	}
	
	public void gameOver() {
		if (this.mGameOver == false && this.mLevelFinish == false) {
			Text gameover = new Text(0, 0, GameData.getInstance().mFontEvent, "Game Over");
			gameover.setColor(1.0f, 0.3f, 0.3f);
			gameover.registerEntityModifier(new ScaleModifier(0.7f, 0f, 1.0f));
			gameover.setPosition(AdEnviroment.getInstance().getScreenWidth() / 2 - gameover.getWidthScaled() / 2, 
								 AdEnviroment.getInstance().getScreenHeight() / 2 - gameover.getHeightScaled() / 2);
			getChild(GUI_LAYER).attachChild(gameover);
			
			setOnAreaTouchListener(null);
			setOnSceneTouchListener(this);
			
			clearScene();
			
			this.mGameOver = true;
		}
	}

	public void clearScene() {
		AdEnviroment.getInstance().getEngine().clearUpdateHandlers();
        clearUpdateHandlers();
        for (int i = 0; i < getChild(GAME_LAYER).getChildCount(); i++) {
        	IEntity elem = getChild(GAME_LAYER).getChild(i);
        	if (elem.getChildCount() > 0 && elem.getFirstChild() instanceof Plant)
        		elem.getFirstChild().clearUpdateHandlers();
        	if (elem instanceof Bug) {
        		elem.clearUpdateHandlers();
        		((Bug) elem).stop();
        	}
        }
	}

	@Override
	public void endScene() {
		if (this.mGameOver)
			AdEnviroment.getInstance().setScene(new MainMenu());
		else
			AdEnviroment.getInstance().setScene(new Game());
	}

	@Override
	public void manageAreaTouch(ITouchArea pTouchArea) {
		if (pTouchArea instanceof Card) {
			this.mSelect = ((Card) pTouchArea).makeSelect();
		} else {
			IEntity field = (IEntity) pTouchArea;
			if (field.getChildCount() == 1 && !(field.getFirstChild() instanceof Plant)) {
				GameData.getInstance().mMySeed.addScore(1);
				AdEnviroment.getInstance().safeDetachEntity(field.getFirstChild());
			} else {
				if (this.mSelect != null && this.mSelect.isReady() && field.getChildCount() == 0) {
					if (GameData.getInstance().mMySeed.getScore() >= this.mSelect.getPrice()) {
						GameData.getInstance().mMySeed.addScore(-this.mSelect.getPrice());
						this.mSelect.startRecharge();
						field.attachChild(this.mSelect.getPlant());
					}
				}
			}
		}
	}

	private void createEnemy() {
		int ss = 2 + (int) (GameData.getInstance().mLevel / 10);
		int dd = (int) (GameData.getInstance().mLevel / 20);
		if (dd < 3) 
			dd = 3;
		
		int numEnemies = 2;
		int ee = (int) (GameData.getInstance().mLevel / 5);
		if (ee >= numEnemies)
			ee = numEnemies - 1;
		
		for (int i = 0; i < MathUtils.random(1, MathUtils.random(2, ss)); i++) { 
			int delay = MathUtils.random(3, 18 - dd);
			final int enemyIndex = MathUtils.random(0, ee);
			final int y = 96 + MathUtils.random(0, 4) * 77;
			
			registerUpdateHandler(new TimerHandler(delay, false, new ITimerCallback() {
				@Override
				public void onTimePassed(TimerHandler pTimerHandler) {
					IEntity e = null;
					switch (enemyIndex) {
					case 0:
						e = new BugBeetle(y);
						break;
					case 1:
						e = new BugLadybug(y);
						break;
					default:
						e = new BugBeetle(y);
					}
					if (e != null)
						getChild(GAME_LAYER).attachChild(e);
				}
			}));
		}
	}

	private void createSeed() {
		int i = MathUtils.random(0, 8) * MathUtils.random(1, 5);
		final Sprite e = new Sprite(12, 25, GameData.getInstance().mSeed);
		IEntity field = getChild(GAME_LAYER).getChild(i);
		if (field.getChildCount() == 0)
			field.attachChild(e);
		
		registerUpdateHandler(new TimerHandler(3f, true, new ITimerCallback() {
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				AdEnviroment.getInstance().safeDetachEntity(e);
			}
		}));
	}
	
	@Override
	public void manageSceneTouch(TouchEvent pSceneTouchEvent) {
		AdEnviroment.getInstance().nextScene();
	}

	@Override
	public MenuScene createMenu() {
		if (this.mGameOver)
			return null;
		else
			return new GameMenu();
	}
	
}
