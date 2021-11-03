package com.haku1806.bubblegame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    Random random = new Random();
    TextView txtScore;
    ViewGroup.LayoutParams params;
    LinearLayout layoutBubble;
    Button btnCreateBubble1, btnCreateBubble2, btnCreateBubble3;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        // Ve random 6 qua bong
        btnCreateBubble1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i <= random.nextInt(6);i++){
                    processAnim();
                }
            }
        });

        btnCreateBubble2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i <= random.nextInt(9);i++){
                    processAnim();
                }
            }
        });

        btnCreateBubble3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i <= random.nextInt(12);i++){
                    processAnim();
                }
            }
        });
    }

    private void addControls() {
        btnCreateBubble1 = findViewById(R.id.btn1);
        btnCreateBubble2 = findViewById(R.id.btn2);
        btnCreateBubble3 = findViewById(R.id.btn3);
        layoutBubble = findViewById(R.id.layoutBubble);
        txtScore = findViewById(R.id.txtScore);

        params = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void processAnim() {
        ImageView img = getImageView();
        img.setBackground(getDrawable());
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutBubble.removeView(view);
                txtScore.setText("Score: " + (score+= 3));
            }
        });

        objectAnimator =(ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.bubble);
        objectAnimator.setDuration(random.nextInt(3000));
        objectAnimator.setTarget(img);
        layoutBubble.addView(img,params);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                layoutBubble.removeAllViews();
                score = 0;
                Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                txtScore.setText("Score: 0");
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        objectAnimator.start();

    }

    private Drawable getDrawable() {
        Drawable drawable;
        int i = random.nextInt(6);
        switch (i){
            case 0:
                drawable = getResources().getDrawable(R.drawable.bubble0001);
                break;
            case 1:
                drawable = getResources().getDrawable(R.drawable.bubble0002);
                break;
            case 2:
                drawable = getResources().getDrawable(R.drawable.bubble0003);
                break;
            case 3:
                drawable = getResources().getDrawable(R.drawable.bubble0004);
                break;
            case 4:
                drawable = getResources().getDrawable(R.drawable.bubble0005);
                break;
            case 5:
                drawable = getResources().getDrawable(R.drawable.bubble0006);
                break;

            default:
                drawable = getResources().getDrawable(R.drawable.bubble0001);
                break;
        }
        return drawable;
    }

    private ImageView getImageView() {
        ImageView img = new ImageView(MainActivity.this);
        img.setX(random.nextInt(1000));
        return img;
    }


}