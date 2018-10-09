package com.mygdx.game.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.playscreen;

/**
 * Created by ROHIT on 9/14/2018.
 */

public abstract class interactivetileobj {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected AssetManager manager;


    protected Fixture fixture;

    public interactivetileobj(playscreen screen, Rectangle bounds, AssetManager manager){
        this.world=screen.getWorld();
        this.map=screen.getMap();
        this.bounds=bounds;

        BodyDef bodyDef=new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type= BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX()+bounds.getWidth()/2)/ mario.PPM,(bounds.getY()+bounds.getHeight()/2)/mario.PPM);

        body=world.createBody(bodyDef);

        shape.setAsBox(bounds.getWidth()/2/mario.PPM,bounds.getHeight()/2/mario.PPM);
        fdef.shape=shape;
        fixture=body.createFixture(fdef);




    }


    public abstract void onHeadhit();

    public void setCategoryFilter(short filterbit){
        Filter filter=new Filter();
        filter.categoryBits=filterbit;
        fixture.setFilterData(filter);

    }


    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer=(TiledMapTileLayer)map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x*mario.PPM/16),(int)(body.getPosition().y*mario.PPM/16));

    }


}
