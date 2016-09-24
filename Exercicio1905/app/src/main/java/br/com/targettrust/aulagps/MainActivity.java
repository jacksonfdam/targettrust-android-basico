package br.com.targettrust.aulagps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	
	LocationListener listener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}
		@Override
		public void onProviderEnabled(String provider) {}
		@Override
		public void onProviderDisabled(String provider) {}
		
		@Override
		public void onLocationChanged(Location location) {
			
			tv.setText(location.getLatitude() 
					+ ", " + location.getLongitude() 
					+ " - " + location.getProvider() 
					+ ", " + location.getAccuracy());
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tvCoordenada);
        LocationManager manager = (LocationManager) getSystemService(
        		LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
        		1000, 
        		1, 
        		listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 
        		1000, 
        		1, 
        		listener);
        
    }

    
}
