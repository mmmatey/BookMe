using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace BookMe
{
    public class Book
    {
        public string avtor { get; set; }
        public string ime { get; set; }
        public int knjiznicaID { get; set; }
        public int leto { get; set; }
        public string vrstaGradiva { get; set; }
        public string lang { get; set; }
        public string access { get; set; }

        SqlConnection con =
               new SqlConnection(
                   @"Data Source=MMMATEY-PC\SqlExpress;Initial Catalog=BookMe;Integrated Security=True");
        SqlCommand command = new SqlCommand();

        public Book()
        {
            SqlConnection con =
               new SqlConnection(
                   "Data Source=mmmatey-pc/sqlexpress;Initial Catalog=BookMe;Persist Security Info=True;");
            SqlCommand command = new SqlCommand();

            
        }

        public Book(string name, string author, int year, string jezik, string gradivo, string dostop)
        {
            
            this.ime = name;
            this.access = dostop;
            this.lang = jezik;
            this.leto = year;
            this.vrstaGradiva = gradivo;
            this.avtor = author;
            
        }

        public string AddNewBook()
        {
            command = new SqlCommand("dbo.DodajKnjigo", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@naslov", ime);
            command.Parameters.AddWithValue("@avtor", avtor);
            command.Parameters.AddWithValue("@jezik", lang);
            command.Parameters.AddWithValue("@leto", leto);
            command.Parameters.AddWithValue("@gradivo", vrstaGradiva);
            command.Parameters.AddWithValue("@dostopno", access);
            con.Open();
            command.ExecuteNonQuery();
            con.Close();
            return "Knjiga dodana!";
        }

        public override string ToString()
        {
            return "Naslov knjige: " + ime + " Leto izdaje: " + leto + " Avtor: " + avtor;
        }

        public string Delete(int id)
        {
            command = new SqlCommand("dbo.BrisiKnjigo", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@id", id);
            con.Open();
            command.ExecuteNonQuery();
            con.Close();
            return "Knjiga izbrisana";
        }

        public string Update(string name, string author, int year, string jezik, string gradivo, string dostop)
        {
            this.ime = name;
            this.access = dostop;
            this.lang = jezik;
            this.leto = year;
            this.vrstaGradiva = gradivo;
            this.avtor = author;

            command = new SqlCommand("dbo.UrediKnjigo", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@naslov", ime);
            command.Parameters.AddWithValue("@avtor", avtor);
            command.Parameters.AddWithValue("@jezik", lang);
            command.Parameters.AddWithValue("@leto", leto);
            command.Parameters.AddWithValue("@gradivo", vrstaGradiva);
            command.Parameters.AddWithValue("@dostopno", access);
            con.Open();
            command.ExecuteNonQuery();
            con.Close();
            return "Knjiga posodobljena";
        }

        public string CopyBook(int id)
        {
            return "Copy book";
        }
    }
}