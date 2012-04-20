package BookMe.com;

import java.util.ArrayList;


public class Book{

	public Book() {
		// TODO Auto-generated constructor stub
		
	}
	
	public String _ID;
	public String _Naslov;
	public String _Jezik;
	public String _Dostop;
	public String _Avtor;
	public String _Vrsta;
	public String _Leto;
	
	public String getId()
	{
		return _ID;
	}
	
	public String getNaslov()
	{
		return _Naslov;
	}
	
	public String getVrsta()
	{
		return _Vrsta;
	}
	
	public String getAvtor()
	{
		return _Avtor;
	}
	
	public String getJezik()
	{
		return _Jezik;
	}
	
	public String getLeto()
	{
		return _Leto;
	}
	
	public String getDostop()
	{
		return _Dostop;
	}
	
	public String toString()
	{
		return "Knjiga:"+_ID+"-->"+ _Avtor+" | "+_Naslov+" | "+_Vrsta+" | "+_Jezik+" | "+_Dostop ;
	}

}
