package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.mariogame;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.screens.playscreen;

/**
 * Created by ROHIT on 9/14/2018.
 */

public class coin extends interactivetileobj {
    private static TiledMapTileSet tileset;
    private final int Blank_coin=28;

    public coin(playscreen screen,
                Rectangle bounds, AssetManager manager){
        super(screen,bounds,manager);
        tileset=map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(mariogame.coin_bit);
    }

    @Override
    public void onHeadhit() {
        Gdx.app.log("coin","collission");
        getCell().setTile(tileset.getTile(Blank_coin));
        Hud.addScore(200);

    }



}
