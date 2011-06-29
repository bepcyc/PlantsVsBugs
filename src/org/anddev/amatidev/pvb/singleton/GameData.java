package org.anddev.amatidev.pvb.singleton;

import java.util.LinkedList;

import org.amatidev.AdResourceLoader;
import org.amatidev.AdTextScoring;
import org.anddev.amatidev.pvb.card.Card;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.HorizontalAlign;

import android.graphics.Color;

public class GameData {
	
    private static GameData mInstance = null;
    
    // generici
    public LinkedList<Card> mCards;
    
    public AdTextScoring mMySeed;
    public AdTextScoring mMyScore;
    public AdTextScoring mMyLevel;
    
    public TextureRegion mSplash;
    
    public TextureRegion mMainBackground;
    public TextureRegion mBackground;
    public TextureRegion mTable;
    public TextureRegion mSeed;
    public TextureRegion mSeed2;
    public TextureRegion mShot;
    public TextureRegion mShotShadow;
    public TextureRegion mPlantShadow;
    public TextureRegion mCardSelected;
    
    // cards
    public TextureRegion mCard;
    public TextureRegion mCardTomato;
	public TextureRegion mCardPotato;
	public TextureRegion mCardBag;
	
	// plants
	public TextureRegion mPlantTomato;
	public TextureRegion mPlantPotato;
	public TextureRegion mPlantBag;
	
	// bugs
	public TextureRegion mBugBeetle;
	public TextureRegion mBugLadybug;

	// fonts
	public Font mFontSeed;
	public Font mFontCard;
	public Font mFontScore;
	public Font mFontEvent;
	public Font mFontMainMenu;
	public Font mFontGameMenu;
	
	private GameData() {
		
	}
	
	public static synchronized GameData getInstance() {
		if (mInstance == null) 
			mInstance = new GameData();
		return mInstance;
	}
	
	public void initData() {
		this.mFontSeed = AdResourceLoader.getFont("akaDylan Plain", 20, 2, Color.WHITE, Color.BLACK);
		this.mFontCard = AdResourceLoader.getFont("akaDylan Plain", 14, 1, Color.WHITE, Color.BLACK);
		this.mFontScore = AdResourceLoader.getFont("akaDylan Plain", 22, 2, Color.WHITE, Color.BLACK);
		
		this.mFontEvent = AdResourceLoader.getFont(512, 512, "akaDylan Plain", 62, 3, Color.WHITE, Color.BLACK);
		this.mFontMainMenu = AdResourceLoader.getFont(512, 512, "akaDylan Plain", 40	, 2, Color.WHITE, Color.BLACK);
		this.mFontGameMenu = AdResourceLoader.getFont(512, 512, "akaDylan Plain", 48, 3, Color.WHITE, Color.BLACK);
		
		this.mSplash = AdResourceLoader.getTexture(1024, 512, "splash");
		this.mBackground = AdResourceLoader.getTexture(1024, 512, "back");
		this.mMainBackground = AdResourceLoader.getTexture(1024, 512, "main2");
		
		this.mTable  = AdResourceLoader.getTexture(1024, 128, "table");
		this.mSeed = AdResourceLoader.getTexture(64, 64, "seed");
		this.mSeed2 = AdResourceLoader.getTexture(64, 64, "seed2");
		this.mShot = AdResourceLoader.getTexture(64, 64, "shot");
		this.mShotShadow = AdResourceLoader.getTexture(64, 64, "shadow2");
		
		this.mCardSelected = AdResourceLoader.getTexture(64, 128, "select");
		
		this.mCard = AdResourceLoader.getTexture(64, 128, "card");
		
		this.mCardTomato = AdResourceLoader.getTexture(64, 64, "card_tomato");
		this.mCardPotato = AdResourceLoader.getTexture(64, 64, "card_potato");
		this.mCardBag = AdResourceLoader.getTexture(64, 64, "card_bag");
		
		this.mPlantShadow = AdResourceLoader.getTexture(64, 16, "shadow");
		
		this.mPlantTomato = AdResourceLoader.getTexture(64, 128, "tomato");
		this.mPlantPotato = AdResourceLoader.getTexture(64, 128, "potato");
		this.mPlantBag = AdResourceLoader.getTexture(64, 128, "bag");
		
		this.mBugBeetle = AdResourceLoader.getTexture(64, 128, "beetle");
		this.mBugLadybug = AdResourceLoader.getTexture(64, 128, "ladybug");
		
		this.mMySeed = new AdTextScoring(48, 67, GameData.getInstance().mFontSeed, HorizontalAlign.CENTER, 6);
		this.mMyScore = new AdTextScoring(703, 30, GameData.getInstance().mFontScore, HorizontalAlign.RIGHT, 0, "Pt.");
		this.mMyLevel = new AdTextScoring(703, 70, GameData.getInstance().mFontScore, HorizontalAlign.RIGHT, 0, "Lv.");
		
		this.mCards = new LinkedList<Card>();
	}
	
}
