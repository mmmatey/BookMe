package BookMe.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class IskanjeO extends Activity {

	Spinner _jezik;
	Spinner _gradivo;
	EditText _niz;
	Button _iskanje;
	
	RestAsyncTask rat;
	private String restURL = "http://192.168.1.2/BookMe/Service1.svc/json/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iskanjeo);
		
		_jezik=(Spinner)findViewById(R.id.jeziki);
		_gradivo=(Spinner)findViewById(R.id.gradivo);
		_niz=(EditText)findViewById(R.id.beseda);
		
		_iskanje=(Button)findViewById(R.id.gumbZaIskanje);
		
		_iskanje.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String gradivo= _gradivo.getSelectedItem().toString();
				String jezik =_jezik.getSelectedItem().toString();
				String niz=_niz.getText().toString();
				
				sendCommand("iskanje/"+gradivo+"/"+jezik+"/niz");
			}
		});
		
	}

	private void sendCommand(String com) {
		
		com = com.replace(" ", "%20");

		rat = new RestAsyncTask(restURL + com);
		rat.execute();
	}
}
