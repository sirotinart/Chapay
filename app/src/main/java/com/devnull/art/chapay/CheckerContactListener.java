package com.devnull.art.chapay;

import android.util.Log;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by art on 20.08.14.
 */
public class CheckerContactListener implements ContactListener
{

    @Override
    public void beginContact(Contact contact)
    {


    }

    @Override
    public void endContact(Contact contact)
    {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold)
    {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse)
    {
        if(contact.getFixtureA()!=null&&contact.getFixtureA().getBody().getUserData()!=null
                && contact.getFixtureA().getBody().getUserData().equals(0) ||
                contact.getFixtureA()!=null&&contact.getFixtureA().getBody().getUserData()!=null
                        && contact.getFixtureA().getBody().getUserData().equals(1))
        {
            ChapayGame.getInstance().getGameData().stopCurrentStep();
            UserInterface.getUI().getGameField().getStage().needSwitchPlayer();
        }

        if(contact.getFixtureB()!=null&&contact.getFixtureB().getBody().getUserData()!=null
                && contact.getFixtureB().getBody().getUserData().equals(0) ||
                contact.getFixtureB()!=null&&contact.getFixtureB().getBody().getUserData()!=null
                        && contact.getFixtureB().getBody().getUserData().equals(1))
        {
            ChapayGame.getInstance().getGameData().stopCurrentStep();
            UserInterface.getUI().getGameField().getStage().needSwitchPlayer();
        }
    }
}
