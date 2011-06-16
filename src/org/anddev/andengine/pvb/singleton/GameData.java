package org.anddev.andengine.pvb.singleton;

import java.util.LinkedList;

import org.anddev.andengine.extra.Resource;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.pvb.card.Card;

public class GameData {
	
    private static GameData mInstance = null;
    
    private int mLevel = 1;
    
    public TextureRegion mShot;
    public TextureRegion mShotShadow;
    
    // cards
    public TextureRegion mCardSelected;
    
    public TextureRegion mCard;
    public TextureRegion mCardTomato;
	public TextureRegion mCardFlower2;

	
	// plants
	public TextureRegion mPlantShadow;
	
	public TextureRegion mPlantTomato;
	public TextureRegion mPlantFlower2;
	
	private LinkedList<Card> mCards;
	
	private GameData() {
		
	}
	
	public static synchronized GameData getInstance() {
		if (mInstance == null) 
			mInstance = new GameData();
		return mInstance;
	}
	
	public void initData() {
		this.mShot = Resource.getTexture(64, 64, "shot");
		this.mShotShadow = Resource.getTexture(64, 64, "shadow2");
		
		this.mCardSelected = Resource.getTexture(64, 128, "select");
		
		this.mCard = Resource.getTexture(64, 128, "card");
		
		this.mCardTomato = Resource.getTexture(64, 128, "card_tomato");
		this.mCardFlower2 = Resource.getTexture(64, 128, "card_flower2");
		
		this.mPlantShadow = Resource.getTexture(64, 16, "shadow");
		
		this.mPlantTomato = Resource.getTexture(64, 128, "tomato");
		this.mPlantFlower2 = Resource.getTexture(64, 128, "default2");
		
		this.mCards = new LinkedList<Card>();
	}
	
	public LinkedList<Card> getCards() {
		return this.mCards;
	}

	public int getLevel() {
		return this.mLevel;
	}

	public void nextLevel() {
		this.mLevel++;
	}
	
}
