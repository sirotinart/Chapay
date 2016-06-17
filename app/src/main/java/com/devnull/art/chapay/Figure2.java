package com.devnull.art.chapay;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Created by art on 26.08.14.
 */
public class Figure2
{
    private Figure2(){}
    static final int maxPosition=6;
    static final int minPosition=1;

    static void createFigure(Group checkers, int position, int color)
    {
        SnapshotArray<Actor> actors=checkers.getChildren();

        if(color==0)
        {
            for(int i=0;i<8;i++)
            {
                Vector2 cellCoordinate;
                if(i%2==0)
                {
                    cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,i);

                }
                else
                {
                    cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-1,i);

                }
                actors.get(i).setPosition(cellCoordinate.x,cellCoordinate.y);
            }
        }
        else
        {
            for(int i=0;i<8;i++)
            {
                Vector2 cellCoordinate;
                if(i%2==0)
                {
                    cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,i);

                }
                else
                {
                    cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+1,i);

                }
                actors.get(i).setPosition(cellCoordinate.x,cellCoordinate.y);
            }
        }
    }
}
