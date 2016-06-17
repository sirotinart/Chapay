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
public class Player2StartDlg implements Screen
{
    protected Stage scr;
    protected RadioSwitch stepOrderSw;
    protected RadioSwitch firstStepSw;

    public Player2StartDlg()
    {
        scr = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
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

        initGraphics();
    }

    @Override
    public void render(float v)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ChapayGame.getInstance().getCamera().update();
        ChapayGame.getInstance().getBatch().setProjectionMatrix(ChapayGame.getInstance().getCamera().combined);
        scr.act(v);
        scr.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(scr);
        Gdx.input.setCatchBackKey(true);
//        if(ChapayGame.getInstance().getGameData().isSavedGameExist(0))
//        {
//            UserInterface.getUI().openSavedGameDialog(0);
//        }
//        else
//        {
//            ChapayGame.getInstance().getGameData().newGame();
//        }
    }

    private void initGraphics()
    {
        float alignX=0.16f;

        TextureRegion background=resourcePool.getPool().getResource("background");
        TextureActor backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        scr.addActor(backGround);

        textActor title =new textActor("2 players mode",Gdx.graphics.getWidth()*0.2f,Gdx.graphics.getHeight()*0.59f,0.7f);
        title.setPosition(Gdx.graphics.getWidth()*alignX+title.getWidth()*0.5f,Gdx.graphics.getHeight()*0.8f);
        scr.addActor(title);

        textActor ng =new textActor("New Game",Gdx.graphics.getWidth()*0.2f,Gdx.graphics.getHeight()*0.59f,0.7f);
        ng.setPosition(Gdx.graphics.getWidth()*alignX+title.getWidth()*0.5f,Gdx.graphics.getHeight()*0.55f);
        scr.addActor(title);

        textActor middle =new textActor("First step",Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.38f,0.6f);
        middle.setPosition(Gdx.graphics.getWidth()*alignX+middle.getWidth()*0.5f,Gdx.graphics.getHeight()*0.47f);
        scr.addActor(middle);

        String[] a=new String[2];
        a[0]="White";
        a[1]="Blue";
        firstStepSw=new RadioSwitch(a,Gdx.graphics.getWidth()*alignX,Gdx.graphics.getHeight()*0.375f);
        scr.addActor(firstStepSw);

        textActor bottom =new textActor("Steps order",Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.20f,0.6f);
        bottom.setPosition(Gdx.graphics.getWidth()*alignX+bottom.getWidth()*0.5f,Gdx.graphics.getHeight()*0.35f);
        scr.addActor(bottom);

        a[0]="Classic"; //TODO: переделать порядок ходов
        a[1]="Step by step";
        stepOrderSw=new RadioSwitch(a,Gdx.graphics.getWidth()*alignX, Gdx.graphics.getHeight()*0.255f);
        scr.addActor(stepOrderSw);


        buttonActor resume = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Resume", Gdx.graphics.getWidth() / 6f, Gdx.graphics.getHeight() * 0.05f, 0.45f,0.45f,0.75f);
        resume.addListener(new ResumeBtnListener());
        scr.addActor(resume);

        buttonActor start = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "New game", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 0.05f, 0.45f,0.45f,0.75f);
        start.addListener(new StartBtnListener());
        scr.addActor(start);

        buttonActor back = new buttonActor(resourcePool.getPool().getResource("button"),
                resourcePool.getPool().getResource("button_pressed"), "Back", Gdx.graphics.getWidth() *5 / 6f, Gdx.graphics.getHeight() * 0.05f, 0.45f,0.45f,0.75f);
        back.addListener(new BackBtnListener());
        scr.addActor(back);
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
    public void dispose() {

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
        public void clicked(InputEvent event, float x, float y)
        {
            //super.clicked(event, x, y);
            GameModeData gmd=new GameModeData();
            gmd.gameMode=0;
            gmd.difficulty=0;
            gmd.firstStep=firstStepSw.getCurrentItem();
            gmd.stepOrder=stepOrderSw.getCurrentItem();
            ChapayGame.getInstance().startGame(gmd);
        }
    }

    class ResumeBtnListener extends  ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            if(ChapayGame.getInstance().getGameData().isSavedGameExist(0))
            {
                ChapayGame.getInstance().getGameData().loadGame(0);
                ChapayGame.getInstance().openGameField();
            }
        }
    }
}
