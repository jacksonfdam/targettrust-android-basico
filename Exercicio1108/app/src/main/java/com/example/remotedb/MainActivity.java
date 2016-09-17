package com.example.remotedb;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private JSONParser jsonParser;
	//10.0.2.2 is the address used by the Android emulators to refer to the host address
	// change this to the IP of another host if required
	private static String ageURL = "http://10.0.2.2/android_api.php";
	private static String getAge = "getAge";
	private static String jsonResult = "success";
	String uname;
	String age_res;
	TextView Results;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Invoke the Json Parser
        jsonParser = new JSONParser();

    
     final EditText Enter = (EditText) findViewById(R.id.Enter);
     Results = (TextView) findViewById(R.id.Results);
     Button GetAge = (Button) findViewById(R.id.GetAge);
    
    //Get Age Button
    GetAge.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            
        	//Get the contents of the edit text
        	uname = Enter.getText().toString();
        	
            //Pass the name to the JSON method and create a JSON object from the return
        	JSONObject json = getAge(uname);
        	
			// check the success of the JSON call
			try {
				if (json.getString(jsonResult) != null) {
					Results.setText("");
					String res = json.getString(jsonResult); 
					if(Integer.parseInt(res) == 1){
						//If it's a success create a new JSON object for the user element 
						JSONObject json_user = json.getJSONObject("user");
						//Set the results text to the age from the above JSON object
						Results.setText("User Age: " + json_user.getString("age"));
						
					}else{
						//If the user could not be found
						Results.setText("User could not be found");
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	
        	
        }
        
        
    });
    
    }
    
    //The below passes the tag and the user name over to the JSON parser class
    public JSONObject getAge(String name){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", getAge));
		params.add(new BasicNameValuePair("name", name));
		JSONObject json = jsonParser.getJSONFromUrl(ageURL, params);
		return json;
	}

}
