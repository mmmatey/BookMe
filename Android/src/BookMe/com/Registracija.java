package BookMe.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class Registracija extends Activity {

	AutoCompleteTextView user;
	AutoCompleteTextView pass;
	TextView error;
	Button novuser;
	RestAsyncTask rat;
	String response;
	String ukaz;
	String username;
	String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registracija);
		novuser=(Button)findViewById(R.id.register);
		user=(AutoCompleteTextView)findViewById(R.id.usertext);
		pass=(AutoCompleteTextView)findViewById(R.id.passtext);
		
		novuser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username=user.getText().toString();
				password=pass.getText().toString();
				
				sendCommand("registracija/"+username+"/"+password);
				response=rat.doInBackground(null);
				try{
					Boolean b = Boolean.valueOf(response);
			        String ime="";
			        
//					for(int i=0;i<mtUsers.length();i++)
//		            {
//						ime= mtUsers.getString(i);
//		            }
					
					if(b)
					{
						Intent in = new Intent(getApplicationContext(), Prijava.class);
						startActivity(in);
					}
					else
						
					{
						error.setText("Napaèno geslo ali uporabniško ime.");
					}
				}
				catch(Exception ex)
				{
					Log.d("REST", "Napaka:"+ex.toString());
				}
			}
		});
	}
	private void sendCommand(String com) 
	{
			
			com = com.replace(" ", "%20");
	
			rat = new RestAsyncTask(Values.restURL + com);
			rat.execute();
			response=rat.r.response;
	}
}
