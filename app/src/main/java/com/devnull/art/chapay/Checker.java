package com.devnull.art.chapay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;


/**
 * Created by art on 17.08.14.
 */
public class Checker extends Actor
{
    private TextureRegion tex;
    private Body checkerBody;
    private Group parentGroup;
    private int checkerColor;

    public Checker(String color,Group parent)
    {
        tex=resourcePool.getPool().getResource(color);
        setWidth(tex.getRegionWidth() * myDeviceScreen.getAlternateScale());
        setHeight(tex.getRegionHeight()*myDeviceScreen.getAlternateScale());

        BodyDef checkerDef=new BodyDef();
        checkerDef.type= BodyDef.BodyType.DynamicBody;
        checkerDef.linearDamping=0f;
        checkerDef.angularDamping=0f;
        checkerDef.bullet=true;
        checkerBody=resourcePool.getWorld().createBody(checkerDef);

        CircleShape checkerCircle=new CircleShape();
        checkerCircle.setRadius(1);

        FixtureDef checkerFixture=new FixtureDef();
        checkerFixture.shape=checkerCircle;
        checkerFixture.density=10;
        checkerFixture.friction=1;
        checkerFixture.restitution=0.6f;

        if(color.compareTo("checker_gray")==0)
        {
            checkerFixture.filter.categoryBits=0x0001;
            checkerFixture.filter.maskBits=0x007;
        }
        else
        {
            checkerFixture.filter.categoryBits=0x0002;
            checkerFixture.filter.maskBits=0x000B;
        }


        checkerBody.createFixture(checkerFixture);


        tex.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        parentGroup=parent;
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        batch.draw(tex, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        if(UserInterface.getUI().getGameField().getBoard().isOnBoard(new Vector2(getCenterX(),getCenterY())))
        {
            Vector2 speed=checkerBody.getLinearVelocity();

            float sp= (float) Math.sqrt(speed.x*speed.x+speed.y*speed.y);

            float F=-500;


            if(sp>0.3f)
            {
                float fx=speed.x*F/sp;
                float fy=speed.y*F/sp;

                checkerBody.applyForce(fx, fy,
                        checkerBody.getPosition().x,checkerBody.getPosition().y,true);
            }
            else
            {
                checkerBody.setLinearVelocity(0,0);
            }
            super.setPosition(checkerBody.getPosition().x * myDeviceScreen.BOX_WORLD_TO - getWidth() / 2f,
                    checkerBody.getPosition().y * myDeviceScreen.BOX_WORLD_TO - getHeight() / 2f);


        }
        else
        {
            parentGroup.removeActor(this);
            checkerBody.setActive(false);
            checkerBody.getWorld().destroyBody(checkerBody);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x-getWidth()/2f, y-getWidth()/2f);
        checkerBody.setTransform(x/myDeviceScreen.BOX_WORLD_TO,
                y/myDeviceScreen.BOX_WORLD_TO, 0);
    }

    public boolean isMoving()
    {
        Vector2 velocity=checkerBody.getLinearVelocity();
        if(velocity.x!=0||velocity.y!=0)
        {
            return true;
        }
        else return false;
    }

    public Vector2 getBodyPosition()
    {
        return checkerBody.getPosition();
    }

    public void setLinearSpeed(float sx, float sy)
    {
        checkerBody.setLinearVelocity(sx,sy);
    }

}
