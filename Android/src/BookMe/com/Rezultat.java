package BookMe.com;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Rezultat extends ListActivity {

	String izbira="";
	String avtor="";
	String leto="";
	String gradivo="";
	String naslov="";
	String jezik="";
	String id="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rezultati);
		
		Intent i = getIntent();
		izbira= i.getExtras().get("Objekt").toString();
		if(izbira.equals("1"))
		{
			//lv_arr = (String[]) IskanjeO.rezultat.toArray(new String[0]);
//			lv.setAdapter(new ArrayAdapter<Book>(Rezultat.this,
//					R.layout.rezult, IskanjeO.rezultat));
			setListAdapter(new ArrayAdapter<Book>(Rezultat.this,
					R.layout.rezult,R.id.label, IskanjeO.rezultat));
		}
		else if(izbira.equals("2"))
		{
			for (Book book : IskanjeI.rezultat) {
				Log.d("REST","Prenos uspel");
			}
			
			

			//lv_arr = (String[]) IskanjeO.rezultat.toArray(new String[0]);
//			lv.setAdapter(new ArrayAdapter<Book>(Rezultat.this,
//					R.layout.rezult, IskanjeI.rezultat));
			setListAdapter(new ArrayAdapter<Book>(Rezultat.this,
					R.layout.rezult,R.id.label, IskanjeI.rezultat));
		}
		
		else
		{
			for (Book book : IskanjeU.rezultat) {
				Log.d("REST","Prenos uspel");
			}
			
			
			String[] lv_arr = {};

			//lv_arr = (String[]) IskanjeO.rezultat.toArray(new String[0]);
//			lv.setAdapter(new ArrayAdapter<Book>(Rezultat.this,
//					R.layout.rezult, IskanjeU.rezultat));
			setListAdapter(new ArrayAdapter<Book>(Rezultat.this,
					R.layout.rezult, R.id.label, IskanjeU.rezultat));
		}
	}
		
//		lv.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
//					long arg3) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent().setClass(getApplicationContext(),InfoActivity.class);
//				if(izbira.equals("1"))
//				{
//					PridobiObjektKnjiga(IskanjeO.rezultat, index);
//					
//				}
//				else if(izbira.equals("2"))
//				{
//					PridobiObjektKnjiga(IskanjeI.rezultat, index);
//				}
//				
//				else
//				{
//					PridobiObjektKnjiga(IskanjeU.rezultat, index);
//
//				}
//				intent.putExtra("Avtor", avtor);
//				intent.putExtra("Naslov", naslov);
//				intent.putExtra("Leto", leto);
//				intent.putExtra("Gradivo", gradivo);
//				intent.putExtra("ID", id);
//				intent.putExtra("Jezik", jezik);
//				startActivity(intent);
//			}
//			
//		});
//	}

	@Override
	protected void onListItemClick(ListView l, View v, int index, long ident) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, index, id);
		
		Intent intent = new Intent().setClass(getApplicationContext(),InfoActivity.class);
		if(izbira.equals("1"))
		{
			PridobiObjektKnjiga(IskanjeO.rezultat, index);
			
		}
		else if(izbira.equals("2"))
		{
			PridobiObjektKnjiga(IskanjeI.rezultat, index);
		}
		
		else
		{
			PridobiObjektKnjiga(IskanjeU.rezultat, index);

		}
		
		intent.putExtra("Avtor", avtor);
		intent.putExtra("Naslov", naslov);
		intent.putExtra("Leto", leto);
		intent.putExtra("Gradivo", gradivo);
		intent.putExtra("ID", id);
		intent.putExtra("Jezik", jezik);
		Log.d("REST", ""+id);
		startActivity(intent);
	}
	
	public void PridobiObjektKnjiga(ArrayList<Book> knjiga, int index)
	{
		avtor=knjiga.get(index)._Avtor;
		naslov=knjiga.get(index)._Naslov;
		leto=knjiga.get(index)._Leto;
		gradivo=knjiga.get(index)._Vrsta;
		jezik=knjiga.get(index)._Jezik;
		id=knjiga.get(index)._ID;
	}
}
