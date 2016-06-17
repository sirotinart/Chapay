package com.devnull.art.chapay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by art on 10.08.14.
 */
public class resourcePool
{
    private Map <String, TextureRegion> textures;
    private static resourcePool pool=new resourcePool();
    private String imgPath;
    private String fntPath;
    private BitmapFont font;
    private static World world = new World(new Vector2(0, 0), true);
    private static Box2DDebugRenderer debugRenderer;

    static final float BOX_STEP=1/60f;
    static final int BOX_VELOCITY_ITERATIONS=6;
    static final int BOX_POSITION_ITERATIONS=2;
    static final float WORLD_TO_BOX=0.01f;
    private float BOX_WORLD_TO=65f;

    private resourcePool()
    {
        imgPath="images/";
        imgPath=imgPath.concat(myDeviceScreen.getMode());
        imgPath=imgPath.concat("/");
        fntPath="fonts/";
        fntPath=fntPath.concat(myDeviceScreen.getMode());
        fntPath=fntPath.concat("/ubuntu.fnt");
        textures=new HashMap<String, TextureRegion>();


    }

    public static resourcePool getPool()
    {
        return pool;
    }

    public TextureRegion getResource(String name)
    {
        if(!textures.containsKey(name))
        {
            String s=new String (imgPath);
            s=s.concat(name);
            s=s.concat(".png");
            Texture tmpTex=new Texture(Gdx.files.internal(s));
            TextureRegion tmpReg=new TextureRegion(tmpTex,0,0,tmpTex.getWidth(),tmpTex.getHeight());
            textures.put(name,tmpReg);
            return tmpReg;
        }
        else
        {
            return textures.get(name);
        }
    }

    public BitmapFont getFont()
    {
        if (font==null)
        {
            font=new BitmapFont(Gdx.files.internal(fntPath));
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return font;
    }

    public static World getWorld()
    {
        return world;
    }

    public static Box2DDebugRenderer getDebugRenderer()
    {
        return debugRenderer;
    }

    public void step()
    {
        if(!ChapayGame.getInstance().getGameData().isPaused())
        world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS,BOX_POSITION_ITERATIONS);
    }

    public void free()
    {
        textures.clear();
        font=null;
    }
}
