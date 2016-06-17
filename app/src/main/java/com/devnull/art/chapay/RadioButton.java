package com.devnull.art.chapay;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 17.09.14.
 */
public class RadioButton extends Actor
{
    private TextureRegion normal;
    private TextureRegion pressed;
    private boolean isPressed=false;
    private String text;
    private textActor t;


    public RadioButton (String text)
    {
        this.text=text;
        normal=resourcePool.getPool().getResource("radio1");
        pressed=resourcePool.getPool().getResource("radio2");

        setWidth(normal.getRegionWidth()*myDeviceScreen.getScale());
        setHeight(normal.getRegionHeight()*myDeviceScreen.getScale());


        t=new textActor(text,0,0,0.5f);
        setPosition(0,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        if(!isPressed)
        {
            batch.draw(normal, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());


        }
        else
        {
            batch.draw(pressed, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());
        }
        t.draw(batch,parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);

        BitmapFont.TextBounds b=resourcePool.getPool().getFont().getBounds(text);

        float x1=getCenterX()+getWidth();
        float y1=getCenterY()+b.height/2f;
        t.setPosition(x1,y1);
    }

    public void unPress()
    {
        isPressed=false;
    }

    public void press()
    {
        isPressed=true;
    }


}
