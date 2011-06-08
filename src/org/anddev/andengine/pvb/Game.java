package org.anddev.andengine.pvb;

import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extra.ExtraScene;
import org.anddev.andengine.extra.Resource;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class Game extends ExtraScene {

	private TextureRegion mBack;

	@Override
	public MenuScene createMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createScene() {
		setBackground(new ColorBackground(0.603921569f, 0.909803922f, 0.337254902f));
		
		this.mBack = Resource.getTexture(1024, 512, "back");
		Sprite back = new Sprite(0, 0, this.mBack);
		getChild(GAME_LAYER).attachChild(back);
	}

	@Override
	public void endScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manageAreaTouch(ITouchArea pTouchArea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manageSceneTouch(TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startScene() {
		// TODO Auto-generated method stub
		
	}

}
