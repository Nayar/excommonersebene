package com.example.excommonersebene;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

import com.example.excommonersebenelib.*;

public class MainActivity extends Activity {

	private GoogleMap map;
	private final LatLng location_maritiusLatLng = new LatLng(-20.24347755, 57.49033743);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		/*BusStops bustops = new BusStops();
		bustops.add(1,20.1625, 58.2903);
		bustops.add(2,5, 5);
		bustops.add(3,-26.4545, 7.125);
		bustops.add(4,-25.4545, 3.125);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		BusStop current = bustops.head;
		while(current != null){
			map.addMarker(new MarkerOptions().position(new LatLng(current.x, current.y)).title("find me here"));
			current = current.next;
		}*/
		
		BusStop b1,b2,b3,b4,b5,b6,b7,b8;
		Route r1,r2,r3;
		ArrayList<Route> routes = new ArrayList<Route>();
		ArrayList<BusStop> bustops = new ArrayList<BusStop>();
		bustops.add(b1 = new BusStop(1, -20.23215, 57.498530));
		bustops.add(b2 = new BusStop(2, -20.232498, 57.497736));
		bustops.add(b3 = new BusStop(3, -20.242982, 57.491942));
		bustops.add(b4 = new BusStop(4, -20.242720, 57.491830));
		bustops.add(b5 = new BusStop(5,-20.243214, 57.490811));

		bustops.add(b6 = new BusStop(6,-20.231667, 57.501765));

		bustops.add(b7 = new BusStop(7,-20.229226, 57.503385));

		bustops.add(b8 = new BusStop(8,-20.243586, 57.488906));
		r1 = new Route(1);
		r2 = new Route(2);
		//r1.addBusStop(b1);
		
		for(int i = 0;i<bustops.size();i++){
			map.addMarker(new MarkerOptions().position(new LatLng(bustops.get(i).x, bustops.get(i).y)).title("Bus Stop here"));
		}
		//MeraDatabase mDbHelper = new MeraDatabase(getBaseContext());
		//SQLiteDatabase db = MeraDatabase.;
		
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
