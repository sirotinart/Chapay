package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 11.11.15.
 */
public class LevelEndDialog extends Group
{
    LevelEndDialog(int lvlScore, int lvlTime)
    {
        float alignX=0.16f;

        TextureRegion background=resourcePool.getPool().getResource("background");
        TextureActor backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        addActor(backGround);
        textActor score=new textActor("Level score:", Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.95f);
        addActor(score);

        textActor time=new textActor("Level time:",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        time.setPosition(Gdx.graphics.getWidth()*alignX+time.getWidth()*0.5f,Gdx.graphics.getHeight()*0.75f);
        addActor(time);

        textActor score1=new textActor(String.valueOf(lvlScore), Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score1.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.85f);
        addActor(score1);

        textActor time1=new textActor(String.valueOf(lvlTime),Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        time1.setPosition(Gdx.graphics.getWidth()*alignX+time.getWidth()*0.5f,Gdx.graphics.getHeight()*0.65f);
        addActor(time1);


        buttonActor next = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Start", Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() * 0.05f, 0.5f,0.5f,0.75f);
        next.addListener(new NextBtnListener());
        addActor(next);

        buttonActor replay = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Back", Gdx.graphics.getWidth() *3 / 4f, Gdx.graphics.getHeight() * 0.05f, 0.5f,0.5f,0.75f);
        replay.addListener(new ReplayBtnListener());
        addActor(replay);
    }

    class NextBtnListener extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            remove();
            //ChapayGame.getInstance().getGameData().nextLevel();
            ChapayGame.getInstance().getGameData().saveStat();

        }
    }

    class ReplayBtnListener extends ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            remove();
            ChapayGame.getInstance().getGameData().restartLevel();
        }
    }
}
