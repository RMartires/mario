package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ROHIT on 9/14/2018.
 */

public class Brick extends interactivetileobj {
    public Brick(World world, TiledMap map, Rectangle bounds){
        super(world,map,bounds);
        fixture.setUserData(this);

    }

    @Override
    public void onHeadhit() {
        Gdx.app.log("bick","collission");
    }
}
