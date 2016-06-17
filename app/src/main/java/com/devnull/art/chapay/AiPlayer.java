package com.devnull.art.chapay;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import java.util.Random;

/**
 * Created by art on 31.08.14.
 */
public class AiPlayer extends Player
{
    protected Player playerA;
    protected int difficulty;
    protected int coef1, coef2, coef3, coef4,coef5,coef6;
    public AiPlayer(String color, int difficulty)
    {
        super(color);
        playerA=ChapayGame.getInstance().getGameData().getPlayerA();
        this.difficulty=difficulty;
        if(difficulty==0)
        {
            coef1=5;
            coef2=6;
            coef3=1000;
            coef4=700;
            coef5=600;
            coef6=400;
        }
        else
        {
            coef1=2;
            coef2=2;
            coef3=500;
            coef4=200;
            coef5=200;
            coef6=200;
        }
    }

    @Override
    public void startStep()
    {
        int[] v;
        v=chooseChecker();
        doStep(v);
        UserInterface.getUI().getGameField().getStage().needSwitchPlayer();
    }

    @Override
    public void stopStep()
    {

    }


    public int[] chooseChecker()
    {
        SnapshotArray<Actor> black=getCheckersArray();
        SnapshotArray<Actor> white=playerA.getCheckersArray();
        Random r=new Random();
        for (int i=0; i<black.size; i++)
        {

            boolean good;
            for (int j=0; j<white.size; j++)
            {
                int b1;
                if(black.size!=1)
                {
                    b1=r.nextInt(black.size-1);
                }
                else
                {
                    b1=0;
                }

                int w1;
                if(white.size!=1)
                {
                    w1=r.nextInt(white.size-1);
                }
                else
                {
                    w1=0;
                }
                Checker b=(Checker) black.get(b1);
                Checker w=(Checker)white.get(w1);
                good=goodStep(b.getBodyPosition(), w.getBodyPosition(), b1, w1);
                if(good)
                {
                    return new int[]{b1, w1};
                }
            }
        }

        int b1;
        if(black.size!=1)
        {
            b1=r.nextInt(black.size-1);
        }
        else
        {
            b1=0;
        }
        int w1;
        if(white.size!=1)
        {
            w1=r.nextInt(white.size-1);
        }
        else
        {
            w1=0;
        }

        return new int[]{b1,w1};
    }

    public boolean goodStep(Vector2 bPos,Vector2 wPos, int nb, int nw)
    {
        SnapshotArray<Actor> black=getCheckersArray();
        SnapshotArray<Actor> white=playerA.getCheckersArray();
        for (int i=0; i<black.size; i++)
        {
            if (i!=nb)
            {
                Checker b=(Checker) black.get(i);
                if(tooNear(bPos,wPos, b.getBodyPosition()))
                {
                    return false;
                }
            }
        }

        /*for (int j=0; j<white.size; j++)
        {
            if(j!=nw)
            {
                Checker w=(Checker) white.get(j);
                if(tooNear(bPos,wPos, w.getBodyPosition()))
                {
                    return false;
                }
            }
        }*/
        return true;
    }

    public boolean tooNear(Vector2 bPos,Vector2 wPos, Vector2 checking)
    {
        float ab,ac,bc;

        ab= (float)Math.sqrt((checking.x-bPos.x)*(checking.x-bPos.x)+(checking.y-bPos.y)*(checking.y-bPos.y));
        ac=(float)Math.sqrt((wPos.x-bPos.x)*(wPos.x-bPos.x)+(wPos.y-bPos.y)*(wPos.y-bPos.y));
        bc=(float)Math.sqrt((wPos.x-checking.x)*(wPos.x-checking.x)+(wPos.y-checking.y)*(wPos.y-checking.y));
        //Log.d("ab", String.valueOf(ab));
        //Log.d("ac", String.valueOf(ac));
        //Log.d("bc", String.valueOf(bc));


        float cosCAB=(ac*ac+ab*ab-bc*bc)/(2*ab*ac);

        float ABC=(float)Math.acos((-ac*ac+ab*ab+bc*bc)/(2*ab*bc));
        //Log.d("abc", String.valueOf(ABC));

        float sinCAB=(float)Math.sqrt(1-cosCAB*cosCAB);

        float bh=ab*sinCAB;
        //Log.d("bh", String.valueOf(bh));

        if(bh<=2&&ABC>Math.PI/2f)
        {
            return true;
        }

        return false;
    }

    public void doStep(int[] checkers)
    {
        Checker c=(Checker)getCheckersArray().get(checkers[0]);
        Vector2 cp=c.getBodyPosition();
        Checker c2=(Checker)playerA.getCheckersArray().get(checkers[1]);
        Vector2 cp2=c2.getBodyPosition();
        Vector2 edge=UserInterface.getUI().getGameField().getBoard().getNearestEdge(c.getBodyPosition(), c2.getBodyPosition());

        float distToEdge=(float)Math.sqrt((edge.x-cp2.x)*(edge.x-cp2.x)+(edge.y-cp2.y)*(edge.y-cp2.y));
        float sp1=(float) Math.sqrt((2.4f*500*distToEdge)/31.5f);
        float distBetweenC1C2=(float)Math.sqrt((cp.x-cp2.x)*(cp.x-cp2.x)+(cp.y-cp2.y)*(cp.y-cp2.y));
        float sp2=(float)Math.sqrt((31.5f*sp1*sp1+500*distBetweenC1C2)*2/31.5f);

        Vector2 s=new Vector2();
        Random r=new Random();
        if(r.nextInt(15)%coef1==1||r.nextInt(15)%coef2==0)
        {
            s.x=(cp2.x-cp.x)*sp2/distBetweenC1C2+r.nextInt(coef5)/100f-r.nextInt(coef6)/100f;
            s.y=(cp2.y-cp.y)*sp2/distBetweenC1C2+r.nextInt(coef5)/100f-r.nextInt(coef6)/100f;
        }
        else
        {
            s.x=(cp2.x-cp.x)*sp2/distBetweenC1C2+r.nextInt(coef3)/100f-r.nextInt(coef4)/100f;
            s.y=(cp2.y-cp.y)*sp2/distBetweenC1C2+r.nextInt(coef3)/100f-r.nextInt(coef4)/100f;
        }


        c.setLinearSpeed(s.x,s.y);
    }

}
