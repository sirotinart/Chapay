package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 17.09.14.
 */
public class BackMenu extends Group
{
    public BackMenu()
    {
        TextureActor background = new TextureActor(resourcePool.getPool().getResource("background"));

        this.addActor(background);
        background.setHeight(background.getHeight());
        background.setWidth(background.getWidth());
        background.setPosition(Gdx.graphics.getWidth() / 2f - background.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - background.getHeight() / 2f);


        textActor head = new textActor("Exit to main", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.8f, 1f);
        textActor head2=new textActor("menu?",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.7f, 1f);

        addActor(background);
        addActor(head);
        addActor(head2);

        buttonActor yes = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Yes", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.35f, 0.8f);
        yes.addListener(new YesListener(this));

        buttonActor no = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "No", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.5f, 0.8f);
        no.addListener(new NoListener(this));


        addActor(yes);
        addActor(no);



    }

    public class YesListener extends ClickListener
    {
        BackMenu parent;

        public YesListener(BackMenu parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            parent.remove();
            UserInterface.getUI().getGameField().getStage().dialogClosed();
            ChapayGame.getInstance().getGameData().stopGame();

        }
    }

    public class NoListener extends ClickListener
    {
        BackMenu parent;

        public NoListener(BackMenu parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            parent.remove();
            UserInterface.getUI().getGameField().getStage().dialogClosed();
            ChapayGame.getInstance().getGameData().setPause(false);
        }
    }
}
