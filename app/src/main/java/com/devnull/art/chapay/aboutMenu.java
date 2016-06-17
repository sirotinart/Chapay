package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by art on 10.08.14.
 */
public class aboutMenu implements Screen
{
    private Stage stage;
    private TextureActor backGround;
    private BitmapFont font;
    private textActor about;


    public aboutMenu()
    {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                ChapayGame.getInstance().getCamera()), ChapayGame.getInstance().getBatch())
        {

            @Override
            public boolean keyDown(int keyCode)
            {
                if(keyCode== Input.Keys.BACK)
                {
                    ChapayGame.getInstance().openMainMenu();
                }
                return super.keyDown(keyCode);
            }
        };
    }

    private void initActors()
    {
        TextureRegion background=resourcePool.getPool().getResource("background");
        backGround=new TextureActor(background);
        backGround.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        stage.addActor(backGround);

        about=new textActor("Chapayev is a game played on a checkerboard, a unique hybrid of checkers and " +
                "billiards which is played throughout the territory of the former USSR. The aim is to knock " +
                "the opponent's pieces off the board. The game is named after the Russian Civil War hero, " +
                "Vasily Chapayev", Gdx.graphics.getWidth()/100f,Gdx.graphics.getHeight()*0.9f,0.7f,true);

        textActor sign=new textActor("(c) /dev/null/games 2014",Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()*0.1f,0.7f);
        stage.addActor(about);
        stage.addActor(sign);
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
    public void resize(int i, int i2) {

    }

    @Override
    public void show()
    {
        initActors();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
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

    }
}
