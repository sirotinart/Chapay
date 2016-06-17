package com.devnull.art.chapay;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by art on 30.07.14.
 */
public class mainMenu implements Screen {
    protected Stage stage;
    protected TextureActor backGround;
    protected TextureActor star;
    protected buttonActor start1pButton;
    protected buttonActor aboutButton;
    protected buttonActor exitButton;
    protected buttonActor start2pButton;
    protected buttonActor startNetButton;

    public mainMenu()
    {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                ChapayGame.getInstance().getCamera()),ChapayGame.getInstance().getBatch())
        {

            @Override
            public boolean keyDown(int keyCode)
            {
                if(keyCode== Input.Keys.BACK)
                {
                    MainActivity.exitGame();
                }
                return super.keyDown(keyCode);
            }
        };
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
    public void resize(int i, int i2)
    {

    }

    @Override
    public void show()
    {
        initBackground();
        initButtons();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide()
    {
        stage.dispose();
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

    protected void initBackground()
    {

        TextureRegion background=resourcePool.getPool().getResource("background");
        backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        stage.addActor(backGround);

        TextureRegion tmpstar=resourcePool.getPool().getResource("star");
        star=new TextureActor(tmpstar);
        star.setCenterPosition(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()*3.2f/4f);
        stage.addActor(star);

        textActor name = new textActor("Chapaev", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() * 3.3f / 6f, 1.0f);
        stage.addActor(name);

    }

    protected void initButtons()
    {
        float bx,bx2,by;
        float heightScale=1.0f;
        float widthScale=0.7f;
        float fontScale=0.7f;

        bx=Gdx.graphics.getWidth()*0.25f;
        bx2=Gdx.graphics.getWidth()*0.75f;
        by=Gdx.graphics.getHeight()*1.5f/5f;

        TextureRegion tmpbntreg=resourcePool.getPool().getResource("button");
        TextureRegion tmpbntregPressed=resourcePool.getPool().getResource("button_pressed");

        start1pButton =new buttonActor(tmpbntreg,tmpbntregPressed,"1 player",bx,by,fontScale,widthScale,heightScale);
        //by-=start1pButton.getHeight()*1.2;
        start1pButton.addListener(new onPlayer1BtnClicked());

        start2pButton =new buttonActor(tmpbntreg,tmpbntregPressed,"2 players",bx2,by,fontScale,widthScale,heightScale);
        start2pButton.addListener(new onPlayer2BtnClicked());

        by-= start1pButton.getHeight()*1.4;

        //startNetButton=new buttonActor(tmpbntreg,tmpbntregPressed, "Net game", bx, by, fontScale, widthScale, heightScale);


        aboutButton=new buttonActor(tmpbntreg,tmpbntregPressed,"About",bx2,by, fontScale, widthScale, heightScale);
        aboutButton.addListener(new onAboutButtonClicked());
        //by-= start1pButton.getHeight()*1.2;

        exitButton=new buttonActor(tmpbntreg, tmpbntregPressed, "Exit",bx,by,fontScale,widthScale,heightScale);
        exitButton.addListener(new onExitButtonClicked());

        stage.addActor(start1pButton);
        stage.addActor(start2pButton);
        //stage.addActor(startNetButton);
        stage.addActor(aboutButton);
        stage.addActor(exitButton);

    }

    class onExitButtonClicked extends ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            MainActivity.exitGame();
        }
    }


    class onAboutButtonClicked extends ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            ChapayGame.getInstance().openAboutMenu();
            //ChapayGame.getInstance().getResolver().loginGPGS();
        }
    }


    class onStartButtonClicked extends ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {

            //ChapayGame.getInstance().getGameData().loadSavedGame();
            //ChapayGame.getInstance().openGameField();
        }
    }

    class onPlayer1BtnClicked extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            //super.clicked(event, x, y);
            ChapayGame.getInstance().openPlayer1Dlg();
        }
    }

    class onPlayer2BtnClicked extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            //super.clicked(event, x, y);
            ChapayGame.getInstance().openPlayer2Dlg();
        }
    }
}
