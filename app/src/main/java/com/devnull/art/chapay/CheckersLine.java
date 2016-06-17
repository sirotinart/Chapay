package com.devnull.art.chapay;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Created by art on 22.08.14.
 */
public class CheckersLine
{
    private CheckersLine(){}
    static final int maxPosition=7;
    static final int minPosition=0;

    static void createLine(Group checkers, int position, int color) {
        SnapshotArray<Actor> actors = checkers.getChildren();

        for (int i = 0; i < 8; i++) {
            Vector2 cellCoordinate = UserInterface.getUI().getGameField().getBoard().getCellCoordinate(position, i);
            actors.get(i).setPosition(cellCoordinate.x, cellCoordinate.y);
        }
    }
}
