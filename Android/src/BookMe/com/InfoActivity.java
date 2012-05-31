package BookMe.com;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class InfoActivity extends Activity {

	TextView _avtor;
	TextView _naslov;
	TextView _leto;
	TextView _vrstaG;
	TextView _jezik;
	RestAsyncTask rat;
	String response;
	String izbira="";
	String avtor="";
	String leto="";
	String gradivo="";
	String naslov="";
	String jezik="";
	String id="";
	ListView lv;
	
	public static ArrayList<Library> knjiznice= new ArrayList<Library>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		Intent in = getIntent();
		
		naslov=in.getStringExtra("Naslov");
		avtor=in.getStringExtra("Avtor");
		leto=in.getStringExtra("Leto");
		gradivo=in.getStringExtra("Gradivo");
		jezik=in.getStringExtra("Jezik");
		id=in.getStringExtra("ID");
		
		_avtor=(TextView)findViewById(R.id.textView6);
		_naslov=(TextView)findViewById(R.id.textView7);
		_leto=(TextView)findViewById(R.id.textView8);
		_vrstaG=(TextView)findViewById(R.id.textView9);
		_jezik=(TextView)findViewById(R.id.textView10);
		 lv = (ListView) findViewById(R.id.listView1);
		 
		 _avtor.setText(avtor);
		 _naslov.setText(naslov);
		 _leto.setText(leto);
		 _vrstaG.setText(gradivo);
		 _jezik.setText(jezik);
		 
		Log.i("REST", id);
		sendCommand("knjiznicesknjigo/"+id);
		response=rat.doInBackground(null);
		try{
			JSONArray mtUsers = new JSONArray(response);
	        String ime="";
	        
	        knjiznice.clear();
			for(int i=0;i<mtUsers.length();i++)
            {
                //ime= mtUsers.getString(i);
                //ime=mtUsers.getJSONObject(i).getString("ID");
                //Log.d("REST", "ime->:"+ime);
                
                Library knjiznica = new Library();
                knjiznica.kraj=mtUsers.getJSONObject(i).getString("kraj");
                knjiznica.naziv=mtUsers.getJSONObject(i).getString("naziv");
                knjiznica.stevilo=mtUsers.getJSONObject(i).getString("stevilo");
                //knjiga._Dostop=mtUsers.getJSONObject(i).getString("dostop");
                knjiznica.X=mtUsers.getJSONObject(i).getString("X");
                knjiznica.Y=mtUsers.getJSONObject(i).getString("Y");
                knjiznica.steviloIzposojenih=mtUsers.getJSONObject(i).getString("stIzposojenih");
                knjiznica.dostopnost=mtUsers.getJSONObject(i).getString("dostopnost");
                knjiznice.add(knjiznica);
                
            }
			String[] lv_arr = {};

			//lv_arr = (String[]) IskanjeO.rezultat.toArray(new String[0]);
			lv.setAdapter(new ArrayAdapter<Library>(InfoActivity.this,
					R.layout.infolist, knjiznice));
			
		}
		
		catch(Exception ex)
		{
			Log.d("REST", "Napaka:"+ex.toString());
		}
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
					Intent in = new Intent(getBaseContext(), Gmaps.class);
					in.putExtra("X", knjiznice.get(arg2).X);
					in.putExtra("Y", knjiznice.get(arg2).Y);
					in.putExtra("Dostop", knjiznice.get(arg2).dostopnost);
					in.putExtra("S1", knjiznice.get(arg2).stevilo);
					in.putExtra("S2", knjiznice.get(arg2).steviloIzposojenih);
					startActivity(in);
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
