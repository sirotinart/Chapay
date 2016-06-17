package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 17.09.14.
 */
public class NewGameDialog2 extends Group
{
    RadioSwitch sw1;
    RadioSwitch playerSwitch;
    RadioSwitch stepSwitch;

    public NewGameDialog2()
    {
        TextureActor background = new TextureActor(resourcePool.getPool().getResource("background2"));

        this.addActor(background);
        background.setHeight(background.getHeight());
        background.setWidth(background.getWidth());
        background.setPosition(Gdx.graphics.getWidth() / 2f - background.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - background.getHeight() / 2f);

        textActor head =new textActor("New game",Gdx.graphics.getWidth()*0.5f,Gdx.graphics.getHeight()*0.9f,0.6f);
        addActor(head);

        String[] a=new String[2];
        a[0]="2 players";
        a[1]="1 player";
        playerSwitch=new RadioSwitch(2, a,Gdx.graphics.getWidth()*0.2f, Gdx.graphics.getHeight()*0.775f);
        addActor(playerSwitch);

        textActor middle =new textActor("First step",Gdx.graphics.getWidth()*0.5f,Gdx.graphics.getHeight()*0.68f,0.6f);
        addActor(middle);

        a[0]="White";
        a[1]="Blue";
        sw1=new RadioSwitch(2,a,Gdx.graphics.getWidth()*0.2f,Gdx.graphics.getHeight()*0.555f);
        addActor(sw1);

        textActor bottom =new textActor("Steps order",Gdx.graphics.getWidth()*0.5f,Gdx.graphics.getHeight()*0.46f,0.6f);
        addActor(bottom);

        a[0]="Step by step";
        a[1]="Classic";
        stepSwitch=new RadioSwitch(2,a,Gdx.graphics.getWidth()*0.2f, Gdx.graphics.getHeight()*0.335f);
        addActor(stepSwitch);


        buttonActor start = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Start", Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() * 0.15f, 0.5f,0.5f,0.75f);
        start.addListener(new startListener(this));
        addActor(start);

        buttonActor back = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Back", Gdx.graphics.getWidth() *2 / 3f, Gdx.graphics.getHeight() * 0.15f, 0.5f,0.5f,0.75f);
        back.addListener(new backListener(this));
        addActor(back);


    }

    public class startListener extends ClickListener
    {
        NewGameDialog2 parent;

        public startListener(NewGameDialog2 parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().setGameMode(playerSwitch.getCurrentItem());
            ChapayGame.getInstance().getGameData().setFirstStep(sw1.getCurrentItem());
            ChapayGame.getInstance().getGameData().setStepOrder(stepSwitch.getCurrentItem());
            ChapayGame.getInstance().openGameField();
        }
    }

    public class backListener extends ClickListener
    {
        NewGameDialog2 parent;

        public backListener(NewGameDialog2 parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            parent.remove();
        }
    }

}
