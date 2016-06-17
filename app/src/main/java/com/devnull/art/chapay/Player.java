package com.devnull.art.chapay;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Created by art on 17.08.14.
 */
public class Player
{
    protected Finger finger;
    protected FingerTouchListener listener;
    protected Group checkers;
    protected int playerColor; //0-white, 1-black;
    protected String color;

    public Player(String color)
    {
        if(color.compareTo("checker_gray")==0)
        {
            playerColor=0;
        }
        else
        {
            playerColor=1;
        }

        finger=new Finger(this);
        listener=new FingerTouchListener(finger);
        checkers=new Group();
        finger.addListener(listener);
        this.color=color;
    //UserInterface.getUI().getGameField().getStage().addActor(finger);
    }

    public void startStep()
    {
        finger.addListener(listener);
        UserInterface.getUI().getGameField().getStage().addActor(finger);
        finger.show();

    }

    public void stopStep()
    {
        finger.removeListener(listener);
        finger.hide();
        finger.remove();

    }

    public Group getCheckers()
    {
        checkers=new Group();
        for (int i=0; i<8; i++)
        {
            Checker c=new Checker(color, checkers);
            c.setUserObject(playerColor);
            checkers.addActor(c);
        }
        return checkers;
    }

    public FingerTouchListener getListener()
    {
        return listener;
    }

    public int getPlayerColor()
    {
        return playerColor;
    }

    public boolean hasMovingCheckers()
    {
        SnapshotArray<Actor> tmp=checkers.getChildren();

        for(int i=0; i<tmp.size;i++)
        {
            Checker tmpChecker;
            tmpChecker=(Checker)tmp.get(i);
            if(tmpChecker.isMoving())
            {
                return true;
            }
        }
        return false;
    }

    public int getCountOfCheckers()
    {
        return checkers.getChildren().size;
    }

    public SnapshotArray<Actor> getCheckersArray()
    {
        return checkers.getChildren();
    }

    public void delete()
    {
        finger.remove();
    }
}
