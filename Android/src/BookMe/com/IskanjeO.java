package BookMe.com;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IskanjeO extends Activity {

	Spinner _jezik;
	Spinner _gradivo;
	EditText _niz;
	Button _iskanje;
	
	static ArrayList<Book> rezultat = new ArrayList<Book>();
	
	RestAsyncTask rat;
	String response;
	private String restURL = "http://164.8.118.120/BookMe/Service1.svc/json/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iskanjeo);
		
		_jezik=(Spinner)findViewById(R.id.jezi);
		_gradivo=(Spinner)findViewById(R.id.grad);
		_niz=(EditText)findViewById(R.id.beseda);
		
		_iskanje=(Button)findViewById(R.id.gumbZaIskanje);
		
		_iskanje.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String gradivo= _gradivo.getSelectedItem().toString();
				String jezik =_jezik.getSelectedItem().toString();
				String niz=_niz.getText().toString();
				
				sendCommand("iskanje/"+gradivo+"/"+jezik+"/"+niz);
				//Toast.makeText(getBaseContext(),response , Toast.LENGTH_LONG).show();
				response=rat.doInBackground(null);
				
				Log.d("REST", "Response: "+response);
				
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
					intent.putExtra("Objekt", 1);
					startActivity(intent);
					
//					for (Book book : rezultat) 
//					{
//						Log.d("REST", book.toString());
//					}
					
				}
				
				catch(Exception ex)
				{
					Log.d("REST", "Napaka:"+ex.toString());
				}
			
			}
		});
		
	}

	private void sendCommand(String com) {
		
		com = com.replace(" ", "%20");

		rat = new RestAsyncTask(restURL + com);
		rat.execute();
		response=rat.r.response;
	}
}
