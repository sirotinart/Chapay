package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by art on 25.10.15.
 */
public class Player1StartDlg implements Screen {
    protected Stage stage;
    protected RadioSwitch difficultySw;
    protected RadioSwitch stepOrderSw;
    protected RadioSwitch firstStepSw;

    public Player1StartDlg()
    {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                ChapayGame.getInstance().getCamera()),ChapayGame.getInstance().getBatch())
        {

            @Override
            public boolean keyDown(int keyCode)
            {
                if(keyCode== Input.Keys.BACK)
                {
                    ChapayGame.getInstance().openMainMenu();
                    //MainActivity.exitGame();
                }
                return super.keyDown(keyCode);
            }
        };


    }

    public Stage getStage()
    {
        return stage;
    }



    @Override
    public void render(float v)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ChapayGame.getInstance().getCamera().update();
        ChapayGame.getInstance().getBatch().setProjectionMatrix(ChapayGame.getInstance().getCamera().combined);
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void show()
    {
        initGraphics();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        loadTotalScore(ChapayGame.getInstance().getGameData().isSavedGameExist(1));


    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose()
    {
        Gdx.input.setInputProcessor(null);
    }

    public void loadTotalScore(boolean isSavedGameExist)
    {
        float alignX=0.16f;
        textActor score1;
        textActor time1;

        if(isSavedGameExist)
        {
            int[] score=ChapayGame.getInstance().getGameData().getScore();

            score1=new textActor(String.valueOf(score[0]), Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
            time1=new textActor(String.valueOf(score[1]),Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);

        }
        else
        {
            score1=new textActor(String.valueOf(0), Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
            time1=new textActor(String.valueOf(0),Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        }

        score1.setPosition(Gdx.graphics.getWidth()*alignX+score1.getWidth()*0.5f,Gdx.graphics.getHeight()*0.85f);
        stage.addActor(score1);
        time1.setPosition(Gdx.graphics.getWidth()*alignX+time1.getWidth()*0.5f,Gdx.graphics.getHeight()*0.65f);
        stage.addActor(time1);
    }

    public void initGraphics()
    {
        float alignX=0.16f;

        TextureRegion background=resourcePool.getPool().getResource("background");
        TextureActor backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        stage.addActor(backGround);
        textActor score=new textActor("Total score:",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        score.setPosition(Gdx.graphics.getWidth()*alignX+score.getWidth()*0.5f,Gdx.graphics.getHeight()*0.95f);
        stage.addActor(score);

        textActor time=new textActor("Time in game:",Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.9f,0.7f);
        time.setPosition(Gdx.graphics.getWidth()*alignX+time.getWidth()*0.5f,Gdx.graphics.getHeight()*0.75f);
        stage.addActor(time);

        textActor title =new textActor("New Game",Gdx.graphics.getWidth()*0.2f,Gdx.graphics.getHeight()*0.59f,0.7f);
        title.setPosition(Gdx.graphics.getWidth()*alignX+title.getWidth()*0.5f,Gdx.graphics.getHeight()*0.55f);
        stage.addActor(title);

        textActor head =new textActor("Difficulty",Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.7f,0.6f);
        head.setPosition(Gdx.graphics.getWidth()*alignX+head.getWidth()*0.5f,Gdx.graphics.getHeight()*0.47f);
        stage.addActor(head);

        String[] a=new String[2];
        a[0]="Easy";
        a[1]="Hard";
        difficultySw=new RadioSwitch(a,Gdx.graphics.getWidth()*alignX, Gdx.graphics.getHeight()*0.375f);
        stage.addActor(difficultySw);

        textActor middle =new textActor("First step",Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.38f,0.6f);
        middle.setPosition(Gdx.graphics.getWidth()*alignX+middle.getWidth()*0.5f,Gdx.graphics.getHeight()*0.35f);
        stage.addActor(middle);

        a[0]="White";
        a[1]="Blue";
        firstStepSw=new RadioSwitch(a,Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.255f);
        stage.addActor(firstStepSw);

        textActor bottom =new textActor("Steps order",Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.20f,0.6f);
        bottom.setPosition(Gdx.graphics.getWidth()*alignX+bottom.getWidth()*0.5f,Gdx.graphics.getHeight()*0.23f);
        stage.addActor(bottom);

        a[0]="Classic"; //TODO: переделать порядок ходов
        a[1]="Step by step";
        stepOrderSw=new RadioSwitch(a,Gdx.graphics.getWidth()*alignX, Gdx.graphics.getHeight()*0.135f);
        stage.addActor(stepOrderSw);

        buttonActor resume = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Resume", Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() * 0.05f, 0.45f,0.45f,0.75f);
        resume.addListener(new ResumeBtnListener());
        stage.addActor(resume);

        buttonActor start = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "New game", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.05f, 0.45f,0.45f,0.75f);
        start.addListener(new StartBtnListener());
        stage.addActor(start);

        buttonActor back = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Back", Gdx.graphics.getWidth() *5 / 6f, Gdx.graphics.getHeight() * 0.05f, 0.45f,0.45f,0.75f);
        back.addListener(new BackBtnListener());
        stage.addActor(back);
    }

    class BackBtnListener extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            //super.clicked(event, x, y);
            ChapayGame.getInstance().openMainMenu();
        }
    }

    class StartBtnListener extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            //super.clicked(event, x, y);
            GameModeData gmd=new GameModeData();
            gmd.gameMode=1;
            gmd.difficulty=difficultySw.getCurrentItem();
            gmd.stepOrder=stepOrderSw.getCurrentItem();
            gmd.firstStep=firstStepSw.getCurrentItem();

            ChapayGame.getInstance().startGame(gmd);

        }
    }

    class ResumeBtnListener extends  ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            if(ChapayGame.getInstance().getGameData().isSavedGameExist(1))
            {
                ChapayGame.getInstance().getGameData().loadGame(1);
                ChapayGame.getInstance().openGameField();
            }
        }
    }


}
