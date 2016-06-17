package com.devnull.art.chapay;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by art on 22.08.14.
 */
public class CheckerFiguresProvider
{
    private CheckerFiguresProvider(){}

    public static void createCheckers(Player p,int lvl, int pos)
    {
        Group A=p.getCheckers();
        switch (lvl)
        {
            case 0:
                CheckersLine.createLine(A,pos,p.getPlayerColor());
                break;
            case 1:
                Figure2.createFigure(A,pos,p.getPlayerColor());
                break;
            case 2:
                Figure3.createFigure(A,pos,p.getPlayerColor());
                break;
            case 3:
                Figure4.createFigure(A,pos,p.getPlayerColor());
                break;
            case 4:
                Figure5.createFigure(A,pos,p.getPlayerColor());
                break;
            case 5:
                Figure6.createFigure(A,pos,p.getPlayerColor());
                break;
            case 6:
                Figure7.createFigure(A,pos,p.getPlayerColor());
                break;
            case 7:
                Figure8.createFigure(A,pos,p.getPlayerColor());
                break;
            case 8:
                Figure9.createFigure(A,pos,p.getPlayerColor());
                break;
            case 9:
                Figure10.createFigure(A,pos,p.getPlayerColor());
                break;
            case 10:
                Figure11.createFigure(A,pos,p.getPlayerColor());
                break;
            case 11:
                Figure12.createFigure(A,pos,p.getPlayerColor());
                break;
            case 12:
                Figure13.createFigure(A,pos,p.getPlayerColor());
                break;
            case 13:
                Figure14.createFigure(A,pos,p.getPlayerColor());
                break;
            case 14:
                Figure15.createFigure(A,pos,p.getPlayerColor());
                break;
            case 15:
                Figure16.createFigure(A,pos,p.getPlayerColor());
                break;
            case 16:
                Figure17.createFigure(A,pos,p.getPlayerColor());
                break;
            case 17:
                Figure18.createFigure(A,pos,p.getPlayerColor());
                break;
            case 18:
                Figure19.createFigure(A,pos,p.getPlayerColor());
                break;
            case 19:
                break;
        }

        UserInterface.getUI().getGameField().getStage().addActor(A);
    }

    public static boolean checkMaxPosition(int lvl, int pos)
    {
        switch (lvl)
        {
            case 0:
            {
                return pos <= CheckersLine.maxPosition;
            }
            case 1:
            {
                return pos <= Figure2.maxPosition;
            }
            case 2:
            {
                return pos <= Figure3.maxPosition;
            }
            case 3:
            {
                return pos <= Figure4.maxPosition;
            }
            case 4:
            {
                return pos <= Figure5.maxPosition;
            }
            case 5:
            {
                return pos <= Figure6.maxPosition;
            }
            case 6:
            {
                return pos <= Figure7.maxPosition;
            }
            case 7:
            {
                return pos<= Figure8.maxPosition;
            }
            case 8:
            {
                return pos <= Figure9.maxPosition;
            }
            case 9:
            {
                return pos <= Figure10.maxPosition;
            }
            case 10:
            {
                return pos <= Figure11.maxPosition;
            }
            case 11:
            {
                return pos <= Figure12.maxPosition;
            }
            case 12:
            {
                return pos <= Figure13.maxPosition;
            }
            case 13:
            {
                return pos <= Figure14.maxPosition;
            }
            case 14:
            {
                return pos <= Figure15.maxPosition;
            }
            case 15:
            {
                return pos <= Figure16.maxPosition;
            }
            case 16:
            {
                return pos <= Figure17.maxPosition;
            }
            case 17:
            {
                return pos <= Figure18.maxPosition;
            }
            case 18:
            {
                return pos <= Figure19.maxPosition;
            }

        }
        return false;
    }
    public static boolean checkMinPosition(int lvl, int pos)
    {
        switch (lvl)
        {
            case 0:
            {
                return pos >= CheckersLine.minPosition;
            }
            case 1:
            {

                return pos >= Figure2.minPosition;
            }
            case 2:
            {
                return pos >= Figure3.minPosition;
            }
            case 3:
            {
                return pos >= Figure4.minPosition;
            }
            case 4:
            {
                return pos >= Figure5.minPosition;
            }
            case 5:
            {
                return pos >= Figure6.minPosition;
            }
            case 6:
            {
                return pos >= Figure7.minPosition;
            }
            case 7:
            {
                return pos >= Figure8.minPosition;
            }
            case 8:
            {
                return pos >= Figure9.minPosition;
            }
            case 9:
            {
                return pos >= Figure10.minPosition;
            }
            case 10:
            {
                return pos >= Figure11.minPosition;
            }
            case 11:
            {
                return pos >= Figure12.minPosition;
            }
            case 12:
            {
                return pos >= Figure13.minPosition;
            }
            case 13:
            {
                return pos >= Figure14.minPosition;
            }
            case 14:
            {
                return pos >= Figure15.minPosition;
            }
            case 15:
            {
                return pos >= Figure16.minPosition;
            }
            case 16:
            {
                return pos >= Figure17.minPosition;
            }
            case 17:
            {
                return pos >= Figure18.minPosition;
            }
            case 18:
            {
                return pos >= Figure19.minPosition;
            }
        }
        return false;
    }

    public static int getMaxPosition(int lvl)
    {
        switch (lvl)
        {
            case 0:
            {
                return CheckersLine.maxPosition;
            }
            case 1:
            {
                return Figure2.maxPosition;
            }
            case 2:
            {
                return Figure3.maxPosition;
            }
            case 3:
            {
                return Figure4.maxPosition;
            }
            case 4:
            {
                return Figure5.maxPosition;
            }
            case 5:
            {
                return Figure6.maxPosition;
            }
            case 6:
            {
                return Figure7.maxPosition;
            }
            case 7:
            {
                return Figure8.maxPosition;
            }
            case 8:
            {
                return Figure9.maxPosition;
            }
            case 9:
            {
                return Figure10.maxPosition;
            }
            case 10:
            {
                return Figure11.maxPosition;
            }
            case 11:
            {
                return Figure12.maxPosition;
            }
            case 12:
            {
                return Figure13.maxPosition;
            }
            case 13:
            {
                return Figure14.maxPosition;
            }
            case 14:
            {
                return Figure15.maxPosition;
            }
            case 15:
            {
                return Figure16.maxPosition;
            }
            case 16:
            {
                return Figure17.maxPosition;
            }
            case 17:
            {
                return Figure18.maxPosition;
            }
            case 18:
            {
                return Figure19.maxPosition;
            }
        }
        return 0;
    }

    public static int getMinPosition(int lvl)
    {
        switch (lvl)
        {
            case 0:
            {
                return CheckersLine.minPosition;
            }
            case 1:
            {
                return Figure2.minPosition;
            }
            case 2:
            {
                return Figure3.minPosition;
            }
            case 3:
            {
                return Figure4.minPosition;
            }
            case 4:
            {
                return Figure5.minPosition;
            }
            case 5:
            {
                return Figure6.minPosition;
            }
            case 6:
            {
                return Figure7.minPosition;
            }
            case 7:
            {
                return Figure8.minPosition;
            }
            case 8:
            {
                return Figure9.minPosition;
            }
            case 9:
            {
                return Figure10.minPosition;
            }
            case 10:
            {
                return Figure11.minPosition;
            }
            case 11:
            {
                return Figure12.minPosition;
            }
            case 12:
            {
                return Figure13.minPosition;
            }
            case 13:
            {
                return Figure14.minPosition;
            }
            case 14:
            {
                return Figure15.minPosition;
            }
            case 15:
            {
                return Figure16.minPosition;
            }
            case 16:
            {
                return Figure17.minPosition;
            }
            case 17:
            {
                return Figure18.minPosition;
            }
            case 18:
            {
                return Figure19.minPosition;
            }
        }
        return 0;
    }

    public static int getMaxLevel()
    {
        return 19;
    }
}
