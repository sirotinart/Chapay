package com.devnull.art.chapay;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by art on 25.08.14.
 */
public class SaveData
{
    public static final String APP_PREFERENCES="chapay_save";
    public static final String GAME_MODE="mode";
    public static final String STEP="step";
    public static final String LEVEL_A="levelA";
    public static final String LEVEL_B="levelB";
    public static final String POSITION_A="positionA";
    public static final String POSITION_B="positionB";
    public static final String SAVE="saved";
    public static final String STEP_MODE="stepMode";
    private Preferences mySettings;

    public SaveData()
    {
        mySettings=Gdx.app.getPreferences(APP_PREFERENCES);
    }

    public Preferences getSettings()
    {
        return mySettings;
    }
}
