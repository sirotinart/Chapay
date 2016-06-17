package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 21.08.14.
 */
public class DialogStage extends Stage
{
    private Group stageGroup;
    private buttonActor yes;
    private buttonActor no;
    private TextureActor background;
    private textActor request;

    public DialogStage(String request)
    {
        stageGroup=new Group();
        stageGroup.setPosition(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f);
        background=new TextureActor(resourcePool.getPool().getResource("background2"));

        this.addActor(background);
        background.setPosition(Gdx.graphics.getWidth()/2f-background.getWidth()/2f,
                Gdx.graphics.getHeight()/2f-background.getHeight()/2f);

        this.request=new textActor(request,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.75f, 0.5f);

        this.addActor(this.request);

        yes=new buttonActor(resourcePool.getPool().getResource("button"),
                            resourcePool.getPool().getResource("button_pressed"),
                            "Yes",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.5f,0.45f);
        no=new buttonActor(resourcePool.getPool().getResource("button"),
                            resourcePool.getPool().getResource("button_pressed"),
                            "No",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.3f,0.45f);
        no.addListener(new NoButtonListener());

        this.addActor(yes);
        this.addActor(no);

        //this.addActor(stageGroup);
    }


    public class YesButtonListener extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            ChapayGame.getInstance().getGameData().nextLevel();
        }
    }

    public class NoButtonListener extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            UserInterface.getUI().getGameField().close();
        }
    }
}
