package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.EventListener;
/**
 * Created by art on 17.08.14.
 */
public class Finger extends Actor
{
    private TextureRegion tex;
    private Body fingerBody;
    private Boolean visible;
    private Player parent;



    public Finger(Player parent)
    {
        visible=true;

        switch (parent.getPlayerColor())
        {
            case 0:
            {
                tex=resourcePool.getPool().getResource("finger_blue");
                break;
            }
            case 1:
            {
                tex=resourcePool.getPool().getResource("finger_white");
                break;
            }
        }

        setWidth(tex.getRegionWidth()*myDeviceScreen.getScale());
        setHeight(tex.getRegionHeight()*myDeviceScreen.getScale());
        this.parent=parent;
        BodyDef fingerDef=new BodyDef();
        fingerDef.type= BodyDef.BodyType.DynamicBody;
        fingerDef.linearDamping=0.7f;
        fingerDef.angularDamping=0.0f;
        fingerDef.bullet=true;
        fingerBody=resourcePool.getWorld().createBody(fingerDef);

        CircleShape fingerCircle=new CircleShape();
        fingerCircle.setRadius(1);

        FixtureDef fingerFixture=new FixtureDef();
        fingerFixture.shape=fingerCircle;
        fingerFixture.density=3;
        fingerFixture.friction=1;
        fingerFixture.restitution=0.7f;


        if(parent.getPlayerColor()==0)
        {
            fingerFixture.filter.categoryBits=0x0004;
            fingerFixture.filter.maskBits=0x0001;
        }
        else
        {
            fingerFixture.filter.categoryBits=0x0008;
            fingerFixture.filter.maskBits=0x0002;
        }

        fingerBody.createFixture(fingerFixture);
        fingerBody.setUserData(parent.getPlayerColor());
        hide();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
            batch.draw(tex, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        super.setPosition(fingerBody.getPosition().x*myDeviceScreen.BOX_WORLD_TO-getWidth()/2f,
                fingerBody.getPosition().y*myDeviceScreen.BOX_WORLD_TO-getHeight()/2f);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable)
    {
        super.hit(x, y, touchable);
        if(parent.getPlayerColor()==ChapayGame.getInstance().getGameData().getStep() &&
           UserInterface.getUI().getGameField().getBoard().isOnField(new Vector2(getX()+x,getY()+y)))
            return this;
        else
            return null;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x-getWidth()/2f, y-getWidth()/2f);
        fingerBody.setTransform(x/myDeviceScreen.BOX_WORLD_TO,
                                y/myDeviceScreen.BOX_WORLD_TO, 0);
        fingerBody.setLinearVelocity(0,0);
    }

    public void show()
    {
        fingerBody.setLinearVelocity(0,0);
        resetPosition();
        //fingerBody.setAwake(true);
        fingerBody.setActive(true);
    }
    public void hide()
    {
        fingerBody.setLinearVelocity(0,0);
        resetPosition();
        fingerBody.setActive(false);
        //fingerBody.setAwake(false);

    }

    public Body getBody()
    {
        return fingerBody;
    }

    public void move()
    {
        Vector2 t=fingerBody.getPosition();
        t.x*=myDeviceScreen.BOX_WORLD_TO;
        t.y*=myDeviceScreen.BOX_WORLD_TO;
        Vector2 u=parent.getListener().getUp();

        fingerBody.setLinearVelocity((u.x-t.x)*(myDeviceScreen.getScale())/2,(u.y-t.y)*(myDeviceScreen.getScale())/2);
    }

    private void resetPosition()
    {
        float x,y;
        x= Gdx.graphics.getWidth()/2;
        if(parent.getPlayerColor()==0)
        {
            y=Gdx.graphics.getHeight()*0.1f;
        }
        else
        {
            y=Gdx.graphics.getHeight()*0.9f;
        }
        setPosition(x,y);
    }

}

