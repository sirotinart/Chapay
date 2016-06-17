package com.devnull.art.chapay;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Created by art on 13.09.14.
 */
public class Figure11
{
    private Figure11(){}
    static final int maxPosition=5;
    static final int minPosition=2;

    static void createFigure(Group checkers, int position, int color) {
        SnapshotArray<Actor> actors = checkers.getChildren();

        if (color == 0)
        {
            Vector2 cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-2,3);
            actors.get(0).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-2,4);
            actors.get(1).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-1,2);
            actors.get(2).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-1,3);
            actors.get(3).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-1,4);
            actors.get(4).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position-1,5);
            actors.get(5).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,3);
            actors.get(6).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,4);
            actors.get(7).setPosition(cellCoordinate.x,cellCoordinate.y);
        }
        else
        {
            Vector2 cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+2,3);
            actors.get(0).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+2,4);
            actors.get(1).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+1,2);
            actors.get(2).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+1,3);
            actors.get(3).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+1,4);
            actors.get(4).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position+1,5);
            actors.get(5).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,3);
            actors.get(6).setPosition(cellCoordinate.x,cellCoordinate.y);

            cellCoordinate=UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position,4);
            actors.get(7).setPosition(cellCoordinate.x,cellCoordinate.y);
        }
    }
}
