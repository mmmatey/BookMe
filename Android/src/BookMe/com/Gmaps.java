package BookMe.com;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class Gmaps extends MapActivity {

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.maps);
	    MapView mapView = (MapView) findViewById(R.id.map_view);
	    mapView.setBuiltInZoomControls(true);
	    mapView.setSatellite(true);
	    mapView.setStreetView(true); // Street View
	    mapView.setTraffic(true); // Traffic View
	}
}
