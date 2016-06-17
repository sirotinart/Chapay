package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 11.11.15.
 */
public class StageEndDialog extends Group
{
    StageEndDialog(int stScore, int stTime, int lvlScore, int lvlTime)
    {
        float alignX=0.16f;

        TextureRegion background=resourcePool.getPool().getResource("background");
        TextureActor backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        addActor(backGround);
        textActor score=new textActor("Stage score:", Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.95f);
        addActor(score);

        textActor score1=new textActor(String.valueOf(stScore), Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score1.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.85f);
        addActor(score1);

        textActor time=new textActor("Stage time:",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        time.setPosition(Gdx.graphics.getWidth()*alignX+time.getWidth()*0.5f,Gdx.graphics.getHeight()*0.75f);
        addActor(time);

        textActor time1=new textActor(String.valueOf(stTime),Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        time1.setPosition(Gdx.graphics.getWidth()*alignX+time.getWidth()*0.5f,Gdx.graphics.getHeight()*0.65f);
        addActor(time1);

        textActor score12=new textActor("Level score:", Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score12.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.55f);
        addActor(score12);

        textActor score11=new textActor(String.valueOf(lvlScore+stScore), Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score11.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.45f);
        addActor(score11);

        textActor time12=new textActor("Level time:",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        time12.setPosition(Gdx.graphics.getWidth()*alignX+time.getWidth()*0.5f,Gdx.graphics.getHeight()*0.35f);
        addActor(time12);

        textActor time11=new textActor(String.valueOf(lvlTime+stTime),Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        time11.setPosition(Gdx.graphics.getWidth() * alignX + time.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.25f);
        addActor(time11);

        buttonActor next = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Next", Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() * 0.05f, 0.5f,0.5f,0.75f);
        next.addListener(new NextBtnListener());
        addActor(next);

        buttonActor replay = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Replay", Gdx.graphics.getWidth() *3 / 4f, Gdx.graphics.getHeight() * 0.05f, 0.5f,0.5f,0.75f);
        replay.addListener(new ReplayBtnListener());
        addActor(replay);

//        buttonActor back = new buttonActor(resourcePool.getPool().getResource("button"),
//                resourcePool.getPool().getResource("button_pressed"), "Back", Gdx.graphics.getWidth() *2 / 3f, Gdx.graphics.getHeight() * 0.05f, 0.5f,0.5f,0.75f);
//        back.addListener(new BackBtnListener());
//        addActor(back);
    }

    StageEndDialog(int winner)
    {
        textActor title;
        float alignX=0.16f;

        TextureRegion background=resourcePool.getPool().getResource("background");
        TextureActor backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        addActor(backGround);

        if(winner==0)
        {
            title=new textActor("White wins!",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);

        }
        else
        {
            title=new textActor("Blue wins!",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);

        }

        title.setPosition(Gdx.graphics.getWidth()*alignX+title.getWidth()*0.5f,Gdx.graphics.getHeight()*0.75f);
        addActor(title);

        buttonActor next = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Next", Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() * 0.05f, 0.5f,0.5f,0.75f);
        next.addListener(new NextBtnListener());
        addActor(next);

        buttonActor replay = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Replay", Gdx.graphics.getWidth() *2 / 3f, Gdx.graphics.getHeight() * 0.05f, 0.5f,0.5f,0.75f);
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
            ChapayGame.getInstance().getGameData().nextLevel();
        }
    }

    class ReplayBtnListener extends ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            remove();
            ChapayGame.getInstance().getGameData().restartStage();
        }
    }
}
