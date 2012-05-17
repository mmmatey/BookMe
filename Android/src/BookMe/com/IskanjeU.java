package BookMe.com;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class IskanjeU extends Activity {

	Button _isci;
	Button _and;
	Button _or;
	AutoCompleteTextView _ukaz;
	
	static ArrayList<Book> rezultat = new ArrayList<Book>();
	
	RestAsyncTask rat;
	String response;
	String ukaz;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ukazno);
		_ukaz=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		_isci=(Button)findViewById(R.id.button1);
		_and=(Button)findViewById(R.id.button2);
		_or=(Button)findViewById(R.id.Button01);
		
		_isci.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ukaz= _ukaz.getText().toString();
				
				sendCommand(ukaz);
				response=rat.doInBackground(null);
				
				try{
					JSONArray mtUsers = new JSONArray(response);
			        String ime="";
			        Intent intent = new Intent().setClass(getApplicationContext(),Rezultat.class);
			        rezultat.clear();
					for(int i=0;i<mtUsers.length();i++)
		            {
		                ime= mtUsers.getString(i);
		                ime=mtUsers.getJSONObject(i).getString("ID");
		                Log.d("REST", "ime->:"+ime);
		                
		                Book knjiga = new Book();
		                knjiga._Avtor=mtUsers.getJSONObject(i).getString("avtor");
		                knjiga._ID=mtUsers.getJSONObject(i).getString("ID");
		                knjiga._Jezik=mtUsers.getJSONObject(i).getString("jezik");
		                knjiga._Dostop=mtUsers.getJSONObject(i).getString("dostop");
		                knjiga._Vrsta=mtUsers.getJSONObject(i).getString("vrsta");
		                knjiga._Naslov=mtUsers.getJSONObject(i).getString("naslov");
		                knjiga._Leto=mtUsers.getJSONObject(i).getString("leto");
		                rezultat.add(knjiga);
		                
		            }
					intent.putExtra("Objekt", 3);
					startActivity(intent);
				}
				
				catch(Exception ex)
				{
					Log.d("REST", "Napaka:"+ex.toString());
				}
				
			}
		});

		_and.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				_ukaz.append(" AND ");
				
			}
		});
		
		_or.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				_ukaz.append(" OR ");
			}
		});
	}
	private void sendCommand(String com) {
		
		com = com.replace(" ", "%20");

		rat = new RestAsyncTask(Values.restURL + com);
		rat.execute();
		response=rat.r.response;
	}
}
