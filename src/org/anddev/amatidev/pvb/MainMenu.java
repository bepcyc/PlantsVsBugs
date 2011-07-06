package org.anddev.amatidev.pvb;

import org.amatidev.scene.AdScene;
import org.amatidev.util.AdEnviroment;
import org.anddev.amatidev.pvb.singleton.GameData;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.modifier.SequenceEntityModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.modifier.IModifier;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import com.openfeint.api.ui.Dashboard;

public class MainMenu extends AdScene {
	
	private int mIndex;
	
	@Override
	public MenuScene createMenu() {
		return null;
	}

	@Override
	public void createScene() {
		Sprite back = new Sprite(0, 0, GameData.getInstance().mMainBackground);
		getChild(BACKGROUND_LAYER).attachChild(back);
		
		Sprite title = new Sprite(3, 9, GameData.getInstance().mMainTitle);
		getChild(BACKGROUND_LAYER).attachChild(title);
		title.registerEntityModifier(
				new LoopEntityModifier(
						null, 
						-1, 
						null,
						new SequenceEntityModifier(
								new ScaleModifier(0.7f, 1f, 1.02f),
								new ScaleModifier(0.7f, 1.02f, 1f)
						)
				)
		);
		
		int x = AdEnviroment.getInstance().getScreenWidth() / 2;
		
		this.mIndex = 159;
		
    	Text play = new Text(0, 0, GameData.getInstance().mFontMainMenu, "PLAY");
    	//play.setColor(1.0f, 1.0f, 0.6f);
    	play.setPosition(x - play.getWidthScaled() / 2, this.mIndex);
    	
    	Text score = new Text(0, 0, GameData.getInstance().mFontMainMenu, "SCORE");
    	//score.setColor(1.0f, 1.0f, 0.6f);
    	score.setPosition(x - score.getWidthScaled() / 2, this.mIndex + 90);
    	
    	Text more = new Text(0, 0, GameData.getInstance().mFontMainMenu, "MORE GAMES");
    	//more.setColor(1.0f, 1.0f, 0.6f);
    	more.setPosition(x - more.getWidthScaled() / 2, this.mIndex + 180);
    	
    	getChild(AdScene.GAME_LAYER).attachChild(play);
    	getChild(AdScene.GAME_LAYER).attachChild(score);
    	getChild(AdScene.GAME_LAYER).attachChild(more);
    	
    	registerTouchArea(play);
    	registerTouchArea(score);
    	registerTouchArea(more);
    	
    	setOnSceneTouchListener(null);
	}

	@Override
	public void endScene() {
		GameData.getInstance().mMyLevel.resetScore();
		GameData.getInstance().mMyScore.resetScore();
		GameData.getInstance().mMySeed.resetScore();
		
		AdEnviroment.getInstance().setScene(new Tutorial());
	}

	@Override
	public void manageAreaTouch(final ITouchArea pTouchArea) {
		final Text item = (Text) pTouchArea;
		item.setColor(1f, 0.7f, 0.7f);
		
		item.registerEntityModifier(
				new SequenceEntityModifier(
						new IEntityModifierListener() {
							@Override
							public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
								item.setColor(1.0f, 1.0f, 1.0f);
								MainMenu.this.execute(pTouchArea);
							}

							@Override
							public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
								
							}
						},
						new ScaleModifier(0.1f, 1f, 1.3f),
						new ScaleModifier(0.1f, 1.3f, 1f)
		));
	}

	private void execute(ITouchArea pTouchArea) {
		Text item = (Text) pTouchArea;
		if ((int) item.getY() == this.mIndex) {
			AdEnviroment.getInstance().nextScene();
		} else if ((int) item.getY() == this.mIndex + 90) {
			try {
				Dashboard.open();
			} catch (Exception e) {
			}
		} else if ((int) item.getY() == this.mIndex + 180) {
			try{
				AdEnviroment.getInstance().getContext().startActivity(new Intent (Intent.ACTION_VIEW, Uri.parse("market://details?id=org.anddev.andengine.braingamelite")));
			} catch (ActivityNotFoundException e) {
			}
		}
	}
	
	@Override
	public void manageSceneTouch(TouchEvent pSceneTouchEvent) {
		
	}

	@Override
	public void startScene() {
		
	}

}
