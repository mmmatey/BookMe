package BookMe.com;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class IskanjeI extends Activity {

	public IskanjeI() {
		// TODO Auto-generated constructor stub
	}
	
	EditText _naslov;
	EditText _avtor;
	EditText _leto;
	
	Spinner _jezik;
	Spinner _gradivo;
	Button  _iskanje;
	
	RestAsyncTask rat;
	String response;
	public static ArrayList<Book> rezultat = new ArrayList<Book>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.izbirno);
		
		_naslov=(EditText)findViewById(R.id.EditText01);
		_avtor=(EditText)findViewById(R.id.editText1);
		_leto=(EditText)findViewById(R.id.EditText02);
		
		_jezik=(Spinner)findViewById(R.id.spinner2);
		_gradivo=(Spinner)findViewById(R.id.spinner1);
		
		_iskanje=(Button)findViewById(R.id.button1);
		
		_iskanje.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String gradivo= _gradivo.getSelectedItem().toString();
				String jezik =_jezik.getSelectedItem().toString();
				String naslov =_naslov.getText().toString();
				String avtor=_avtor.getText().toString();
				String leto = _leto.getText().toString();
				
				
				
				sendCommand("iskanje/"+(("").equals(avtor) ? "%20" : avtor)+"/"+(("").equals(naslov)? "%20" : naslov)+
						"/"+(("").equals(leto) ? "%20" : leto)+"/"+(("").equals(jezik)? "%20" : jezik)+"/"+(("").equals(gradivo)? "%20" : gradivo)+"/");
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
		                //knjiga._Dostop=mtUsers.getJSONObject(i).getString("dostop");
		                knjiga._Vrsta=mtUsers.getJSONObject(i).getString("vrsta");
		                knjiga._Naslov=mtUsers.getJSONObject(i).getString("naslov");
		                knjiga._Leto=mtUsers.getJSONObject(i).getString("leto");
		                rezultat.add(knjiga);
		                
		            }
					intent.putExtra("Objekt", 2);
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
		
			//com = com.replace(" ", "%20");
	
			rat = new RestAsyncTask(Values.restURL + com);
			rat.execute();
			response=rat.r.response;
	}

}
