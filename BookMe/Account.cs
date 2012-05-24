using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace BookMe
{
    public class Account
    {
        public Account()
        {
            SqlConnection con =
               new SqlConnection(
                   @"Data Source=208.91.198.196;Initial Catalog=BookMe;User Id=upo133;Password=test1234");
            SqlCommand command = new SqlCommand();
        }

        SqlConnection con =
               new SqlConnection(
                   @"Data Source=208.91.198.196;Initial Catalog=BookMe;User Id=upo133;Password=test1234");
        SqlCommand command = new SqlCommand();

        public bool Prijava(string upoIme, string geslo)
        {
            command = new SqlCommand("upo133.PreveriUporabnika", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.Add(new SqlParameter("@returnValue", SqlDbType.Int)
                                  {Direction = ParameterDirection.ReturnValue});
            command.Parameters.AddWithValue("@UpoIme", upoIme);
            command.Parameters.AddWithValue("@Geslo", geslo);

            con.Open();

            command.ExecuteNonQuery();

            return ((int)command.Parameters["@returnValue"].Value) == 1;
        }

        public bool Registracija(string upoIme, string geslo)
        {
            command = new SqlCommand("upo133.DodajUporabnika", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.Add(new SqlParameter("@returnValue", SqlDbType.Int) { Direction = ParameterDirection.ReturnValue });
            command.Parameters.AddWithValue("@UpoIme", upoIme);
            command.Parameters.AddWithValue("@Geslo", geslo);

            con.Open();

            command.ExecuteNonQuery();

            return ((int) command.Parameters["@returnValue"].Value) == 1;
        }
    }
}