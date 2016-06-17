package com.devnull.art.chapay;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.sql.SQLException;

/**
 * Created by art on 13.08.14.
 */
public class gameField implements Screen
{
    private GameFieldStage stage;
    private textActor stepLabel;
    private textActor count;
    private Board board;




    public gameField()
    {
        stage = new GameFieldStage();
        resourcePool.getWorld().setContactListener(new CheckerContactListener());


    }

    @Override
    public void render(float v)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ChapayGame.getInstance().getCamera().update();
        ChapayGame.getInstance().getBatch().setProjectionMatrix(ChapayGame.getInstance().getCamera().combined);
        stage.act(v);
        stage.draw();
        resourcePool.getPool().step();
    }

    @Override
    public void resize(int i, int i2) {

    }

    @Override
    public void show()
    {
        initBackground();
        initGameObjects();
        ChapayGame.getInstance().getGameData().createPlayers();
        initLabels();
        ChapayGame.getInstance().getGameData().initializeStep();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide()
    {
        stage.dispose();
        try {
            ChapayGame.getInstance().getGameData().saveGame();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void initBackground()
    {
        float x,y;
        TextureRegion background1=resourcePool.getPool().getResource("background");
        TextureActor background = new TextureActor(background1);
        background.setPosition(-myDeviceScreen.getCropX(), -myDeviceScreen.getCropY());
        stage.addActor(background);



    }

    private void initLabels()
    {
        float x,y;
        TextureRegion head1=resourcePool.getPool().getResource("head1");
        TextureActor headLeft = new TextureActor(head1);
        headLeft.setWidth(headLeft.getWidth() * myDeviceScreen.getAlternateScale() / myDeviceScreen.getScale());
        headLeft.setHeight(headLeft.getHeight() * myDeviceScreen.getAlternateScale() / myDeviceScreen.getScale());
        x=0;
        y=Gdx.graphics.getHeight()- headLeft.getHeight();

        headLeft.setPosition(x, y);
        //headLeft.setScale(myDeviceScreen.getAlternateScale());

        stage.addActor(headLeft);
        x+= headLeft.getWidth();

        TextureRegion head2=resourcePool.getPool().getResource("head2");
        TextureActor headMid = new TextureActor(head2);
        headMid.setWidth(headMid.getWidth() * myDeviceScreen.getAlternateScale() / myDeviceScreen.getScale());
        headMid.setHeight(headMid.getHeight() * myDeviceScreen.getAlternateScale() / myDeviceScreen.getScale());

        headMid.setPosition(Gdx.graphics.getWidth() / 2f - headMid.getWidth() / 2f, y);


        //headMid.setScale(myDeviceScreen.getAlternateScale());
        stage.addActor(headMid);
        x+= headMid.getWidth();

        x=Gdx.graphics.getWidth()- headLeft.getWidth()/2f;
        y+= headLeft.getHeight()/2f;
        buttonActor headRight = new buttonActor(head1, head2, "Menu", 0, 0, 0.45f);
        headRight.setWidth(headMid.getWidth());
        headRight.setHeight(headMid.getHeight());
        //y-=head1.getRegionHeight();
        //x=Gdx.graphics.getWidth()-headLeft.getWidth();
        headRight.setPosition(x, y);

        //headRight.setScale(myDeviceScreen.getAlternateScale());
        headRight.addListener(new MenuButtonListener());
        stage.addActor(headRight);


        x=Gdx.graphics.getWidth()/6f;
        y=Gdx.graphics.getHeight()*0.978f;
        stepLabel=new textActor("",x,y,0.45f*myDeviceScreen.getAlternateScale()/myDeviceScreen.getScale());
        stage.addActor(stepLabel);


        count=new textActor(ChapayGame.getInstance().getGameData().getCount(),Gdx.graphics.getWidth()/2f,
                y, 0.5f*myDeviceScreen.getAlternateScale()/myDeviceScreen.getScale());

        stage.addActor(count);

        TextureActor ch1=new TextureActor(resourcePool.getPool().getResource("checker_gray"));
        ch1.setWidth(ch1.getWidth()*0.5f*myDeviceScreen.getAlternateScale()/myDeviceScreen.getScale());
        ch1.setHeight(ch1.getHeight()*0.5f*myDeviceScreen.getAlternateScale()/myDeviceScreen.getScale());
        ch1.setCenterPosition(Gdx.graphics.getWidth()*6/15f,y);
        stage.addActor(ch1);

        TextureActor ch2=new TextureActor(resourcePool.getPool().getResource("checker_blue"));
        ch2.setWidth(ch2.getWidth()*0.5f*myDeviceScreen.getAlternateScale()/myDeviceScreen.getScale());
        ch2.setHeight(ch2.getHeight()*0.5f*myDeviceScreen.getAlternateScale()/myDeviceScreen.getScale());
        ch2.setCenterPosition(Gdx.graphics.getWidth()*9/15f,y);
        stage.addActor(ch2);

    }

    private void initGameObjects()
    {
        board=new Board();
        stage.addActor(board);
    }

    public void setStep(int color)
    {

        switch (color)
        {
            case 0:
                stepLabel.changeText("White's move");
                break;
            case 1:
                stepLabel.changeText("Blue's move");
                break;
        }
    }

    public Board getBoard()
    {
        return board;
    }

    public GameFieldStage getStage()
    {
        return stage;
    }

    public void openWinnerDialog(int winner)
    {
        SmallPopup popup=new SmallPopup("");
        switch (winner)
        {
            case 0:
            {
                popup.setText("Stalemate!");
                break;
            }
            case 1:
            {
                popup.setText("White wins!");
                break;
            }
            case -1:
            {
                popup.setText("Blue wins!");
            }
        }
        stage.addActor(popup);
    }

    public class MenuButtonListener extends ClickListener
    {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            ChapayGame.getInstance().getGameData().setPause(true);
            openPauseDialog();
        }
    }

    public void close()
    {
        ChapayGame.getInstance().getGameData().closeGame();
        ChapayGame.getInstance().openMainMenu();

    }

    public void openPauseDialog()
    {
        PauseMenu pauseMenu=new PauseMenu();
        stage.addActor(pauseMenu);
    }

    public void setCount(String count)
    {
        this.count.changeText(count);
    }

}
