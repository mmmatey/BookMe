package BookMe.com;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class RestAsyncTask extends AsyncTask<String, Void, String> {
	public Reader r;
	String url;
	
	public RestAsyncTask(String url) {
		
		this.url = url;
		Log.d("REST", url);
		r= new Reader();
	}
	
	@Override
	protected String doInBackground(String... arg0) {

		r.toJson(url);

		String response = r.response;
		Log.i("REST", response);
		
//        restClient = new RestClient(url);
//        
//        try {
//            restClient.Execute(RequestMethod.GET);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error witch connection.";
//        }
		
		// save to db
//        DBHelper db = new DBHelper(getBaseContext());
//        db.open();
//        
//        Calendar c = Calendar.getInstance();
//        int ura = c.get(Calendar.HOUR_OF_DAY);
//        int min = c.get(Calendar.MINUTE);
//        String clock = ura+":"+min;
//        
//        int day = c.get(Calendar.DAY_OF_MONTH);
//        int month = c.get(Calendar.MONTH)+1;
//        int year = c.get(Calendar.YEAR);
//        String date = day+"."+month+"."+year;
//        
//        Log.i("REST", "Ura je: "+ura+":"+min+" Datum: "+date);
//        
//        db.addRow(date, clock, Integer.toString(percentage)); // percentage
//        db.close();
        
        //return restClient.getResponse();
		
		return response;
	}

	@Override
	protected void onPostExecute(String result) {
		
//		if (result.equals("ERROR"))
//			Toast.makeText(BookMeActivity.this, "Error. Try Again.", Toast.LENGTH_SHORT).show();
//		
		//Toast.makeText(ProjectActivity.this, result, Toast.LENGTH_SHORT).show();
		
	}

}