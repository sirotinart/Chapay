package com.devnull.art.chapay;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Created by art on 26.08.14.
 */
public class Figure3
{
    private Figure3(){}
    static final int maxPosition=6;
    static final int minPosition=1;

    static void createFigure(Group checkers, int position, int color)
    {
        SnapshotArray<Actor> actors=checkers.getChildren();

        if(color==0)
        {
            Vector2 cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-1,2);
            actors.get(0).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-1,5);
            actors.get(1).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,1);
            actors.get(2).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,2);
            actors.get(3).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,3);
            actors.get(4).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,4);
            actors.get(5).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,5);
            actors.get(6).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,6);
            actors.get(7).setPosition(cellCoordinate.x,cellCoordinate.y);

        }
        else
        {
            Vector2 cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+1,2);
            actors.get(0).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+1,5);
            actors.get(1).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,1);
            actors.get(2).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,2);
            actors.get(3).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,3);
            actors.get(4).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,4);
            actors.get(5).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,5);
            actors.get(6).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,6);
            actors.get(7).setPosition(cellCoordinate.x,cellCoordinate.y);
        }
    }
}
