package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 06.09.14.
 */
public class FinalPopup extends Group
{
    public FinalPopup(int winner, int difficulty, int gameMode)
    {
        String w;

        float alignX=0.08f;

        TextureRegion background=resourcePool.getPool().getResource("background");
        TextureActor backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        addActor(backGround);

        if(gameMode==1)
        {
            if(winner==0)
            {
                w="You win!";
                if (difficulty==0)
                {

                    textActor title1=new textActor("Try to play in hard mode!",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.6f);
                    title1.setPosition(Gdx.graphics.getWidth() * alignX + title1.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.85f);
                    addActor(title1);

                    textActor close=new textActor("(Tap to close)",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.6f);
                    close.setPosition(Gdx.graphics.getWidth() * alignX + close.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.75f);
                    addActor(close);
                }
            }
            else
            {
                w="Computer win!";
                if(difficulty==1)
                {
                    textActor title1=new textActor("Try to play in easy mode!",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.6f);
                    title1.setPosition(Gdx.graphics.getWidth() * alignX + title1.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.85f);
                    addActor(title1);

                    textActor close=new textActor("(Tap to close)",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.6f);
                    close.setPosition(Gdx.graphics.getWidth() * alignX + close.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.75f);
                    addActor(close);
                }
            }
        }
        else
        {
            if(winner==0)
            {
                w="White wins!";
            }
            else
            {
                w="Blue wins!";
            }

            textActor close=new textActor("(Tap to close)",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.6f);
            close.setPosition(Gdx.graphics.getWidth() * alignX + close.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.85f);
            addActor(close);
        }

        textActor score=new textActor(w, Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.95f);
        addActor(score);

        addListener(new finalListener(this));

    }

    public class finalListener extends ClickListener
    {
        private FinalPopup parent;
        public finalListener(FinalPopup parent)
        {
            this.parent=parent;
        }


        @Override
        public void clicked(InputEvent event, float x, float y)
      {
            parent.remove();
            ChapayGame.getInstance().getGameData().endGame();
        }
    }
}
