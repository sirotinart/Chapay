package com.devnull.art.chapay;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
//import com.google.example.games.basegameutils.GameHelper;


public class MainActivity extends AndroidApplication implements ActionResolver {
    private static final String AD_UNIT_ID = "ca-app-pub-7558057845057193/5369082669";
    //private static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/developer?id=TheInvader360";
    protected AdView adView;
    protected View gameView;
    //private GameHelper gameHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //gameHelper = new GameHelper(this, GameHelper.CLIENT_ALL);
        //gameHelper.setConnectOnStart(false);
        //gameHelper.enableDebugLog(true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        AndroidApplicationConfiguration config=new AndroidApplicationConfiguration();
        config.useAccelerometer=false;
        config.useCompass=false;
        //config.numSamples=4;
        config.useGLSurfaceView20API18=true;
        config.useWakelock=true;

        //initialize(ChapayGame.getInstance(),config);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        AdView admobView = createAdView();
        layout.addView(admobView);
        View gameView = createGameView(config);
        layout.addView(gameView);

        setContentView(layout);
        startAdvertising(admobView);

        //gameHelper.setup(this);

        ChapayGame.getInstance().setActionResolver(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //gameHelper.onStart(this);
        //submitScoreGPGS(10);

    }

    private AdView createAdView() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setId(5); // this is an arbitrary id, allows for relative positioning in createGameView()
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setLayoutParams(params);
        adView.setBackgroundColor(Color.BLACK);
        return adView;
    }

    private View createGameView(AndroidApplicationConfiguration cfg) {
        gameView = initializeForView(ChapayGame.getInstance(), cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ABOVE, adView.getId());
        gameView.setLayoutParams(params);
        return gameView;
    }

    private void startAdvertising(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public static void exitGame()
    {
        Log.d("Exit", "Exit");
        ChapayGame.getInstance().dispose();
        System.runFinalization();
        System.exit(0);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) adView.resume();
    }

    @Override
    public void onPause() {
        if (adView != null) adView.pause();
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //gameHelper.onStop();
    }

    @Override
    public void onDestroy() {
        if (adView != null) adView.destroy();
        super.onDestroy();
        resourcePool.getPool().free();
    }

    public void onSignInFailed(){}

    public void onSignInSucceeded()
    {
        Log.d("GPS", "Signed in");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // здесь gameHelper принимает решение о подключении, переподключении или
        // отключении от игровых сервисов, в зависимости от кода результата
        // Activity
        //gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean getSignedInGPGS() {
        // статус подключения
        //return gameHelper.isSignedIn();
        return true;
    }

    @Override
    public void loginGPGS() {
        try {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // инициировать вход пользователя. Может быть вызван диалог
                    // входа. Выполняется в UI-потоке
                    //gameHelper.beginUserInitiatedSignIn();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void submitScoreGPGS(int score) {
        // отправить игровые очки в конкретную таблицу рекордов с ID
        // “HgkIr62KmoQJEAIQAQ”
        //Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                //"CgkIgtv4mIEaEAIQAQ", score);

    }

    @Override
    public void unlockAchievementGPGS(String achievementId) {
        // открыть достижение с ID achievementId
        //Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);

    }

    @Override
    public void getLeaderboardGPGS() {
        // вызвать Activity для всех зарегистрированных таблиц рекордов. Так же
        // можно вызывать Activity под конкретную таблицу
        //startActivityForResult(
                //Games.Leaderboards.getAllLeaderboardsIntent(gameHelper
                      //  .getApiClient()), 100);

    }

    @Override
    public void getAchievementsGPGS() {
        // вызвать Activity с достижениями
        //startActivityForResult(
                //Games.Achievements.getAchievementsIntent(gameHelper
                      //  .getApiClient()), 101);

    }

}
