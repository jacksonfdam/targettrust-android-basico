package br.com.targettrust.av0401;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView foto;
    Button botao;
    Animation animationAlpha = new AlphaAnimation(1.0f, 0.0f);
    Animation animationRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foto = (ImageView)findViewById(R.id.foto);
        botao = (Button)findViewById(R.id.botao);

        animationRotate = new AnimationUtils()
                .loadAnimation(this,R.anim.rotate_anim);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Via API*/
                animationAlpha.setFillAfter(false);
                animationAlpha.setDuration(500);
                animationAlpha.setRepeatCount(1);

                animationRotate.setFillAfter(false);
                animationRotate.setDuration(500);
                animationRotate.setRepeatCount(1);

                AnimationSet asAnimacoes = new AnimationSet(true);
                asAnimacoes.addAnimation(animationAlpha);
                asAnimacoes.addAnimation(animationRotate);


                animationAlpha.setAnimationListener(
                        new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.v("Main","Animation Start");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.v("Main","Animation Repeat");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.v("Main","Animation End");
                        //foto.startAnimation(animationRotate);
                        foto.setAlpha(0);
                        foto.setVisibility(View.GONE);
                    }
                });

                foto.startAnimation(asAnimacoes);
            }
        });
    }
}
