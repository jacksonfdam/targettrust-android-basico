package br.exemplogooglemapsv2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	private SupportMapFragment mapFrag;
	private GoogleMap map;
	private Marker marker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GoogleMapOptions options = new GoogleMapOptions();
		options.zOrderOnTop(true);
		
		mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		
		map = mapFrag.getMap();
		
		/*mapFrag = SupportMapFragment.newInstance(options);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.llContainer, mapFrag);
		ft.commit();*/
		
		configMap();
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		/*new Thread(){
			public void run(){
				while(mapFrag.getMap() == null){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				runOnUiThread(new Runnable(){
					public void run(){
						configMap();
					}
				});
			}
		}.start();*/
	}
	
	
	public void configMap(){
		map = mapFrag.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		LatLng latLng = new LatLng(-23.564224, -46.653156);
		
		CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(18).bearing(0).tilt(90).build();
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
		
		//map.moveCamera(update);
		map.animateCamera(update, 3000, new CancelableCallback(){
			@Override
			public void onCancel() {
				Log.i("Script", "CancelableCallback.onCancel()");
			}

			@Override
			public void onFinish() {
				Log.i("Script", "CancelableCallback.onFinish()");
			}
		});
		
		// MARKERS
			//customAddMarker(new LatLng(-23.564224, -46.653156), "Marcador 1", "O Marcador 1 foi reposicionado");
			//customAddMarker(new LatLng(-23.564205, -46.653102), "Marcador 2", "O Marcador 2 foi reposicionado");
			
			map.setInfoWindowAdapter(new InfoWindowAdapter(){

				@Override
				public View getInfoContents(Marker marker) {
					TextView tv = new TextView(MainActivity.this);
					tv.setText(Html.fromHtml("<b><font color=\"#ff0000\">"+marker.getTitle()+":</font></b> "+marker.getSnippet()));
					
					return tv;
				}

				@Override
				public View getInfoWindow(Marker marker) {
					LinearLayout ll = new LinearLayout(MainActivity.this);
					ll.setPadding(20, 20, 20, 20);
					ll.setBackgroundColor(Color.GREEN);
					
					TextView tv = new TextView(MainActivity.this);
					tv.setText(Html.fromHtml("<b><font color=\"#ffffff\">"+marker.getTitle()+":</font></b> "+marker.getSnippet()));
					ll.addView(tv);
					
					Button bt = new Button(MainActivity.this);
					bt.setText("Botão");
					bt.setBackgroundColor(Color.RED);
					bt.setOnClickListener(new Button.OnClickListener(){

						@Override
						public void onClick(View v) {
							Log.i("Script", "Botão clicado");
						}
						
					});
					
					ll.addView(bt);
					
					return ll;
				}
				
			});
		
		
		// EVENTS
			map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
				@Override
				public void onCameraChange(CameraPosition cameraPosition) {
					Log.i("Script", "setOnCameraChangeListener()");
					
					if(marker != null){
						marker.remove();
					}
					customAddMarker(new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude), "1: Marcador Alterado", "O Marcador foi reposicionado");
					
				}
			});
			
			map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
				@Override
				public void onMapClick(LatLng latLng) {
					Log.i("Script", "setOnMapClickListener()");
					if(marker != null){
						marker.remove();
					}
					customAddMarker(new LatLng(latLng.latitude, latLng.longitude), "2: Marcador Alterado", "O Marcador foi reposicionado");
					
				}
			});
			
			map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
				@Override
				public boolean onMarkerClick(Marker marker) {
					Log.i("Script", "3: Marker: "+marker.getTitle());
					return false;
				}
			});
			
			map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
				@Override
				public void onInfoWindowClick(Marker marker) {
					Log.i("Script", "4: Marker: "+marker.getTitle());
				}
			});
	}
	
	
	public void customAddMarker(LatLng latLng, String title, String snippet){
		MarkerOptions options = new MarkerOptions();
		options.position(latLng).title(title).snippet(snippet).draggable(true);
		options.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
		
		marker = map.addMarker(options);
	}
}





