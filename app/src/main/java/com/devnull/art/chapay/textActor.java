package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.sql.Struct;

/**
 * Created by art on 06.08.14.
 */
public class textActor extends Actor
{
    private String text;
    private float scale;
    private float height;
    private float width;
    boolean multiline;
    private float x,y;

    public textActor(String text,float x,float y, float scale)
    {
        this.scale=scale*myDeviceScreen.getScale();
        resourcePool.getPool().getFont().setScale(this.scale);
        this.x=x;
        this.y=y;

        changeText(text);

    }

    public void changeText(String newText)
    {
        this.text=newText;
        BitmapFont.TextBounds b=resourcePool.getPool().getFont().getBounds(text);
        height=b.height;
        width=b.width;
        setPosition(x-b.width/2f,y+b.height/2f);
        this.multiline=false;

    }

    public textActor(String text,float x,float y, float scale, boolean multiline)
    {

        this.text=text;
        this.scale=scale*myDeviceScreen.getScale();
        resourcePool.getPool().getFont().setScale(this.scale);
        BitmapFont.TextBounds b=resourcePool.getPool().getFont().getBounds(text);
        //setWidth(b.width);
        //setHeight(b.height);
        height=b.height;
        width=b.width;
        setPosition(x,y);
        //setCenterPosition(x,y);
        this.multiline=true;

    }


    public void drawMultiline(Batch batch)
    {
        float strY=getY();
        String tmp[]=text.split(" ");
        int tmpCounter=0;
        StringBuilder tmpStr=new StringBuilder();
        int i=0;
        resourcePool.getPool().getFont().setScale(scale);
        while (i==0)
        {
            String s=new String();
            if (tmpStr.length()!=0)
                s=tmpStr.toString();
            BitmapFont.TextBounds b=resourcePool.getPool().getFont().getBounds(s);
            if(b.width< Gdx.graphics.getWidth()-getX()*2)
            {

                s=s.concat(tmp[tmpCounter]);
                s=s.concat(" ");
                b=resourcePool.getPool().getFont().getBounds(s);
                if(b.width<Gdx.graphics.getWidth()-getX()*2)
                {
                    //tmpCounter;
                    if (tmpCounter==tmp.length)
                    {
                        i=1;
                    }
                    else
                    {

                        tmpStr.append(tmp[tmpCounter]);
                        tmpStr.append(" ");
                        tmpCounter++;
                        if(tmpCounter==tmp.length)i=1;
                    }
                }
                else
                {
                    i=2;
                }

            }
            if(i==1||i==2)
            {
                resourcePool.getPool().getFont().draw(batch,tmpStr.toString(),getX(),strY);
                tmpStr.delete(0,tmpStr.length());
                strY-=height*1.5;
                if (i==2)i=0;
            }
        }

    }

    public void drawPlain(Batch batch)
    {
        resourcePool.getPool().getFont().setScale(scale);
        resourcePool.getPool().getFont().draw(batch,text,getX(),getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        if(multiline)
        {
            drawMultiline(batch);
        }
        else drawPlain(batch);
    }

    public void setScale(float scale)
    {
        this.scale=scale;
    }
}
