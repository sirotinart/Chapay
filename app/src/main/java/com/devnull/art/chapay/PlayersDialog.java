package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by art on 27.08.14.
 */
public class PlayersDialog extends Group
{


    public PlayersDialog()
    {
        TextureActor background = new TextureActor(resourcePool.getPool().getResource("background2"));

        this.addActor(background);
        background.setHeight(background.getHeight());
        background.setWidth(background.getWidth());
        background.setPosition(Gdx.graphics.getWidth() / 2f - background.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - background.getHeight() / 2f);

        addActor(background);

        buttonActor player1 = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "1 player", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.75f, 0.8f);
        player1.addListener(new p1Listener(this));

        buttonActor player2 = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "2 players", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.5f, 0.8f);
        player2.addListener(new p2Listener(this));

        buttonActor back = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Back", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.25f, 0.8f);
        back.addListener(new backListener(this));

        addActor(back);

        addActor(player1);
        addActor(player2);
    }

    public class p1Listener extends ClickListener
    {
        private PlayersDialog parent;

        public p1Listener(PlayersDialog parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().setGameMode(1);
            ChapayGame.getInstance().openGameField();
        }
    }

    public class p2Listener extends ClickListener
    {
        private PlayersDialog parent;

        public p2Listener(PlayersDialog parent)
        {
            this.parent=parent;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            parent.remove();
            ChapayGame.getInstance().getGameData().setGameMode(0);
            ChapayGame.getInstance().openGameField();
        }
    }

    public class backListener extends ClickListener
    {
        PlayersDialog parent;

        public backListener(PlayersDialog parent)
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
