package com.dev.dormotory.Utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.dormotory.R;

import java.util.Random;

import static com.dev.dormotory.GameActivity.COUNT_ITEMS;
import static com.dev.dormotory.Utils.BottomSheet.getResourseId;

public class GameManager {
    private String TAG = "GAMEMANAGER";
    private ImageView[] gameViews;
    private boolean[] activeItem;
    private Runnable r;
    private Context context;
    private float height;
    private Activity activity;
    private TextView scoreView,levelView,timeView;
    private int activeCount = 0;
    private int level = 0;
    private OnGameOverListner onGameOverListner;
    private int score = 0;
    private int timeForItem = 700;
    private int ResImageOff = R.drawable.game_resoff;
    private int ResImageOn = R.drawable.game_reson;

    public GameManager(Activity activity){
        context = activity;
        this.activity = activity;
        gameViews = new ImageView[COUNT_ITEMS];
        activeItem = new boolean[COUNT_ITEMS];
        scoreView = activity.findViewById(R.id.scope);
        timeView = activity.findViewById(R.id.time);
        levelView = activity.findViewById(R.id.level);
        height = 100* (context.getResources().getDisplayMetrics().density);
        initGameView();
    }

    private void initGameView() {
        for (int i=0;i<COUNT_ITEMS;i++) {
            gameViews[i] = activity.findViewById(getResourseId(context, "item_" + String.valueOf(i), context.getPackageName()));
            ViewGroup.LayoutParams params = gameViews[i].getLayoutParams();
// Changes the height and width to the specified *pixels*
            params.height = (int) height;
            params.width = (int) height;
            gameViews[i].setLayoutParams(params);

        }
        clearArray();
    }

    private void clearArray(){
        for (int i=0;i<COUNT_ITEMS;i++)
        activeItem[i] = false;
    }
    boolean checkActive(){
        return getActiveCount() != 0;
    }

    private int getActiveCount(){
        int n = 0;
        for(int i =0;i<COUNT_ITEMS;i++){
            if(activeItem[i]){
                n+=1;
            }
        }
        return n;
    }
    static void shuffleArray(boolean[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            boolean a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    void settingViews(){

        for(int i = 0;i<activeCount;i++){
            activeItem[i] = true;
        }

        shuffleArray(activeItem);
        Log.e(TAG, "settingViews: "+activeCount );
        for(int i = 0; i<COUNT_ITEMS; i++){
            if(activeItem[i]){
                int j = i;
                gameViews[i].setVisibility(View.VISIBLE);
                gameViews[i].setOnClickListener(v->{
                    if(checkActive() && activeItem[j]){
                        score+=1;
                        scoreView.setText("Счет "+String.valueOf(score));
                        activeItem[j] = false;
                        gameViews[j].setImageDrawable(activity.getResources().getDrawable(ResImageOn));
                        animateView(false,gameViews[j]);
                    }
                });
                gameViews[i].setImageDrawable(activity.getResources().getDrawable(ResImageOff));
                animateView(true,gameViews[i]);
            }else{
                gameViews[i].setVisibility(View.INVISIBLE);
                gameViews[i].setOnClickListener(null);
            }
        }
    }

    private void animateView(boolean active, View view) {
        if(active){
            view.animate().alpha(1).scaleY(view.getScaleY()/1.4f).scaleX(view.getScaleX()/1.4f)
                    .setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(100);
        }else{
            view.animate().alpha(0).scaleY(view.getScaleY()*1.4f).scaleX(view.getScaleX()*1.4f)
                    .setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(100);
        }
    }

    private void nextLevel(){
        level += 1;
        levelView.setText("Уровень "+String.valueOf(level));
        if(timeForItem > 40){
            timeForItem -=40;
        }
        Random r = new Random();
        int t = r.nextInt(100);
        timeForItem +=t;
        if(level < 5) {
            activeCount = getRandomCount(6);
        }else{
            activeCount = getRandomCount(9);
        }
    }
    private int getRandomCount(int border){
        int i = 0;
        while (i == 0){
            Random random = new Random();
            i = random.nextInt(border);
        }
        return i;
    }
    public void play(){
       r = () -> {
//           try {
               if (!checkActive()) {
                   nextLevel();
                   clearArray();
                   Log.e(TAG, "play: " );
                   settingViews();
                   int time;
                   if(activeCount>3){
                       time = timeForItem*activeCount/2;
                   }else{
                       time = timeForItem*activeCount;
                   }
                   ValueAnimator animator = ValueAnimator.ofFloat(time/1000,0 );
                   animator.setDuration(time);
                   animator.setInterpolator(new AccelerateInterpolator());

                   animator.addUpdateListener(animation -> {
                       float value = ((Float) (animation.getAnimatedValue()))
                               .floatValue();
                       timeView.setText("Времени осталось: "+String.valueOf((float) Math.round(value*100)/100)+"с");
                   });

                   animator.start();
                   new Handler().postDelayed(r, time);
               } else {
                   GameOver();
               }
//           }catch (Exception e){}
       };
       r.run();
    }
    public void RestartGame(){
        activeCount = 0;
        level = 0;
        score = 0;
        timeForItem = 700;
        levelView.setText("Уровень 0");
        scoreView.setText("Счет 0");
        initGameView();
        play();
    }
    private void GameOver(){
        Log.e(TAG, "GameOver " );
        if(onGameOverListner != null)
            onGameOverListner.OnGameOver(score);
        Toast.makeText(activity,"Проигрышь",Toast.LENGTH_LONG).show();
    }

    public void setOnGameOverListner(OnGameOverListner onGameOverListner) {
        this.onGameOverListner = onGameOverListner;
    }

    public interface OnGameOverListner{
        void OnGameOver(int score);
    }

}
