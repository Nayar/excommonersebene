package com.example.excommonersebene;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private GoogleMap map;
	private final LatLng location_maritiusLatLng = new LatLng(-20.24347755, 57.49033743);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BusStops bustops = new BusStops();
		bustops.add(1,20.1625, 58.2903);
		bustops.add(2,5, 5);
		bustops.add(3,-26.4545, 7.125);
		bustops.add(4,-25.4545, 3.125);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		BusStop current = bustops.head;
		while(current != null){
			map.addMarker(new MarkerOptions().position(new LatLng(current.x, current.y)).title("find me here"));
			current = current.next;
		}
		
		
		
		//CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location_maritiusLatLng, 15);
		//map.animateCamera(update);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
