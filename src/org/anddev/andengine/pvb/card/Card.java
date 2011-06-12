package org.anddev.andengine.pvb.card;

import java.util.LinkedList;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.ScaleModifier;
import org.anddev.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.pvb.Game;
import org.anddev.andengine.pvb.singleton.GameData;
import org.anddev.andengine.util.modifier.IModifier;

import android.graphics.drawable.shapes.Shape;
import android.util.Log;

public abstract class Card extends Sprite {

	private Rectangle mBlack;
	protected TextureRegion mObjectTexure;

	protected float mRecharge = 10f;
	protected boolean mReady = false;

	public Card(Game game, float pX, float pY, TextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion);
		
		this.mBlack = new Rectangle(1, 1, this.mBaseWidth - 2, this.mBaseHeight - 2);
		this.mBlack.setColor(0f, 0f, 0f);
		this.mBlack.setAlpha(0.5f);
		this.mBlack.setScaleCenter(0, 0);
		attachChild(this.mBlack);
		
		game.registerTouchArea(this);
	}

	public void startRecharge() {
		this.mReady = false;
		Card.this.mBlack.setScaleY(1f);

		this.mBlack.registerEntityModifier(new ScaleModifier(this.mRecharge, 1f, 1f, 1f, 0f, new IEntityModifierListener() {
			@Override
			public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
				Card.this.mReady = true;
			}
		}));
	}

	public boolean isReady() {
		return this.mReady;
	}

	public Card makeSelect() {
		Card sel = null;
		LinkedList<Card> cards = GameData.getInstance().getCards();
		for (int i = 0; i < cards.size(); i++) {
			Card c = cards.get(i);
			if (c == this) {
				if (getChildCount() == 1) {
					Log.i("Game", Integer.toString(this.getChildCount()));
					Sprite s = new Sprite(0, 0, GameData.getInstance().mSelect);
					attachChild(s);
					sel = c;
				} else
					c.setUnselect();
			} else
				c.setUnselect();
		}
		return sel;
	}

	public void setUnselect() {
		if (getChildCount() > 1)
			detachChild(getLastChild());
	}

	public IShape getObject() {
		return new Sprite(0, 0, this.mObjectTexure);
	}
	
}
