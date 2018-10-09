package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.playscreen;

/**
 * Created by ROHIT on 9/18/2018.
 */

public abstract class enemy extends Sprite {
    protected World world;
    protected playscreen screen;
    public Body b2body;

    public enemy(playscreen screen, float x, float y){
        this.world=screen.getWorld();
        this.screen=screen;
        setPosition(x,y);
        defineenemy();

    }


    protected abstract void defineenemy();
    public abstract void hitonhead();

}
