package br.exemplocountdowntimer;

import java.util.Calendar;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyCountDownTimer extends CountDownTimer {
	private Context context;
	private TextView tv;
	private long timeInFuture;
	
	
	public MyCountDownTimer(Context context, TextView tv, long timeInFuture, long interval){
		super(timeInFuture, interval);
		this.context = context;
		this.tv = tv;
	}
	
	@Override
	public void onTick(long millisUntilFinished) {
		Log.i("Script", "Timer: "+millisUntilFinished);
		timeInFuture = millisUntilFinished;
		tv.setText(getCorretcTimer(true, millisUntilFinished)+":"+getCorretcTimer(false, millisUntilFinished));
	}

	@Override
	public void onFinish() {
		timeInFuture -= 1000;
		tv.setText(getCorretcTimer(true, timeInFuture)+":"+getCorretcTimer(false, timeInFuture));
		
		Toast.makeText(context, "FINISH!", Toast.LENGTH_SHORT).show();
	}
	
	
	private String getCorretcTimer(boolean isMinute, long millisUntilFinished){
		String aux;
		int constCalendar = isMinute ? Calendar.MINUTE : Calendar.SECOND;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millisUntilFinished);
		
		aux = c.get(constCalendar) < 10 ? "0"+c.get(constCalendar) : ""+c.get(constCalendar);
		return(aux);
	}
	
}
