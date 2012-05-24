package BookMe.com;

import java.util.Calendar;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

public class BookMeActivity extends TabActivity {
    /** Called when the activity is first created. */
	
	
	private RestClient restClient;
    private String restURL = "http://localhost/BookMe/Service1.svc/json/";
    Reader r = new Reader();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        Resources res = getResources(); 
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        //Dodajanej tab-ov
        intent = new Intent().setClass(this, IskanjeO.class);


        spec = tabHost.newTabSpec("IskanjeO").setIndicator("Osnovno Iskanje",
                          res.getDrawable(R.drawable.tab_iskanjei))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, IskanjeI.class);
        spec = tabHost.newTabSpec("iskanjeI").setIndicator("IskanjeI",
                          res.getDrawable(R.drawable.tab_iskanjei))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Prijava.class);
        spec = tabHost.newTabSpec("dodaj").setIndicator("Dodajanje",
                          res.getDrawable(R.drawable.tab_book))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, IskanjeU.class);
        spec = tabHost.newTabSpec("isciu").setIndicator("UkaznoI",
                          res.getDrawable(R.drawable.tab_iskanjei))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        
        
    }
}