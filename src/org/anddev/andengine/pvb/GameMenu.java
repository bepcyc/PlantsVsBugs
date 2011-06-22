package org.anddev.andengine.pvb;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.anddev.andengine.extra.Enviroment;
import org.anddev.andengine.extra.ExtraMenu;
import org.anddev.andengine.extra.ExtraScene;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.pvb.singleton.GameData;

public class GameMenu extends ExtraMenu {
	private static final int MENU_AUDIO = 0;
	private static final int MENU_VIBRO = 1;
	private static final int MENU_EXIT = 2;
	
	public GameMenu() {
		Font font = GameData.getInstance().mFontGameMenu;
		
		String audio = "";
		if (Enviroment.getInstance().getAudio())
			audio += "ON";
		else
			audio += "OFF";
		
		String vibro = "";
		if (Enviroment.getInstance().getVibro())
			vibro += "ON";
		else
			vibro += "OFF";
		
		IMenuItem resetMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_AUDIO, font, "AUDIO " + audio), 1f, 0.7f, 0.7f, 1.0f, 1.0f, 0.6f);
		resetMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		addMenuItem(resetMenuItem);
		
		IMenuItem vibroMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_VIBRO, font, "VIBRO " + vibro), 1f, 0.7f, 0.7f, 1.0f, 1.0f, 0.6f);
		vibroMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		addMenuItem(vibroMenuItem);
		
		IMenuItem quitMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_EXIT, font, "EXIT"), 1f, 0.7f, 0.7f, 1.0f, 1.0f, 0.6f);
		quitMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		addMenuItem(quitMenuItem);
		
		buildAnimations();
	}
	
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
		case MENU_AUDIO:
			Enviroment.getInstance().getScene().clearChildScene();
			Enviroment.getInstance().toggleAudio();
			((ExtraScene) Enviroment.getInstance().getScene()).hideOpacity();
			return true;
		case MENU_VIBRO:
			Enviroment.getInstance().getScene().clearChildScene();
			Enviroment.getInstance().toggleVibro();
			((ExtraScene) Enviroment.getInstance().getScene()).hideOpacity();
			return true;
		case MENU_EXIT:
			Enviroment.getInstance().setScene(new MainMenu());
			return true;
		default:
			return false;
		}
	}
	
}