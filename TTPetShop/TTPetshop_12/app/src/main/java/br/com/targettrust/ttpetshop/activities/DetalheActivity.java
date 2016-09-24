/**
 * Created by Jackson F. de A. Mafra on 15/04/2015.
 * jackson@targettrust.com.br / jacksonfdam@gmail.com
 * Targettrust Treinamento e Tecnologia
 * Formação Desenvolvedor Mobile
 * Curso Android Básico
 *
 */

package br.com.targettrust.ttpetshop.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.transition.AutoTransition;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import br.com.targettrust.ttpetshop.R;
import br.com.targettrust.ttpetshop.utils.DebugActivity;
import br.com.targettrust.ttpetshop.utils.RoundedImageView;

public class DetalheActivity extends DebugActivity {

    private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);

        // Initially hide the content view.
        mContentView.setVisibility(View.GONE);

        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);




    }

    @Override
    protected void onStart() {
        super.onStart();
        crossfade();
        
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        carregar();
                    }
                }, 2000);
            }
        });

    }

    private void carregar() {

        String extra_nome = getIntent().getStringExtra("nome");
        String extra_arquivo = getIntent().getStringExtra("arquivo");

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);

        RoundedImageView foto = (RoundedImageView) findViewById(R.id.foto);



        TextView  nome = (TextView) findViewById(R.id.nome);
        TextView  descricao = (TextView) findViewById(R.id.descricao);

        AssetManager manager = getAssets();
        /*
        * String[] files = assetManager.list("");  // files from 'assets' directory
        * String[] files = assetManager.list("Files"); // files from 'assets/Files'
        */

        nome.setText(extra_nome);

        try {
            String[] files = manager.list("");

            for(int i=0; i<files.length; i++){
                Log.v("MainActivity", "\n Arquivo :" + i + " Nome => " + files[i]);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        InputStream input;
        try {
            input = manager.open(extra_arquivo + ".txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            String text = new String(buffer);
            descricao.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream ims = manager.open(extra_arquivo + ".jpg");
            int width = 300;
            int height = 300;
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
            Bitmap bmp = BitmapFactory.decodeStream(ims);
            bmp = Bitmap.createScaledBitmap(bmp, width,height, true);
            Bitmap resized = ThumbnailUtils.extractThumbnail(bmp, width, height);
            foto.setImageBitmap(resized);
            foto.setScaleType(ImageView.ScaleType.CENTER);
            foto.setLayoutParams(parms);
            foto.startAnimation(fadeInAnimation);
        } catch(IOException ex) {
            return;
        }


    }
    private void crossfade() {
        /*
        * Ver documentação
        * http://developer.android.com/training/animation/crossfade.html
        *
        */

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        mContentView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLoadingView.setVisibility(View.GONE);
                    }
                });
    }
}
