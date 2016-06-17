package com.devnull.art.chapay;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by art on 10.08.14.
 */
public class TextureActor extends Actor {
    TextureRegion toDraw;

    public TextureActor(TextureRegion tex) {
        toDraw = tex;
        setPosition(0, 0);
        setWidth(tex.getRegionWidth()*myDeviceScreen.getScale());
        setHeight(tex.getRegionHeight()*myDeviceScreen.getScale());
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        batch.draw (toDraw, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());
    }
}