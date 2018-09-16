package com.mygdx.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.sprites.Brick;
import com.mygdx.game.sprites.coin;
import com.mygdx.game.sprites.mario;

/**
 * Created by ROHIT on 9/14/2018.
 */

public class B2worldcreator {
    public B2worldcreator(World world, TiledMap map){

        //b2dr
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;



        for(MapObject object:map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            bodyDef.type=BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX()+rectangle.getWidth()/2)/mario.PPM,(rectangle.getY()+rectangle.getHeight()/2)/mario.PPM);

            body=world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth()/2/mario.PPM,rectangle.getHeight()/2/mario.PPM);
            fdef.shape=shape;
            body.createFixture(fdef);

        }


        for(MapObject object:map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            bodyDef.type=BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX()+rectangle.getWidth()/2)/mario.PPM,(rectangle.getY()+rectangle.getHeight()/2)/mario.PPM);

            body=world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth()/2/mario.PPM,rectangle.getHeight()/2/mario.PPM);
            fdef.shape=shape;
            body.createFixture(fdef);


        }

        for(MapObject object:map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            new Brick(world,map,rectangle);

        }

        for(MapObject object:map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            new coin(world,map,rectangle);
        }


    }
}
