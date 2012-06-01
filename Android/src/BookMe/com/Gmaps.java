package BookMe.com;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Gmaps extends MapActivity {

private MapView mapView;
    
    private static int lat = 46668287;
    private static int lon = 15380859;
    public String dostopnost="";
    public int steviloVseh=0;
    public int steviloIzposojenih=0;
    public int naVoljo=0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        Intent in = getIntent();
        lat=Integer.parseInt(in.getStringExtra("X"));
        lon=Integer.parseInt(in.getStringExtra("Y"));
        dostopnost=in.getStringExtra("Dostop");
        steviloVseh=Integer.parseInt(in.getStringExtra("S1"));
        steviloIzposojenih=Integer.parseInt(in.getStringExtra("S2"));
        naVoljo=steviloVseh-steviloIzposojenih;
        
        
        mapView = (MapView) findViewById(R.id.map_view);       
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
        CustomItemizedOverlay itemizedOverlay = 
             new CustomItemizedOverlay(drawable, this);
        
        GeoPoint point = new GeoPoint(lat, lon);
        OverlayItem overlayitem = 
             new OverlayItem(point, "Dostopnost:", dostopnost+" Na voljo:"+naVoljo);
        
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        
        MapController mapController = mapView.getController();
        
        mapController.animateTo(point);
        mapController.setZoom(16);
        
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
