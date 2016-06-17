package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by art on 17.08.14.
 */
public class Board extends Actor
{
    private TextureRegion tex;
    private Vector2[][] cells=new Vector2[8][8];


    public Board()
    {
        tex=resourcePool.getPool().getResource("board");
        setWidth(tex.getRegionWidth()*myDeviceScreen.getAlternateScale());
        setHeight(tex.getRegionHeight()*myDeviceScreen.getAlternateScale());
        setPosition((Gdx.graphics.getWidth() - getWidth()) / 2f, Gdx.graphics.getHeight()/2f -getHeight()/2f);
        calculateCellsCoordinates();
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        batch.draw(tex, getX(), getY(),getOriginX(),getOriginY(), getWidth(), getHeight(),getScaleX(),getScaleY(),getRotation());
    }


   private void calculateCellsCoordinates()
   {
       for (int i=0;i<8;i++)
       {

           for(int j=0; j<8; j++)
           {
               cells[i][j]=new Vector2();
           }

       }
       //cells=new Vector2[8][8];
       float borderWidth=32*myDeviceScreen.getConstantScale();
       float boardWidth=getWidth()-2*borderWidth;
       float delta=boardWidth/8f;

       float startX, startY;
       startX=getX()+borderWidth+delta/2f;
       startY=getY()+borderWidth+delta/2f;

       float x,y;
       x=startX;
       y=startY;
       for (int i=0;i<8;i++)
       {

           for(int j=0; j<8; j++)
           {
               cells[i][j].set(x,y);
               x+=delta;
           }
           y+=delta;
           x=startX;
       }
   }

   public Vector2 getCellCoordinate(int i, int j)
   {
       return cells[i][j];
   }

   public boolean isOnBoard(Vector2 position)
   {
       Vector2 tmp=new Vector2();
       tmp.x=position.x-getX();
       tmp.y=position.y-getY();
       if(tmp.x>=0 && tmp.y>=0 && tmp.x<=getWidth() && tmp.y<=getHeight())
       {
           return true;
       }
       else return false;
   }

   public boolean isOnField(Vector2 tap)
   {
       if(tap.y>0 && tap.y<Gdx.graphics.getHeight()*0.95f)
           return true;
       else
           return false;
   }

   public Vector2 getNearestEdge(Vector2 sp,Vector2 ep)
   {
       float h1,h2,v1,v2;
       Vector2 res=new Vector2();
       v1=getX()/myDeviceScreen.BOX_WORLD_TO;
       v2=(getX()+getWidth())/myDeviceScreen.BOX_WORLD_TO;
       h1=getY()/myDeviceScreen.BOX_WORLD_TO;
       h2=(getY()+getHeight())/myDeviceScreen.BOX_WORLD_TO;

       if(ep.x-sp.x==0)
       {
           res.x=ep.x;
           if(ep.y-sp.y>0&&h2-ep.y>0)
           {
               res.y=h2;
           }
           else
           {
               res.y=h1;
           }
       }
       else
       {
           if(ep.y-sp.y==0)
           {
               res.y=ep.y;
               if(ep.x-sp.x>0&&v2-ep.x>0)
               {
                   res.x=v2;
               }
               else
               {
                   res.y=v1;
               }
           }
           else
           {
               Vector2 p1=new Vector2();
               Vector2 p2=new Vector2();
               if(ep.x-sp.x>0&&ep.y-sp.y>0)
               {
                   p1.x=v2;
                   p2.y=h2;
               }
               if(ep.x-sp.x>0&&ep.y-sp.y<0)
               {
                   p1.x=v2;
                   p2.y=h1;
               }
               if(ep.x-sp.x<0&&ep.y-sp.y>0)
               {
                   p1.x=v1;
                   p2.y=h2;
               }
               if(ep.x-sp.x<0&&ep.y-sp.y<0)
               {
                   p1.x=v1;
                   p2.y=h1;
               }

               p1.y=((p1.x-sp.x)/(ep.x-sp.x))*(ep.y-sp.y)+sp.y;
               p2.x=((p2.y-sp.y)/(ep.y-sp.y))*(ep.x-sp.x)+sp.x;

               float r1,r2;
               r1=(float)Math.sqrt((p1.x-ep.x)*(p1.x-ep.x)+(p1.y-ep.y)*(p1.y-ep.y));
               r2=(float)Math.sqrt((p2.x-ep.x)*(p2.x-ep.x)+(p2.y-ep.y)*(p2.y-ep.y));
               if(r1<r2)
               {
                   res=p1;
               }
               else
               {
                   res=p2;
               }
           }
       }
       return res;
   }

}
