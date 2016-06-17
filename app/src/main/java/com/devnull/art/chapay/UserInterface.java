package com.devnull.art.chapay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.PriorityQueue;

/**
 * Created by art on 30.07.14.
 */
public class UserInterface {
    protected mainMenu menu1;
    protected Player1StartDlg menu12;
    private aboutMenu menu2;
    private gameField menu3;
    protected Player2StartDlg menu22;

    private static UserInterface instance = new UserInterface();

    private UserInterface()
    {
        //menu1=new mainMenu();
    }

    public void close()
    {
        menu1.dispose();
    }

    public static UserInterface getUI()
    {
        return instance;
    }


    public mainMenu getMainMenu()
    {
        if (menu1 == null) {
            menu1 = new mainMenu();
        }
        return menu1;
    }

    public aboutMenu getAboutMenu()
    {
        if(menu2==null)
        {
            menu2=new aboutMenu();
        }
        return menu2;
    }

    public gameField getGameField()
    {
        if(menu3==null)
        {
            menu3=new gameField();
        }
        return menu3;
    }

    public Player1StartDlg getPlayer1StartDlg()
    {
        if (menu12==null)
        {
            menu12=new Player1StartDlg();
        }
        return menu12;
    }

    public Player2StartDlg getPlayer2StartDlg()
    {
        if(menu22==null)
        {
            menu22=new Player2StartDlg();
        }
        return menu22;
    }

    public void openPlayersDialog()
    {
        //PlayersDialog p=new PlayersDialog();
        NewGameDialog2 p=new NewGameDialog2();
        menu1.stage.addActor(p);
    }

    public void openFinalDialog(int mode, int difficulty, int gameMode)
    {
        FinalPopup p=new FinalPopup(mode, difficulty, gameMode);
        menu3.getStage().addActor(p);
    }

    public void openQuestionDialog()
    {
        BackMenu m=new BackMenu();
        menu3.getStage().addActor(m);
    }

    public void openSavedGameDialog(int mode)
    {
        NewGameDialog d=new NewGameDialog(mode);
        menu12.getStage().addActor(d);
    }

    public void openLevelEndDialog(int lvlScore, int lvlTime)
    {
        LevelEndDialog d=new LevelEndDialog(lvlScore, lvlTime);
        menu3.getStage().addActor(d);
    }

    public void openStageEndDialog(int stScore, int stTime, int lvlScore, int lvlTime)
    {
        StageEndDialog d=new StageEndDialog(stScore,stTime,lvlScore,lvlTime);
        menu3.getStage().addActor(d);
    }

    public void openStageEndDialog(int winner)
    {
        StageEndDialog d=new StageEndDialog(winner);
        menu3.getStage().addActor(d);
    }


}
