package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;

/**
 * Created by art on 03.08.14.
 */
public class myDeviceScreen {
    private static float virtualWidth;
    private static float virtualHeight;
    private static float virtualAspectRatio;
    private static float width;
    private static float height;
    private static float scale;
    private static float scale2;
    private static float cropX;
    private static float cropY;
    private static float aspectRatio;
    private static float constantScale;
    private static myDeviceScreen instance =new myDeviceScreen();
    private static String mode;
    public static float BOX_WORLD_TO;

    private myDeviceScreen()
    {
        if(Gdx.graphics.getHeight()>1024)
        {
            virtualWidth=1536;
            virtualHeight=2048;
            mode="QXGA";
            BOX_WORLD_TO=66f;
        }
        else
        if (Gdx.graphics.getHeight()>640)
        {
            virtualHeight=1024;
            virtualWidth=768;
            mode="XGA";
            BOX_WORLD_TO=33f;
        }
        else
        {
            virtualWidth=320;
            virtualHeight=480;
            mode="HVGA";
            BOX_WORLD_TO=14;
        }

        virtualAspectRatio = virtualWidth / virtualHeight;

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        aspectRatio = width / height;

        if (aspectRatio < virtualAspectRatio) {
            scale = height / virtualHeight;
            scale2=width/virtualWidth;
            cropX = (virtualWidth * scale - width) / 2f;
        } else if (aspectRatio > virtualAspectRatio) {
            scale = width / virtualWidth;
            scale2= height/virtualHeight;
            cropY = (virtualHeight * scale - height) / 2f;
        } else {
            scale = width / virtualWidth;
            scale2=scale;
        }

        BOX_WORLD_TO*=scale2;
        constantScale=(Gdx.graphics.getHeight()/2048f)*scale*0.8f;
    }

    public static float getScale() {
        return scale;
    }

    public static float getAlternateScale()
    {
        return scale2;
    }

    public static float getCropX()
    {
        return cropX;
    }

    public static float getCropY()
    {
        return cropY;
    }

    public static float getVirtualWidth()
    {
        return virtualWidth;
    }

    public static float getVirtualHeight()
    {
        return virtualHeight;
    }

    public static String getMode()
    {
        return mode;
    }

    public static float getConstantScale()
    {
        return constantScale;
    }

}
