using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace BookMe
{
    public class Search
    {
        public Search(bool en)
        {
            if (en)
            {
                con =
                    new SqlConnection(
                        @"Data Source=208.91.198.196;Initial Catalog=BookMe;User Id=upo133;Password=test1234");
                command = new SqlCommand();
            }
        }
        public string naslov { get; set; }
        public int Leto { get; set; }

        SqlConnection con =
               new SqlConnection(
                   @"Data Source=208.91.198.196;Initial Catalog=BookMe;User Id=upo133;Password=test1234");
        SqlCommand command = new SqlCommand();

        public DataSet IskanjeOsnovno(string jezik, string gradivo, string beseda)
        {
            command = new SqlCommand("upo133.OsnovnoIskanje", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@vrsta", gradivo);
            command.Parameters.AddWithValue("@jezik", jezik);
            command.Parameters.AddWithValue("@iskalniNiz", beseda);

            Search ss = new Search(false);


            DataSet ds = new DataSet("Rezultat");
            SqlDataAdapter sql = new SqlDataAdapter(command);
            sql.Fill(ds);

            foreach (DataRow dataRow in ds.Tables[0].Rows)
            {
                ss.naslov = (String)dataRow["Naslov"];
                ss.Leto = (int)dataRow["LetoIzdaje"];
            }
            return ds;
        }
    }
}