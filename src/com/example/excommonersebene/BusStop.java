package com.example.excommonersebene;
import com.google.android.gms.maps.model.LatLng;

public class BusStop {
	int id;
	double x,y;
	LatLng geoPoint;
	BusStop next;
	
	public BusStop(int id,double x,double y) {
		this.id = id;
		this.x = x;
		this.y = y;
		geoPoint = new LatLng(x, y);
	}
}
