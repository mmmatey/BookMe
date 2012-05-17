package BookMe.com;

import BookMe.com.RestAsyncTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class DodajanjeKnjig extends Activity {

	public DodajanjeKnjig() {
		// TODO Auto-generated constructor stub
	}

	EditText _naslov;
	EditText _avtor;
	EditText _leto;
	Spinner _zalozba;
	Spinner _gradivo;
	Spinner _jezik;
	Button _dodaj;
	RestAsyncTask rat;
	private String restURL = "http://164.8.118.62/BookMe/Service1.svc/json/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dodajanje);
		
		_naslov=(EditText)findViewById(R.id.multiAutoCompleteTextView1);
		_avtor=(EditText)findViewById(R.id.MultiAutoCompleteTextView02);
		_leto=(EditText)findViewById(R.id.MultiAutoCompleteTextView01);
		
		_zalozba=(Spinner)findViewById(R.id.Spinner02);
		_gradivo=(Spinner)findViewById(R.id.spinner1);
		_jezik=(Spinner)findViewById(R.id.Spinner01);
		
		_dodaj=(Button)findViewById(R.id.button1);
		
		
		_dodaj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO tukaj pride string za dodajanje knjige
//				http://localhost:62394/Service1.svc/json/DODAJ/naslov/Desa/poez/1990/sloven/mladinska
				
				String naslov=_naslov.getText().toString();
				String avtor=_avtor.getText().toString();
				String leto = _leto.getText().toString();
				
				String jezik = _jezik.getSelectedItem().toString();
				String gradivo=_gradivo.getSelectedItem().toString();
				String zalozba=_zalozba.getSelectedItem().toString();
				
				sendCommand("DODAJ/"+naslov+"/"+avtor+"/"+gradivo+"/"+leto+"/"+jezik+"/"+zalozba);
				
				Toast.makeText(getApplicationContext(), "Knjiga uspešno dodana!", Toast.LENGTH_LONG).show();
			}
		});
		
		
	}
	
	private void sendCommand(String com) {
		
		com = com.replace(" ", "%20");

		rat = new RestAsyncTask(restURL + com);
		rat.execute();
	}

}
