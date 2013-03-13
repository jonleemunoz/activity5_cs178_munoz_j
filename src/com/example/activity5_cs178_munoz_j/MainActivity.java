package com.example.activity5_cs178_munoz_j;


import com.example.cs178activity5_munoz_j.Criteria;
import com.example.cs178activity5_munoz_j.GoogleMap;
import com.example.cs178activity5_munoz_j.LatLng;
import com.example.cs178activity5_munoz_j.Location;
import com.example.cs178activity5_munoz_j.LocationManager;
import com.example.cs178activity5_munoz_j.MainActivity;
import com.example.cs178activity5_munoz_j.MapFragment;
import com.example.cs178activity5_munoz_j.MarkerOptions;
import com.example.cs178activity5_munoz_j.Override;
import com.example.cs178activity5_munoz_j.String;
import com.example.cs178activity5_munoz_j.TextView;
import com.example.cs178activity5_munoz_j.View;
import com.sibi.locationmapsactivity.R;

import android.location.LocationListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements LocationListener{


	private GoogleMap map;
	TextView tv;
	LocationManager localmanager;
	Criteria crit = new Criteria();
	String prov, localprint;
	Location local;
	Button start, stop;
	private int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.btnStart);
		stop = (Button) findViewById(R.id.btnStop);
		localmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		prov = localmanager.getBestProvider(crit, false);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		View.OnClickListener listenbuttons = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(((Button)v).getId() == start.getId()){
					localmanager.requestLocationUpdates(prov, 10000, (float)10.0, MainActivity.this);					
				}
				else if(((Button)v).getId() == stop.getId()){
					localmanager.removeUpdates(MainActivity.this);
					local = localmanager.getLastKnownLocation(prov);
					Toast.makeText(MainActivity.this, "Last Location: " 
							+ local.getLatitude() + ", " + local.getLongitude() + ".",
							Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		start.setOnClickListener(listenbuttons);
		stop.setOnClickListener(listenbuttons);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	public void showUpdate(){
		LatLng temp = new LatLng(local.getLatitude(), local.getLongitude());
		map.addMarker(new MarkerOptions().position(temp).title("Location # " + (count+1)));
		count++;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(local == null || (location.getLatitude() != local.getLatitude() && 
				location.getLongitude() != local.getLongitude())) {
			local = location;
			showUpdate();
			Toast.makeText(this, "You are in: " + local.getLatitude() + ", " + 
					local.getLongitude(), Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(this, "You are still in the same location.", Toast.LENGTH_SHORT).show();
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
		
	}  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
