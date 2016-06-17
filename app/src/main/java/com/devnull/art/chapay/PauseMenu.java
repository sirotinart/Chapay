package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 23.08.14.
 */
public class PauseMenu extends Group
{
    private textActor head;
    private TextureActor background;
    private buttonActor resume;
    private buttonActor restart;
    private buttonActor exit;

    public PauseMenu()
    {
        background=new TextureActor(resourcePool.getPool().getResource("background"));

        this.addActor(background);
        background.setHeight(background.getHeight());
        background.setWidth(background.getWidth());
        background.setPosition(Gdx.graphics.getWidth()/2f-background.getWidth()/2f,
                Gdx.graphics.getHeight()/2f-background.getHeight()/2f);


        head=new textActor("Pause",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.8f, 1f);

        addActor(background);
        addActor(head);

        resume=new buttonActor(resourcePool.getPool().getResource("button"),
                                 resourcePool.getPool().getResource("button_pressed"),"Resume",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.5f,0.8f);
        resume.addListener(new ResumeListener(this));

        restart=new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"),"Restart",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.35f,0.8f);
        restart.addListener(new RestartListener(this));
        exit=new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"),"Exit",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.2f,0.8f);

        exit.addListener(new ExitListener(this));

        addActor(restart);
        addActor(resume);
        addActor(exit);


    }

    public class  ResumeListener extends ClickListener
    {
        PauseMenu parent;

        public ResumeListener(PauseMenu parent)
        {
            this.parent=parent;
        }


        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().setPause(false);
        }
    }

    public class RestartListener extends ClickListener
    {
        PauseMenu parent;

        public RestartListener(PauseMenu parent)
        {
            this.parent=parent;

        }


        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().restartStage();
        }
    }

    public class ExitListener extends ClickListener
    {
        PauseMenu parent;

        public ExitListener(PauseMenu parent)
        {
            this.parent=parent;

        }


        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().stopGame();
        }
    }

}
