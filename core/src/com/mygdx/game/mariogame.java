package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.playscreen;

public class mariogame extends Game {
	public SpriteBatch batch;
	public static final int V_width=400;
	public static final int V_height=208;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new playscreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	

}
