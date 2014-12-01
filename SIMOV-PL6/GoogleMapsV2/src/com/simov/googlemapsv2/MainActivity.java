package com.simov.googlemapsv2;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	// Google Map
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			initilizeMap();

			// change map type
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

			// show current location
			googleMap.setMyLocationEnabled(true);

			// latitude and longitude
			double latitude = 38.766667;
			double longitude = -9.15;

			// create marker
			MarkerOptions marker = new MarkerOptions().position(
					new LatLng(latitude, longitude)).title("Portugal");

			// marker color
			marker.icon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

			// adding marker
			googleMap.addMarker(marker);

			// show zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(true);
			
			// show compass
			googleMap.getUiSettings().setCompassEnabled(true);
			
			// my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Load Map
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}
}
