package com.example.excommonersebene;

public class BusStops {
	BusStop head;
	
	public void add(int id,double x,double y){
		if(head == null){
			head = new BusStop(id,x,y);
			return;
		}
		BusStop current = head.next;
		while(current.next != null){
			current = current.next;
		}
		current.next = new BusStop(id,x, y);
	}
}
