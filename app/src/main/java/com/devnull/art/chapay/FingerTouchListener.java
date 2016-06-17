package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 17.08.14.
 */
public class FingerTouchListener extends ClickListener
{
    private Finger parent;
    private Vector2 up;

    public FingerTouchListener(Finger parent)
    {
        this.parent=parent;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        super.touchDown(event, x, y, pointer, button);
        if(pointer==0)
        {
            up=new Vector2(Gdx.input.getX(pointer),Gdx.graphics.getHeight()-Gdx.input.getY(pointer));
            parent.setPosition(Gdx.input.getX(pointer),Gdx.graphics.getHeight()-Gdx.input.getY(pointer));
        }
        //parent.show();
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
        if(pointer==0)
        {
            up = new Vector2(Gdx.input.getX(pointer), Gdx.graphics.getHeight() - Gdx.input.getY(pointer));
            parent.move();
        }
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        //parent.hide();
    }

    public Vector2 getUp()
    {
        return up;
    }
}
