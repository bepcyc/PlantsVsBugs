package org.anddev.andengine.pvb;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.extra.Enviroment;
import org.anddev.andengine.extra.ExtraGameActivity;
import org.anddev.andengine.pvb.singleton.GameData;

public class PlantsVsBugs extends ExtraGameActivity {

	@Override
	protected int getLayoutID() {
		return R.layout.main;
	}

	@Override
	protected int getRenderSurfaceViewID() {
		return R.id.xmllayoutexample_rendersurfaceview;
	}

	@Override
	public void onLoadComplete() {
		
	}

	@Override
	public Engine onLoadEngine() {
		return Enviroment.createEngine(ScreenOrientation.LANDSCAPE, true, false);
	}

	@Override
	public void onLoadResources() {
		GameData.getInstance().initData();
	}

	@Override
	public Scene onLoadScene() {
		return new Game();
	}
	
}