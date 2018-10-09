package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.mariogame;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.screens.playscreen;

/**
 * Created by ROHIT on 9/14/2018.
 */

public class Brick extends interactivetileobj {
    public Brick(playscreen screen, Rectangle bounds, AssetManager manager){
        super(screen,bounds,manager);
        fixture.setUserData(this);
        setCategoryFilter(mariogame.brick_bit);

    }

    @Override
    public void onHeadhit() {
        Gdx.app.log("bick","collission");

        setCategoryFilter(mariogame.distroyerd_bit);
        getCell().setTile(null);
        Hud.addScore(100);

    }
}
