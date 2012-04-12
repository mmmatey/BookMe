using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace BookMe
{
    public class Search
    {
        public Search()
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

        public void IskanjeOsnovno(string jezik, string gradivo, string beseda)
        {
            command = new SqlCommand("upo133.Search", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@vrsta", gradivo);
            command.Parameters.AddWithValue("@jezik", jezik);
            command.Parameters.AddWithValue("@iskalniNiz", beseda);




            DataSet ds = new DataSet("Rezultat");
            SqlDataAdapter sql = new SqlDataAdapter(command);
            sql.Fill(ds);

            foreach (DataRow dataRow in ds.Tables[0].Rows)
            {
                String naslov = (String)dataRow["Naslov"];
                String pisatelj = (String)dataRow["LetoIzdaje"];
            }
        }
    }
}