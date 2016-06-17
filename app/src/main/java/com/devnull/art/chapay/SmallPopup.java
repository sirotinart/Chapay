package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 22.08.14.
 */
public class SmallPopup extends Group
{
    private textActor text;
    private TextureActor background;

    public SmallPopup(String s)
    {
        background=new TextureActor(resourcePool.getPool().getResource("background2"));

        this.addActor(background);
        background.setHeight(background.getHeight()*0.7f);
        background.setWidth(background.getWidth()*0.7f);
        background.setPosition(Gdx.graphics.getWidth()/2f-background.getWidth()/2f,
                Gdx.graphics.getHeight()/2f-background.getHeight()/2f);


        text=new textActor(s,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.5f, 1f);

        addActor(background);
        addActor(text);
        addListener(new PopupTouchListener());
    }

    public void setText(String s)
    {
        text.changeText(s);
    }
    class PopupTouchListener extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            remove();
            ChapayGame.getInstance().getGameData().nextLevel();
        }
    }

}
