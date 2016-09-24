package com.example.exemploviewpersonalizada;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class EmailText extends EditText {
	
	public EmailText(Context context){
		super(context);
	}
	public EmailText(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	
	public boolean isEmail(){
		String expressaoRegular = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String email = getText().toString();
		
		return(email.matches(expressaoRegular));
	}
}
