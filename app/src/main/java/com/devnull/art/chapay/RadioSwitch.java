package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by art on 17.09.14.
 */
public class RadioSwitch extends Group
{
    RadioButton[] buttons;
    private int currentItem;


    public RadioSwitch(int count, String[] items, float x, float y)
    {
        setPosition(x,y);
        buttons=new RadioButton[count];
        int j=count-1;
        for (int i=0; i<count; i++)
        {
            buttons[i]=new RadioButton(items[i]);
            buttons[i].setPosition(0,buttons[i].getHeight()*j); //todo: set position
            buttons[i].addListener(new RadioButtonListener(buttons,i, count));
            addActor(buttons[i]);

            j-=2;
        }
        buttons[0].press();
        currentItem=0;
    }

    public RadioSwitch(String[] items, float x, float y)
    {
        setPosition(x,y);
        buttons=new RadioButton[2];


        buttons[0]=new RadioButton(items[0]);
        buttons[0].setPosition(0,0); //todo: set position
        buttons[0].addListener(new RadioButtonListener(buttons,0, 2));
        addActor(buttons[0]);

        buttons[1]=new RadioButton(items[1]);
        buttons[1].setPosition(Gdx.graphics.getWidth()*0.32f,0); //todo: set position
        buttons[1].addListener(new RadioButtonListener(buttons,1, 2));
        addActor(buttons[1]);

        buttons[0].press();
        currentItem=0;
    }

    public class RadioButtonListener extends ClickListener
    {

        private RadioButton[] buttons;
        private int number;
        private int count;

        public RadioButtonListener(RadioButton[] buttons,int arrayNum, int count)
        {
            this.buttons=buttons;
            this.count=count;
            number=arrayNum;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            buttons[number].press();
            currentItem=number;

            for (int i=0; i<count; i++)
            {
                if(i!=number)
                {
                    buttons[i].unPress();
                }
            }
        }

    }

    public int getCurrentItem()
    {
        return currentItem;
    }


}
