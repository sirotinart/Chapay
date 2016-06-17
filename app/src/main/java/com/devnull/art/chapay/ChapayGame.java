package com.devnull.art.chapay;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by art on 30.07.14.
 */
public class ChapayGame extends Game
{
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private static ChapayGame instance=new ChapayGame();
    private UserInterface gui=UserInterface.getUI();
    private ActionResolver resolver;

    private GameData gameData;




    private ChapayGame() {
    }

    @Override
    public void create()
    {
        batch=new SpriteBatch();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameData=new GameData();

        openMainMenu();
    }

    public static ChapayGame getInstance()
    {
        return instance;
    }

    public void setActionResolver(ActionResolver resolver)
    {
        this.resolver=resolver;
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        setScreen(null);
        gui.close();
    }
    public ActionResolver getResolver()
    {
        return resolver;
    }

    public void openAboutMenu()
    {
        setScreen(gui.getAboutMenu());
    }

    public void openMainMenu()
    {
        setScreen(gui.getMainMenu());
    }

    public void openGameField() {setScreen(gui.getGameField());}

    public void startGame(GameModeData gmd)
    {
        getGameData().startGame(gmd);
        setScreen(gui.getGameField());
    }

    public void openPlayer1Dlg()
    {
        setScreen(gui.getPlayer1StartDlg());
    }

    public void openPlayer2Dlg()
    {
        setScreen(gui.getPlayer2StartDlg());
    }


    @Override
    public void render()
    {
        super.render();
    }

    public OrthographicCamera getCamera()
    {
        return camera;
    }

    public SpriteBatch getBatch()
    {
        return batch;
    }

    public  GameData getGameData()
    {
        return gameData;
    }



}
