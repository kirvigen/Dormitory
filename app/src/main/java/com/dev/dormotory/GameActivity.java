package com.dev.dormotory;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.dormotory.Utils.BottomSheet;
import com.dev.dormotory.Utils.GameManager;
import com.dev.dormotory.Utils.Utils;

import static com.dev.dormotory.Utils.BottomSheet.getResourseId;

public class GameActivity extends AppCompatActivity {
    Utils utils;
    public static int COUNT_ITEMS = 9;
    GameManager gameManager;
    View game, menu;
    TextView lose,you_score,play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViewById(R.id.back).setOnClickListener(v->{
            onBackPressed();
            finish();
        });
        utils= new Utils(this);

        lose = findViewById(R.id.lose);
        you_score = findViewById(R.id.you_score);
        game = findViewById(R.id.game);
        menu = findViewById(R.id.menu);
        play = findViewById(R.id.play);

        gameManager = new GameManager(this);
        gameManager.setOnGameOverListner(score -> {
            animateView(0,()->{
                game.setVisibility(View.GONE);
            },game);
            menu.setVisibility(View.VISIBLE);
            animateView(1,null,menu);
            lose.setText("Вы проиграли");
            lose.setVisibility(View.VISIBLE);
            play.setText("Еще раз");
            play.setOnClickListener(v->{
                animateView(0,()->{
                    menu.setVisibility(View.GONE);
                },menu);
                game.setVisibility(View.VISIBLE);
                animateView(1,()->{gameManager.RestartGame();},game);
            });
            try {
                if (score > Integer.valueOf(utils.getScore())) {
                    utils.SaveScore(score);
                    you_score.setText("Новый рекорд: " + String.valueOf(score));
                }
            }catch (Exception e){
                utils.SaveScore(score);
                you_score.setText("Ваш рекорд: " + String.valueOf(score));
            }
        });

        if(!utils.getScore().equals(""))
            you_score.setText("Ваш рекорд: "+utils.getScore());
        else
            you_score.setText("");

        play.setOnClickListener(v->{

            animateView(0,()->{
                menu.setVisibility(View.GONE);
            },menu);

            game.setVisibility(View.VISIBLE);
            animateView(1,()->{gameManager.play();},game);
        });







    }
    private void animateView(float alpha, BottomSheet.OnEventListener onEventListener, View... view) {
        int  i = 0;

        for (View v:view) {
            int finalI = i;
            v.animate().alpha(alpha).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if(finalI == view.length-1 && onEventListener != null)
                        onEventListener.OnEnd();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            }).start();
            i+=1;
        }

    }

}
