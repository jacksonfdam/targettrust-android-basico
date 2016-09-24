package br.exemplogooglemapsv2;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

public class GPSMainActivity extends FragmentActivity implements LocationListener {
	private SupportMapFragment mapFrag;
	private GoogleMap map;
	private LocationManager locationManager;
	private boolean allowNetwork;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpsmain);
		
		
		GoogleMapOptions options = new GoogleMapOptions();
		options.zOrderOnTop(true);
		mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		map = mapFrag.getMap();
		configMap();
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		allowNetwork = true;
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			Intent it = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(it);
		}
		else{
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}
	}
	
	
	@Override
	public void onPause(){
		super.onPause();
		locationManager.removeUpdates(this);
		
	}
	
	
	public void configMap(){
		map = mapFrag.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		LatLng latLng = new LatLng(-20.230521, -40.314816);
		configLocation(latLng);
	}
	
	
	public void configLocation(LatLng latLng){
		CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(8).bearing(0).tilt(90).build();
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
		
		map.setMyLocationEnabled(true);
		map.animateCamera(update);
		MyLocation myLocation = new MyLocation();
		map.setLocationSource(myLocation);
		myLocation.setLocation(latLng);
	}
	
	
	
	public class MyLocation implements LocationSource{
		private OnLocationChangedListener listener;
		
		@Override
		public void activate(OnLocationChangedListener listener) {
			this.listener = listener;
			Log.i("Script", "activate()");
		}

		@Override
		public void deactivate() {
			Log.i("Script", "deactivate()");
		}
		
		
		public void setLocation(LatLng latLng){
			Location location = new Location(LocationManager.GPS_PROVIDER);
			location.setLatitude(latLng.latitude);
			location.setLongitude(latLng.longitude);
			
			if(listener != null){
				listener.onLocationChanged(location);
			}
		}
	}



	@Override
	public void onLocationChanged(Location location) {
		if(location.getProvider().equals(LocationManager.GPS_PROVIDER)){
			allowNetwork = false;
		}
		
		if(allowNetwork || location.getProvider().equals(LocationManager.GPS_PROVIDER)){
			configLocation(new LatLng(location.getLatitude(), location.getLongitude()));
		}
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
}
