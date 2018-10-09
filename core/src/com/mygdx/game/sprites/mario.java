package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.mariogame;
import com.mygdx.game.screens.playscreen;

/**
 * Created by ROHIT on 9/14/2018.
 */

public class mario extends Sprite {
    public enum State{falling,jumping,standing,runing}
    public State current,previous;
    public World world;
    public Body b2body;
    public static final float PPM=100;
    private TextureRegion mariostand;
    private Animation<TextureRegion> mariorun,mariojump;
    private float statetimer;
    private boolean runningright;

    public mario(playscreen screen ){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world=screen.getWorld();
        current=State.standing;
        previous=State.standing;
        statetimer=0;
        runningright=true;;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i=1;i<4;i++)
            frames.add(new TextureRegion(getTexture(),i*16,11,16,16));
        mariorun=new Animation<TextureRegion>(0.1f,frames);
        frames.clear();


        for(int i=4;i<6;i++)
            frames.add(new TextureRegion(getTexture(),i*16,11,16,16));
        mariojump=new Animation<TextureRegion>(0.1f,frames);



        definemario();
        mariostand=new TextureRegion(getTexture(),1,11,16,16);
        setBounds(0,0,16/PPM,16/PPM);
        setRegion(mariostand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
        setRegion(getFrames(dt));
    }


    public TextureRegion getFrames(float dt){
        current=getstate();
        TextureRegion region;
        switch (current){
            case jumping:
                region=mariojump.getKeyFrame(statetimer);
                break;
            case runing:
                region=mariorun.getKeyFrame(statetimer,true);
                break;
            default:
                region=mariostand;
                break;
        }

        if((b2body.getLinearVelocity().x<0 || !runningright)&& !region.isFlipX()){
            region.flip(true,false);
            runningright=false;
        }
        else if((b2body.getLinearVelocity().x>0 || runningright)&& region.isFlipX()){
            region.flip(true,false);
            runningright=true;
        }
        statetimer=current==previous?statetimer+dt:0;
        previous=current;

        return region;
    }

    public State getstate(){
        if(b2body.getLinearVelocity().y>0 || (b2body.getLinearVelocity().y<0 && previous==State.jumping))
            return State.jumping;
        else if(b2body.getLinearVelocity().y<0)
            return State.falling;
        else if(b2body.getLinearVelocity().x !=0)
            return State.runing;
        else
            return State.standing;

    }


    public  void definemario(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(32/PPM,32/PPM);
        bdef.type=BodyDef.BodyType.DynamicBody;
        b2body=world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape= new CircleShape();
        shape.setRadius(6/PPM);
        fdef.filter.categoryBits= mariogame.mario_bit;
        fdef.filter.maskBits=mariogame.ground_bit |mariogame.coin_bit|mariogame.brick_bit|mariogame.enemy_bit|mariogame.object_bit|mariogame.enemy_head;

        fdef.shape=shape;
        b2body.createFixture(fdef);

        EdgeShape head= new EdgeShape();
        head.set(new Vector2(-2/PPM,6/PPM),new Vector2(2/PPM,6/PPM));
        fdef.shape=head;
        fdef.isSensor=true;
        b2body.createFixture(fdef).setUserData("head");

    }


}
