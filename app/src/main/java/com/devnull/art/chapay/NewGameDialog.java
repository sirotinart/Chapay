package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 25.08.14.
 */
public class NewGameDialog extends Group
{
    public int mode;

    public NewGameDialog(int mode)
    {
        TextureActor background = new TextureActor(resourcePool.getPool().getResource("background2"));

        this.addActor(background);
        background.setHeight(background.getHeight());
        background.setWidth(background.getWidth());
        background.setPosition(Gdx.graphics.getWidth() / 2f - background.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - background.getHeight() / 2f);


        textActor head = new textActor("Resume last", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.8f, 1f);
        textActor head2=new textActor("game?",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.7f, 1f);

        addActor(background);
        addActor(head);
        addActor(head2);

        buttonActor yes = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Resume", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.5f, 0.8f);
        yes.addListener(new YesListener(this));

        buttonActor no = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "New Game", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.35f, 0.8f);
        no.addListener(new NoListener(this));


        addActor(yes);
        addActor(no);

        //buttonActor back = new buttonActor(resourcePool.getPool().getResource("button"),
        //        resourcePool.getPool().getResource("button_pressed"), "Back", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.2f, 0.8f);
        //back.addListener(new BackListener(this));

        //addActor(back);
        this.mode=mode;

    }

    public class YesListener extends ClickListener
    {
        NewGameDialog parent;

        public YesListener(NewGameDialog parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().loadGame(parent.mode);
            ChapayGame.getInstance().openGameField();
        }
    }

    public class NoListener extends ClickListener
    {
        NewGameDialog parent;

        public NoListener(NewGameDialog parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().newGame();
        }
    }

    public class BackListener extends ClickListener
    {
        NewGameDialog parent;

        public BackListener(NewGameDialog parent)
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
