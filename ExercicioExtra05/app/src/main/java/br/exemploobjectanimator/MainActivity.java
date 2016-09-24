package br.exemploobjectanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private boolean flag = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void efeitoXML(View view){
		ImageView img = (ImageView) findViewById(R.id.img);
		
		ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.animacao);
		anim.setTarget(img);
		
		if(flag){
			anim.start();
		}
		else{
			anim.reverse();
		}
		flag = !flag;
	}
	
	public void efeitoAPI(View view){
		ImageView img = (ImageView) findViewById(R.id.img);
		
		PropertyValuesHolder animacao1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
		PropertyValuesHolder animacao2 = PropertyValuesHolder.ofFloat("x", 0f, 30f);
		PropertyValuesHolder animacao3 = PropertyValuesHolder.ofFloat("Y", 0f, 30f);
		
		ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(img, animacao1, animacao2, animacao3);
		anim.setDuration(6000);
		
		/*ObjectAnimator anim = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
		anim.setDuration(2000);
		anim.setRepeatCount(2);
		anim.setRepeatMode(ObjectAnimator.INFINITE);*/
		
		anim.addListener(new AnimatorListenerAdapter(){
			@Override
			public void onAnimationEnd(Animator animation){
				Log.i("Script", "onAnimationEnd()");
			}
			@Override
			public void onAnimationStart(Animator animation){
				Log.i("Script", "onAnimationStart()");
			}
			@Override
			public void onAnimationRepeat(Animator animation){
				Log.i("Script", "onAnimationRepeat()");
			}
		});
		
		if(flag){
			anim.start();
		}
		else{
			anim.reverse();
		}
		flag = !flag;
	}
}
