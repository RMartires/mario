package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.sprites.interactivetileobj;

import java.awt.event.ContainerListener;

/**
 * Created by ROHIT on 9/16/2018.
 */

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixa=contact.getFixtureA();
        Fixture fixb=contact.getFixtureB();


        if(fixa.getUserData()=="head" || fixb.getUserData()=="head"){
            Fixture head = fixa.getUserData()=="head" ? fixa:fixb;
            Fixture object = head==fixa?fixb:fixa;

            if(object.getUserData() instanceof interactivetileobj){
                ((interactivetileobj)object.getUserData()).onHeadhit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
