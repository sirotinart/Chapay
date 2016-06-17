package com.devnull.art.chapay;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.GridLayout;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by art on 17.08.14.
 */
public class GameData
{


    private Player playerA, playerB;
    private int step; //0- white, 1-black
    private int levelA, levelB,positionA,positionB;
    private int gameMode;//0-human vs human, 1-human vs ai;
    private CheckerController controller;
    private int win;
    private int levelStartStep;
    private boolean pause;
    private int stepOrder;
    private int difficulty; //0-easy, 1-hard
    int score, time;
    int stageScore, stageTime;
    long stepStartTime;


    private DbManager dbManager;

    public void startGame(GameModeData gmd)
    {
        gameMode=gmd.gameMode;
        levelStartStep=gmd.firstStep;
        step=gmd.firstStep;
        stepOrder=gmd.stepOrder;
        difficulty=gmd.difficulty;
        stageTime=0;
        score=0;
        time=0;
        positionA=0;
        positionB=7;
        levelA=0;
        levelB=0;

        SQLiteDatabase db=dbManager.getReadableDatabase();

        db.delete(DbManager.STAT_TABLE, null, null);
        db.delete(DbManager.SG1P_TABLE, null, null);
    }

    public void restartLevel()
    {

    }


    public class CheckerController
    {
        public int startA,startB,stopA, stopB;
    }


    public GameData()
    {
        MainActivity mainActivity=(MainActivity)ChapayGame.getInstance().getResolver();
        dbManager=new DbManager(mainActivity.getContext(),"chapay_stat",null,DbManager.DATABASE_VERSION);

        step=0;
        levelA=0;
        levelB=0;
        positionA=0;
        positionB=7;
        pause=false;
        win=-2;
        stageTime=0;
    }


    public void createPlayers()
    {
        playerA=new Player("checker_gray");
        if(gameMode==0)
        {
            playerB=new Player("checker_blue");
        }
        else
        {
            playerB=new AiPlayer("checker_blue",difficulty);
        }
        CheckerFiguresProvider.createCheckers(playerA, levelA,positionA);
        CheckerFiguresProvider.createCheckers(playerB, levelB,positionB);
        controller=new CheckerController();
    }


    public void initializeStep()
    {

        levelStartStep=step;

        if(step==0)
        {
            UserInterface.getUI().getGameField().setStep(0);
            stepStartTime=System.currentTimeMillis()/1000;
        }
        else
        {
            UserInterface.getUI().getGameField().setStep(1);
        }
        continueStep();

    }

    public int getStep()
    {
        return step;
    }

    public void switchPlayer()
    {
        boolean needSwitch=false;
        controller.stopA=playerA.getCountOfCheckers();
        controller.stopB=playerB.getCountOfCheckers();


        if(controller.stopB!=0&&controller.stopA!=0)
        {
            switch (step)
            {
                case 0:
                {
                    if(stepOrder==0)
                    {
                        if(controller.stopA-controller.startA<0)
                        {
                            needSwitch=true;
                        }
                        else
                        if(controller.stopA-controller.startA==0 && controller.stopB-controller.startB==0)
                        {
                            needSwitch=true;
                        }
                    }
                    else
                    {
                        needSwitch=true;
                    }
                    break;
                }

                case 1:
                {
                    if(stepOrder==0)
                    {
                        if(controller.stopB-controller.startB<0)
                        {
                            needSwitch=true;
                        }
                        else
                        if(controller.stopB-controller.startB==0 && controller.stopA-controller.startA==0)
                        {
                            needSwitch=true;
                        }
                    }
                    else
                    {
                        needSwitch=true;
                    }
                    break;
                }
            }

            if(needSwitch)
            {
                startNewStep();
            }
            else
            {
                continueStep();
            }
        }
        else
        {
            battleEnded();
        }

    }

    public void stopCurrentStep()
    {

        if(step==0)
        {
            playerA.stopStep();
        }
        else
        {
            playerB.stopStep();
        }
    }

    public void startNewStep()
    {
        controller.startA=playerA.getCountOfCheckers();
        controller.startB=playerB.getCountOfCheckers();



        if(step==0)
        {
            UserInterface.getUI().getGameField().setStep(1);
            playerB.startStep();
            step=1;
            stageTime+=System.currentTimeMillis()/1000-stepStartTime;
            Log.d("lol", String.valueOf(System.currentTimeMillis() / 1000 - stepStartTime));
        }
        else
        {
            UserInterface.getUI().getGameField().setStep(0);
            playerA.startStep();
            step=0;
            stepStartTime=System.currentTimeMillis()/1000;
        }
    }

    public void continueStep()
    {
        controller.startA=playerA.getCountOfCheckers();
        controller.startB=playerB.getCountOfCheckers();

        if(step==1)
        {
            UserInterface.getUI().getGameField().setStep(1);
            playerB.startStep();
        }
        else
        {
            UserInterface.getUI().getGameField().setStep(0);
            playerA.startStep();
        }

    }

    public Player getPlayerA()
    {
        return playerA;
    }

    public Player getPlayerB()
    {
        return playerB;
    }

    public void battleEnded()
    {
        if(step==0)
        {
            stageTime+=System.currentTimeMillis()/1000-stepStartTime;
        }
        int winner=0; //0-ничья, 1-белые, -1-черные
        if(controller.stopA==0&&controller.stopB!=0)
        {
            winner=-1;
        }
        else if(controller.stopB==0&&controller.stopA!=0)
        {
            winner=1;
        }
        calculateScore(winner);

        if(gameMode==1)
        {
            UserInterface.getUI().openStageEndDialog(stageScore,stageTime,score,time);
            //UserInterface.getUI().openFinalDialog(0, 1, 1);
        }
        else
        {
            UserInterface.getUI().openStageEndDialog(winner);
        }
        //UserInterface.getUI().getGameField().openWinnerDialog(winner);
        //UserInterface.getUI().getGameField().setCount(getCount());
        win=winner;
    }

    public void calculateScore(int winner)
    {
        stageScore=0;
        double difMult=1;
        if(difficulty==1)
        {
            difMult=1.3;
        }
        switch (winner)
        {
            case 0:
            {
                break;
            }
            case 1:
            {
                stageScore= (int) (10*controller.stopA*difMult*(1+positionA/100.0)*(1+levelA/50.0)*(1-positionB/100.0)*(1-levelB/50.0));
                break;
            }
            case -1:
            {
                stageScore= (int) (-10*controller.stopB*difMult*(1-positionA/100.0)*(1-levelA/50.0)*(1+positionB/100.0)*(1+levelB/50.0));
                break;
            }
        }
    }

    public void closeGame()
    {
        Array<Body> bodies=new Array<Body>();
        resourcePool.getWorld().getBodies(bodies);
        for(int i=0; i< bodies.size; i++)
        {
            resourcePool.getWorld().destroyBody(bodies.get(i));
        }

        step=0;
        levelA=0;
        levelB=0;
        pause=false;

    }

    public void nextLevel()
    {
        boolean newLevel1p=false;
        resetCheckers();
        score+=stageScore;
        time+=stageTime;
        stageScore=0;
        stageTime=0;

        if(win!=-2)
        {
            switch (win)
            {
                case 1:
                {
                    if(positionA+1!=positionB)
                    {
                        positionA++;
                    }
                    else
                    {
                        positionA++;
                        positionB++;
                        if(!CheckerFiguresProvider.checkMaxPosition(levelB,positionB))
                        {
                            levelA++;
                            newLevel1p=true;
                            if(gameMode==1)
                            {
                                newLevel1p=true;
                            }
                            if(levelA!=CheckerFiguresProvider.getMaxLevel())
                            {
                                positionB=CheckerFiguresProvider.getMaxPosition(levelB);
                                positionA=CheckerFiguresProvider.getMinPosition(levelA);
                            }
                            else
                            {
                                UserInterface.getUI().openFinalDialog(0,difficulty,gameMode);
                                return;
                            }

                        }
                    }
                    step=0;
                    break;
                }
                case -1:
                {
                    if(positionB-1!=positionA)
                    {
                        positionB--;
                    }
                    else
                    {
                        positionA--;
                        positionB--;
                        if(!CheckerFiguresProvider.checkMinPosition(levelA,positionA))
                        {
                            levelB++;
                            if(levelB!=CheckerFiguresProvider.getMaxLevel()) {
                                positionA = CheckerFiguresProvider.getMinPosition(levelA);
                                positionB = CheckerFiguresProvider.getMaxPosition(levelB);
                            }
                            else
                            {
                                UserInterface.getUI().openFinalDialog(1,difficulty,gameMode);
                                return;
                            }
                        }

                    }
                    step=1;
                    break;
                }
                case 0:
                {
                    if(step==0)
                    {
                        step=1;
                    }
                    else
                    {
                        step=0;
                    }
                    break;
                }
            }
            win=-2;
        }
        if(newLevel1p && gameMode==1)
        {
                //UserInterface.getUI().openLevelEndDialog(score, time);
            saveStat();
            score=0;
            time=0;
        }
        ChapayGame.getInstance().openGameField();
    }

    public void  resetCheckers()
    {
        Array<Body> bodies=new Array<Body>();
        resourcePool.getWorld().getBodies(bodies);
        for(int i=0; i< bodies.size; i++)
        {
            resourcePool.getWorld().destroyBody(bodies.get(i));
        }
    }

    public void restartStage()
    {
        resetCheckers();
        step=levelStartStep;

        stageScore=0;
        stageTime=0;

        pause=false;
        ChapayGame.getInstance().openGameField();
    }

    public void stopGame()
    {
        pause=false;
        resetCheckers();
        try {
            saveGame();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ChapayGame.getInstance().openMainMenu();
    }

    public String getCount()
    {
        StringBuilder s=new StringBuilder();
        s.append(String.valueOf(levelA));
        s.append(" : ");
        s.append(String.valueOf(levelB));
        return s.toString();
    }

    public void saveGame() throws SQLException {
//        SaveData save=new SaveData();
//        Preferences tmp=save.getSettings();
//        tmp.putInteger(SaveData.LEVEL_A,levelA);
//        tmp.putInteger(SaveData.LEVEL_B,levelB);
//        tmp.putInteger(SaveData.POSITION_A,positionA);
//        tmp.putInteger(SaveData.POSITION_B,positionB);
//        tmp.putInteger(SaveData.STEP, levelStartStep);
//        tmp.putInteger(SaveData.GAME_MODE, gameMode);
//        tmp.putInteger(SaveData.STEP_MODE,stepOrder);
//        tmp.putInteger(SaveData.SAVE, 1);
//        tmp.flush();


        SQLiteDatabase db=dbManager.getReadableDatabase(); //todo

        switch (gameMode)
        {
            case 1:
            {
                ContentValues values=new ContentValues();
                values.put(DbManager.SG1P_ORDER, stepOrder);
                values.put(DbManager.SG1P_DIF, difficulty);
                values.put(DbManager.SG1P_LEVEL1P, levelA);
                values.put(DbManager.SG1P_LEVEL2P,levelB);
                values.put(DbManager.SG1P_POS1P, positionA);
                values.put(DbManager.SG1P_POS2P, positionB);
                values.put(DbManager.SG1P_ST_STEP, step);
                values.put(DbManager.SG1P_SCORE, score);
                values.put(DbManager.SG1P_TIME, time);

                Cursor cur=db.query(DbManager.SG1P_TABLE,new String[]{"_id"},null,null,null,null,null);
                long a=0;

                if (cur.getCount() > 0)
                {
                    db.delete(DbManager.SG1P_TABLE, null,null);
                    a = db.insertOrThrow(DbManager.SG1P_TABLE, null, values);
                }
                else
                {
                    a = db.insertOrThrow(DbManager.SG1P_TABLE, null, values);
                }
                Log.d("lol1", String.valueOf(a));
                break;
            }
            case 0:
            {
                ContentValues values=new ContentValues();
                values.put(DbManager.SG2P_ORDER, stepOrder);
                values.put(DbManager.SG2P_LEVEL1P, levelA);
                values.put(DbManager.SG2P_LEVEL2P, levelB);
                values.put(DbManager.SG2P_POS1P, positionA);
                values.put(DbManager.SG2P_POS2P, positionB);
                values.put(DbManager.SG2P_ST_STEP, step);

                Cursor cur=db.query(DbManager.SG2P_TABLE,new String[]{"_id"},null,null,null,null,null);
                if(cur.getCount()>0)
                {
                    db.delete(DbManager.SG2P_TABLE, null,null);
                    db.insertOrThrow(DbManager.SG2P_TABLE,null,values);
                }
                else
                {
                    db.insertOrThrow(DbManager.SG2P_TABLE,null,values);
                }

                break;
            }
        }


    }

    public void loadGame(int mode)
    {
//        SaveData save=new SaveData();
//        Preferences tmp=save.getSettings();
//        levelA=tmp.getInteger(SaveData.LEVEL_A,0);
//        levelB=tmp.getInteger(SaveData.LEVEL_B,0);
//        positionA=tmp.getInteger(SaveData.POSITION_A,0);
//        positionB=tmp.getInteger(SaveData.POSITION_B,7);
//        step=tmp.getInteger(SaveData.STEP,0);
//        gameMode=tmp.getInteger(SaveData.GAME_MODE,0);
//        stepOrder=tmp.getInteger(SaveData.STEP_MODE,0);
//        tmp.flush();
        SQLiteDatabase db=dbManager.getReadableDatabase();
        switch (mode)
        {
            case 0:
            {
                Cursor cur=db.query(DbManager.SG2P_TABLE,new String[]{"_id", DbManager.SG2P_ORDER, DbManager.SG2P_LEVEL1P, DbManager.SG2P_LEVEL2P,
                        DbManager.SG2P_POS1P, DbManager.SG2P_POS2P, DbManager.SG2P_ST_STEP},null,null,null,null,null);
                if(cur!=null && cur.moveToFirst())
                {
                    levelA=cur.getInt(cur.getColumnIndex(DbManager.SG2P_LEVEL1P));
                    levelB=cur.getInt(cur.getColumnIndex(DbManager.SG2P_LEVEL2P));
                    positionA=cur.getInt(cur.getColumnIndex(DbManager.SG2P_POS1P));
                    positionB=cur.getInt(cur.getColumnIndex(DbManager.SG2P_POS2P));
                    step=cur.getInt(cur.getColumnIndex(DbManager.SG2P_ST_STEP));
                    stepOrder=cur.getInt(cur.getColumnIndex(DbManager.SG2P_ORDER));
                    gameMode=mode;
                }
                break;
            }
            case 1:
            {
                Cursor cur=db.query(DbManager.SG1P_TABLE,new String[]{"_id", DbManager.SG1P_ORDER, DbManager.SG1P_LEVEL1P, DbManager.SG1P_LEVEL2P,
                        DbManager.SG1P_POS1P, DbManager.SG1P_POS2P, DbManager.SG1P_DIF, DbManager.SG1P_SCORE, DbManager.SG1P_TIME, DbManager.SG1P_ST_STEP},null,null,null,null,  null);
                {
                    if(cur!=null && cur.moveToFirst())
                    {
                        levelA=cur.getInt(cur.getColumnIndex(DbManager.SG1P_LEVEL1P));
                        levelB=cur.getInt(cur.getColumnIndex(DbManager.SG1P_LEVEL2P));
                        positionA=cur.getInt(cur.getColumnIndex(DbManager.SG1P_POS1P));
                        positionB=cur.getInt(cur.getColumnIndex(DbManager.SG1P_POS2P));
                        step=cur.getInt(cur.getColumnIndex(DbManager.SG1P_ST_STEP));
                        stepOrder=cur.getInt(cur.getColumnIndex(DbManager.SG1P_ORDER));
                        time=cur.getInt(cur.getColumnIndex(DbManager.SG1P_TIME));
                        score=cur.getInt(cur.getColumnIndex(DbManager.SG1P_SCORE));
                        difficulty=cur.getInt(cur.getColumnIndex(DbManager.SG1P_DIF));
                        gameMode=mode;
                    }
                }
                break;
            }
        }
    }

    public void loadSavedGame()
    {
        SaveData save=new SaveData();
        Preferences tmp=save.getSettings();
        if(tmp.getInteger(SaveData.SAVE,0)==1)
        {
            UserInterface.getUI().getMainMenu().stage.addActor(new NewGameDialog(0));
        }
        else
        {
            newGame();
        }
    }

    public void newGame()
    {
        resetCheckers();
        step=0;
        levelA=0;
        levelB=0;
        positionA=0;
        positionB=7;
        //UserInterface.getUI().openPlayersDialog();
        //ChapayGame.getInstance().openGameField();
    }


    public  void setPause(boolean mode)
    {
        pause=mode;
    }

    public boolean isPaused()
    {
        return pause;
    }

    public void setGameMode(int mode)
    {
        gameMode=mode;
    }

    public void endGame()
    {
        ChapayGame.getInstance().openMainMenu();
        pause=false;
        resetCheckers();
        score+=stageScore;
        time+=stageTime;
//        SaveData save=new SaveData();
//        Preferences tmp=save.getSettings();
//        tmp.putInteger(SaveData.SAVE,0);
//        tmp.flush();
        try {
            saveGame();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setFirstStep(int step)
    {
        this.step=step;
    }

    public void setStepOrder(int mode)
    {
        stepOrder=mode;
    }

    class DbManager extends SQLiteOpenHelper
    {
        public static final String STAT_TABLE="STAT_TABLE";
        public static final String STAT_LEVEL_COLUMN="STAT_LEVEL_COLUMN";
        public static final String STAT_COUNT_COLUMN="STAT_COUNT_COLUMN";
        public static final String STAT_TIME_COLUMN="STAT_TIME_COLUMN";

        public static final String SG2P_TABLE="SG2P_TABLE";
        public static final String SG2P_ORDER="SG2P_ORDER";
        public static final String SG2P_LEVEL1P="SG2P_LEVEL1P";
        public static final String SG2P_LEVEL2P="SG2P_LEVEL2P";
        public static final String SG2P_POS1P="SG2P_POS1P";
        public static final String SG2P_POS2P="SG2P_POS2P";
        public static final String SG2P_ST_STEP="SG2P_ST_STEP";

        public static final String SG1P_TABLE="SG1P_TABLE";
        public static final String SG1P_ORDER="SG1P_ORDER";
        public static final String SG1P_LEVEL1P="SG1P_LEVEL1P";
        public static final String SG1P_LEVEL2P="SG1P_LEVEL2P";
        public static final String SG1P_POS1P="SG1P_POS1P";
        public static final String SG1P_POS2P="SG1P_POS2P";
        public static final String SG1P_DIF="SG1P_DIF";
        public static final String SG1P_SCORE="SG1P_SCORE";
        public static final String SG1P_TIME="SG1P_TIME";
        public static final String SG1P_ST_STEP="SG1P_ST_STEP";

        private static final int DATABASE_VERSION=2;

        public DbManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            sqLiteDatabase.execSQL("CREATE TABLE STAT_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, STAT_LEVEL_COLUMN INTEGER, STAT_COUNT_COLUMN INTEGER, STAT_TIME_COLUMN INTEGER);");
            sqLiteDatabase.execSQL("CREATE TABLE SG1P_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, SG1P_ORDER INTEGER, " +
                                    "SG1P_LEVEL1P INTEGER, SG1P_LEVEL2P INTEGER, SG1P_POS1P INTEGER, SG1P_POS2P INTEGER, SG1P_DIF INTEGER, SG1P_SCORE INTEGER, SG1P_TIME INTEGER, SG1P_ST_STEP INTEGER);");
            sqLiteDatabase.execSQL("CREATE TABLE SG2P_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, SG2P_ORDER INTEGER, SG2P_LEVEL1P INTEGER, SG2P_LEVEL2P INTEGER, SG2P_POS1P INTEGER, SG2P_POS2P INTEGER, SG2P_ST_STEP INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STAT_TABLE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SG1P_TABLE);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SG2P_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public boolean isSavedGameExist(int gameMode)
    {
        switch (gameMode)
        {
            case 0:
            {
                Cursor c=dbManager.getReadableDatabase().query(DbManager.SG2P_TABLE,new String[]{"_id"},null,null,null,null,null);
                if(c.getCount()==1)
                {
                    c.close();
                    return true;
                }
                c.close();
                return false;
            }
            case 1:
            {
                Cursor c=dbManager.getReadableDatabase().query(DbManager.SG1P_TABLE,new String[]{"_id"},null,null,null,null,null);
                if(c.getCount()==1)
                {
                    c.close();
                    return true;
                }
                c.close();
                return false;
            }
        }
        return false;
    }

    public void saveStat()
    {
        SQLiteDatabase db=dbManager.getReadableDatabase();

        ContentValues val=new ContentValues();
        val.put(DbManager.STAT_LEVEL_COLUMN, levelA-1);
        val.put(DbManager.STAT_COUNT_COLUMN, score);
        val.put(DbManager.STAT_TIME_COLUMN, time);

        Cursor c=db.query(DbManager.STAT_TABLE,new String[]{"_id", DbManager.STAT_COUNT_COLUMN, DbManager.STAT_TIME_COLUMN, DbManager.STAT_LEVEL_COLUMN},
                DbManager.STAT_LEVEL_COLUMN+"= ?",new String[]{Integer.toString(levelA-1)},null, null, null);
        if(c!=null && c.moveToFirst())
        {
            int lvl=c.getInt(c.getColumnIndex(DbManager.STAT_LEVEL_COLUMN));
            db.delete(DbManager.STAT_TABLE, DbManager.STAT_LEVEL_COLUMN, new String[]{Integer.toString(lvl)});
            db.insertOrThrow(DbManager.STAT_TABLE,null,val);
        }
        else
        {
            db.insertOrThrow(DbManager.STAT_TABLE,null, val);
        }
        c.close();
    }


    int[] getScore()
    {
        int[] score=new int[2];
        score[0]=0;
        score[1]=0;
        Cursor c=dbManager.getReadableDatabase().query(DbManager.STAT_TABLE,new String[]{"_id", DbManager.STAT_COUNT_COLUMN, DbManager.STAT_TIME_COLUMN},null, null, null, null,null);
        if(c!=null && c.moveToFirst())
        {
            for(int i=0; i<c.getCount(); i++)
            {
                score[0]+=c.getInt(c.getColumnIndex(DbManager.STAT_COUNT_COLUMN));
                score[1]+=c.getInt(c.getColumnIndex(DbManager.STAT_TIME_COLUMN));
                c.moveToNext();
            }
        }
        c.close();
        c=dbManager.getReadableDatabase().query(DbManager.SG1P_TABLE,new String[]{"_id", DbManager.SG1P_TIME, DbManager.SG1P_SCORE},null, null, null, null,null);
        if(c!=null && c.moveToFirst())
        {
            score[0]+=c.getInt(c.getColumnIndex(DbManager.SG1P_SCORE));
            score[1]+=c.getInt(c.getColumnIndex(DbManager.SG1P_TIME));
        }
        c.close();
        return score;
    }

}

