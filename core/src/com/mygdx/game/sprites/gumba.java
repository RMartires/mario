package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.mariogame;
import com.mygdx.game.screens.playscreen;

import static com.mygdx.game.sprites.mario.PPM;

/**
 * Created by ROHIT on 9/18/2018.
 */

public class gumba extends enemy {

    private float statetime;
    private Animation<TextureRegion> walk;
    private Array<TextureRegion> frames;
    private boolean setToDest;
    private boolean destroyed;

    public gumba(playscreen screen, float x, float y) {
        super(screen, x, y);
        frames=new Array<TextureRegion>();
        for(int i=0;i<2;i++){
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"),i*16,0,16,16));
        }
        walk=new Animation(0.4f,frames);
        statetime=0;
        setBounds(getX(),getY(),16/PPM,16/PPM);

        setToDest=false;
        destroyed=false;

    }

    public void update(float dt){
        statetime+=dt;
        if(setToDest && !destroyed){
            world.destroyBody(b2body);
            destroyed=true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("goomba"),32,0,16,16));
        }
        else if(!destroyed){
            setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
            setRegion(walk.getKeyFrame(statetime,true));
        }
    }


    @Override
    protected void defineenemy() {
        BodyDef bdef= new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape= new CircleShape();
        shape.setRadius(6/PPM);
        fdef.filter.categoryBits= mariogame.enemy_bit;
        fdef.filter.maskBits=mariogame.ground_bit |mariogame.coin_bit|mariogame.brick_bit|mariogame.enemy_bit|mariogame.object_bit|mariogame.mario_bit;

        fdef.shape=shape;
        b2body.createFixture(fdef);

        //Create the head
        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0]=new Vector2(-5,8).scl(1/PPM);
        vertice[1]=new Vector2(5,8).scl(1/PPM);
        vertice[2]=new Vector2(-3,3).scl(1/PPM);
        vertice[3]=new Vector2(3,3).scl(1/PPM);
        head.set(vertice);

        fdef.shape=head;
        fdef.restitution=0.5f;
        fdef.filter.categoryBits=mariogame.enemy_head;
        b2body.createFixture(fdef).setUserData(this);

    }

    @Override
    public void hitonhead() {
        setToDest=true;
    }
}
