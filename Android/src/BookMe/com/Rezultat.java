package BookMe.com;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Rezultat extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rezultati);
		
		Intent i = getIntent();
		
		String izbira= i.getExtras().get("Objekt").toString();
		
		if(izbira=="1")
		{
			for (Book book : IskanjeO.rezultat) {
				Log.d("REST","Prenos uspel");
			}
			
			ListView lv = (ListView) findViewById(R.id.ListView01);
			String[] lv_arr = {};

			//lv_arr = (String[]) IskanjeO.rezultat.toArray(new String[0]);
			lv.setAdapter(new ArrayAdapter<Book>(Rezultat.this,
					android.R.layout.simple_list_item_1, IskanjeO.rezultat));
		}
		else
		{
			for (Book book : IskanjeI.rezultat) {
				Log.d("REST","Prenos uspel");
			}
			
			ListView lv = (ListView) findViewById(R.id.ListView01);
			String[] lv_arr = {};

			//lv_arr = (String[]) IskanjeO.rezultat.toArray(new String[0]);
			lv.setAdapter(new ArrayAdapter<Book>(Rezultat.this,
					android.R.layout.simple_list_item_1, IskanjeI.rezultat));
		}
		
		
        
	}

	
}
