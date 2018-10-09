package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.playscreen;

public class mariogame extends Game {
	public SpriteBatch batch;
	public static final int V_width=400;
	public static final int V_height=208;

	public static  final short mario_bit=2;
	public static final short brick_bit=4;
	public static final short coin_bit=8;
	public static final short distroyerd_bit=16;
	public static final short ground_bit =1;
	public static final short object_bit=32;
	public static final short enemy_bit=64;
	public static final short enemy_head=120;

	public AssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager= new AssetManager();
		manager.load("audio/music/mario_music.mp3", Music.class);
		manager.finishLoading();
		setScreen(new playscreen(this,manager));
	}

	@Override
	public void render () {
		super.render();
		manager.update();
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}
