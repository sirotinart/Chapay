package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 06.08.14.
 */
public class buttonActor extends Actor
{
    private TextureRegion buttonNormal;
    private TextureRegion buttonPressed;
    private boolean isPressed=false;
    private BitmapFont font;
    private String text;
    private float textX,textY;
    private float fontScale;

    public buttonActor(TextureRegion bn, TextureRegion bp, String text,float x,float y, float fontScale)
    {
        buttonNormal=bn;
        buttonPressed=bp;
        this.font=resourcePool.getPool().getFont();
        this.text=text;
        setWidth(bn.getRegionWidth()*myDeviceScreen.getScale());
        setHeight(bn.getRegionHeight() * myDeviceScreen.getScale());
        super.setPosition(x - getWidth() / 2f, y - getHeight() / 2f);
        //setScale(myDeviceScreen.getScale());
        addListener(new defaultButtonListener(this));
        this.fontScale=fontScale*myDeviceScreen.getScale();
        this.font.setScale(this.fontScale);
        BitmapFont.TextBounds bounds=font.getBounds(text);
        textX=x-bounds.width/2f;
        textY=y+bounds.height/2f;
        buttonNormal.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        buttonPressed.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public buttonActor(TextureRegion bn, TextureRegion bp, String text,float x,float y, float fontScale,float btnScaleWidth,float btnScaleHeight)
    {
        buttonNormal=bn;
        buttonPressed=bp;
        this.font=resourcePool.getPool().getFont();
        this.text=text;
        setWidth(bn.getRegionWidth()*myDeviceScreen.getScale()*btnScaleWidth);
        setHeight(bn.getRegionHeight() * myDeviceScreen.getScale()*btnScaleHeight);
        super.setPosition(x - getWidth() / 2f, y - getHeight() / 2f);
        //setScale(myDeviceScreen.getScale());
        addListener(new defaultButtonListener(this));
        this.fontScale=fontScale*myDeviceScreen.getScale();
        this.font.setScale(this.fontScale);
        BitmapFont.TextBounds bounds=font.getBounds(text);
        textX=x-bounds.width/2f;
        textY=y+bounds.height/2f;
        buttonNormal.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        buttonPressed.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        font.setScale(fontScale);
        if(!isPressed)
        {
            batch.draw(buttonNormal, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());

        }
        else
        {
            batch.draw(buttonPressed, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());
        }
        font.draw(batch,text, textX, textY);
    }

    @Override
    public void setPosition(float x, float y) {

        super.setPosition(x - getWidth() / 2f, y - getHeight() / 2f);
        BitmapFont.TextBounds bounds=font.getBounds(text);
        textX=x-bounds.width/2f;
        textY=y+bounds.height/2f;
    }


    class defaultButtonListener extends ClickListener
    {
        buttonActor parent;

        public defaultButtonListener(buttonActor parent)
        {
            this.parent=parent;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
        {
            super.touchDown(event, x, y, pointer, button);
            parent.isPressed=true;
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button)
        {
            super.touchUp(event, x, y, pointer, button);
            parent.isPressed=false;
        }
    }


}