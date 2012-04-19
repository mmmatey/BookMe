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

        public List<Service1.IskalniRezultat> IskanjeOsnovno(string jezik, string gradivo, string beseda)
        {
            command = new SqlCommand("upo133.OsnovnoIskanje", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@vrsta", gradivo);
            command.Parameters.AddWithValue("@jezik", jezik);
            command.Parameters.AddWithValue("@iskalniNiz", beseda);

            DataSet ds = new DataSet("Rezultat");
            SqlDataAdapter sql = new SqlDataAdapter(command);
            sql.Fill(ds);

            List<Service1.IskalniRezultat> rezultati = new List<Service1.IskalniRezultat>();
            foreach (DataRow dataRow in ds.Tables[0].Rows)
            {
                Service1.IskalniRezultat ir = new Service1.IskalniRezultat();
                ir.ID = (int)dataRow["ID"];
                ir.avtor = (string)dataRow["Avtor"];
                ir.naslov = (string)dataRow["Naslov"];
                ir.vrsta = (string)dataRow["Vrsta"];
                ir.jezik = (string)dataRow["Jezik"];
                ir.leto = (int)dataRow["LetoIzdaje"];
                ir.dostop = (string)dataRow["Dostopnost"];
                rezultati.Add(ir);
            }

            return rezultati;
        }

        public List<Service1.IskalniRezultat> IskanjeIzbirno(string avtor,
            string naslov, string leto, string jezik, string gradivo)
        {
            command = new SqlCommand("upo133.IzbirnoIskanje", con);
            command.CommandType = CommandType.StoredProcedure;
            if (avtor == " ")
            {
                command.Parameters.Add("@avtor", SqlDbType.NVarChar).Value = DBNull.Value;
            }
            else
            {
                command.Parameters.AddWithValue("@avtor", avtor);
            }
            if (naslov == " ")
            {
                command.Parameters.Add("@naslov", SqlDbType.NVarChar).Value = DBNull.Value;
            }
            else
            {
                command.Parameters.AddWithValue("@naslov", naslov);
            }
            if (leto == " ")
            {
                command.Parameters.Add("@leto", SqlDbType.NVarChar).Value = DBNull.Value;
            }
            else
            {
                command.Parameters.AddWithValue("@leto", leto);
            }
            if (jezik == " ")
            {
                command.Parameters.Add("@jezik", SqlDbType.NVarChar).Value = DBNull.Value;
            }
            else
            {
                command.Parameters.AddWithValue("@jezik", jezik);
            }
            if (gradivo == " ")
            {
                command.Parameters.Add("@vrsta", SqlDbType.NVarChar).Value = DBNull.Value;
            }
            else
            {
                command.Parameters.AddWithValue("@vrsta", gradivo);
            }

            DataSet ds = new DataSet("Rezultat");
            SqlDataAdapter sql = new SqlDataAdapter(command);
            sql.Fill(ds);

            List<Service1.IskalniRezultat> rezultati = new List<Service1.IskalniRezultat>();
            foreach (DataRow dataRow in ds.Tables[0].Rows)
            {
                Service1.IskalniRezultat ir = new Service1.IskalniRezultat();
                ir.ID = (int)dataRow["ID"];
                ir.avtor = (string)dataRow["Avtor"];
                ir.naslov = (string)dataRow["Naslov"];
                ir.vrsta = (string)dataRow["Vrsta"];
                ir.jezik = (string)dataRow["Jezik"];
                ir.leto = (int)dataRow["LetoIzdaje"];
                ir.dostop = (string)dataRow["Dostopnost"];
                rezultati.Add(ir);
            }

            return rezultati;
        }
    }
}