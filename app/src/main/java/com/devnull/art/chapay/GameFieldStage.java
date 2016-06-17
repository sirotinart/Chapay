package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by art on 20.08.14.
 */
public class GameFieldStage extends Stage
{
    private boolean needSwitch;
    private boolean dialogOpened=false;

    public GameFieldStage()
    {
        super(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                ChapayGame.getInstance().getCamera()),ChapayGame.getInstance().getBatch());
        needSwitch=false;


    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(needSwitch)
        {
            boolean allow = true;
            if(ChapayGame.getInstance().getGameData().getPlayerA().hasMovingCheckers()
                    ||ChapayGame.getInstance().getGameData().getPlayerB().hasMovingCheckers())
            {
                allow=false;
            }

            if(allow)
            {
                needSwitch=false;
                ChapayGame.getInstance().getGameData().switchPlayer();

            }
        }

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);

        return true;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if(keyCode== Input.Keys.BACK&&dialogOpened==false)
        {
            //ChapayGame.getInstance().getGameData().stopGame();
            dialogOpened=true;
            ChapayGame.getInstance().getGameData().setPause(true);
            UserInterface.getUI().openQuestionDialog();

        }
        return super.keyDown(keyCode);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        needSwitch=false;
    }

    public void needSwitchPlayer()
    {
        needSwitch=true;
    }

    public void dialogClosed()
    {
        dialogOpened=false;
    }
}
