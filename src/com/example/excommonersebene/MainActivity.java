package com.example.excommonersebene;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.excommonersebenelib.*;

public class MainActivity extends Activity implements LocationListener,OnMarkerClickListener,DialogInterface.OnClickListener{

	private GoogleMap map;
	private final LatLng location_maritiusLatLng = new LatLng(-20.24347755, 57.49033743);
	Marker[] meraMarker;
	ArrayList<BusStop> bustops;
	ArrayList<Route> routes;
	 MarkerDataSource data;
	Context context;
	CharSequence text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();	
		map.setMyLocationEnabled(true);
		
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location_maritiusLatLng, 14);
		map.animateCamera(update);
		
		BusStop b1,b2,b3,b4,b5,b6,b7,b8;
		Route r1,r2,r3,r4;
		
		routes = new ArrayList<Route>();
		bustops = new ArrayList<BusStop>();
		
		bustops.add(b1 = new BusStop(1, -20.23215, 57.498530));
		bustops.add(b2 = new BusStop(2, -20.232498, 57.497736));
		bustops.add(b3 = new BusStop(3, -20.242982, 57.491942));
		bustops.add(b4 = new BusStop(4, -20.242720, 57.491830));
		bustops.add(b5 = new BusStop(5,-20.243214, 57.490811));
		bustops.add(b6 = new BusStop(6,-20.231667, 57.501765));
		bustops.add(b7 = new BusStop(7,-20.229226, 57.503385));
		bustops.add(b8 = new BusStop(8,-20.243586, 57.488906));
		
		routes.add(r1 = new Route(141,"Port Louis","Bord Cascade"));
		routes.add(r2 = new Route(173,"Curepipe","St-Pierre"));
		routes.add(r3 = new Route(198,"Port Louis","Maheborg"));
		routes.add(r4 = new Route(222,"Rose-Hill","Rempart"));
		
		r1.addBusStop(b1);
		r1.addBusStop(b2);
		r1.addBusStop(b3);
		r4.addBusStop(b1);
		r2.addBusStop(b4);
		r2.addBusStop(b5);
		r4.addBusStop(b2);
		r4.addBusStop(b8);
		r4.addBusStop(b3);
		r4.addBusStop(b7);
		r4.addBusStop(b6);
		r1.addBusStop(b5);
		r1.addBusStop(b6);
		r3.addBusStop(b7);
		r3.addBusStop(b3);
		r1.addBusStop(b7);
		r1.addBusStop(b8);
		
		map.setOnMarkerClickListener(this);
		
		meraMarker = new Marker[bustops.size()];
		for(int i = 0;i<bustops.size();i++){
			meraMarker[i] = map.addMarker(new MarkerOptions().position(new LatLng(bustops.get(i).x, bustops.get(i).y)).title("Bus Stop here"));
		}

		  LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
	      String provider = lm.getBestProvider(new Criteria(), true);
	        
	      if (provider == null) {
	    	  onProviderDisabled(provider);
	      }
	        data = new MarkerDataSource(context);
	        try {
	           data.open();
	           List<MyMarkerObj> m = data.getMyMarkers();
		        for (int i = 0; i < m.size(); i++) {
		            String[] slatlng =  m.get(i).getPosition().split(" ");
		            LatLng lat = new LatLng(Double.valueOf(slatlng[0]), Double.valueOf(slatlng[1]));
		            //LatLng lat = new LatLng(0,0);
		            map.addMarker(new MarkerOptions()
		                    .title(m.get(i).getTitle())
		                    .snippet(m.get(i).getSnippet())
		                    .position(lat)
		                    );         
		        }
	           
	        } catch (Exception e) {
	            Log.i("hello", "hello");
	        }
	         
	        //add marker
	        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

	            public void onInfoWindowClick(Marker marker) {
	                marker.remove();
	                data.deleteMarker(new MyMarkerObj(marker.getTitle(), marker.getSnippet(), marker.getPosition().latitude + " " + marker.getPosition().longitude));
	            }
	        });
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public  boolean onMarkerClick (Marker marker){
		for(int i = 0;i<meraMarker.length;i++){
			if(marker.equals(meraMarker[i])){
				ArrayList<Route> passingroutes = bustops.get(i).getRoutes(routes);
				 context = getApplicationContext();
				
				final CharSequence[] items= new CharSequence[passingroutes.size()];
				for(int j = 0;j<passingroutes.size();j++){
					items[j] = passingroutes.get(j).no + " " + passingroutes.get(j).from + " " + passingroutes.get(j).to;
				}
				 
				AlertDialog ad = new AlertDialog.Builder(this)
				.setIcon(R.drawable.map_ic)
				.setTitle("#"+ bustops.get(i).id + " : Select your bus")
				.setItems(items, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "Selected: "+items[which], Toast.LENGTH_SHORT).show();
					}
				})
				.setCancelable(true)
				.create();
				ad.show();

			}
		}
		return true;
		
	}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			
				// TODO Auto-generated method stub
	
		}

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
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
