using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text.RegularExpressions;
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
                //ir.dostop = (string)dataRow["Dostopnost"];
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
                //ir.dostop = (string)dataRow["Dostopnost"];
                rezultati.Add(ir);
            }

            return rezultati;
        }

        public List<Service1.IskalniRezultat> IskanjeUkazno(string ukaz)
        {
            ukaz = ukaz.Trim();
            ukaz = Regex.Replace(ukaz, @"\s+", "");
            string[] columns = new[] { " upo133.Avtor.Avtor LIKE '%", " upo133.Knjiga.Naslov LIKE '%", 
                " upo133.Knjiga.LetoIzdaje LIKE '%", " upo133.Jezik.Jezik LIKE '%", 
                " upo133.Gradivo.Vrsta LIKE '%" };
            string[] zacUkazi = new string[5];
            Dictionary<int, int> orAnd = new Dictionary<int, int>();

            int ind = ukaz.IndexOf("OR", 0, StringComparison.InvariantCulture);
            while (ind != -1)
            {
                orAnd.Add(ind, ind + 5);
                ind = ukaz.IndexOf("OR", ind + 3, StringComparison.InvariantCulture);
            }
            ind = ukaz.IndexOf("AND", 0, StringComparison.InvariantCulture);
            while (ind != -1)
            {
                orAnd.Add(ind, ind + 6);
                ind = ukaz.IndexOf("AND", ind + 3, StringComparison.InvariantCulture);
            }

            var keys = from k in orAnd.Keys
                        orderby k descending 
                        select k;

            for (int i = 0; i < columns.Length; i++ )
            {
                string zacUkaz = ukaz + "%') ";
                foreach (int key in keys)
                {
                    zacUkaz = zacUkaz.Insert(key, "%' ");
                    zacUkaz = zacUkaz.Insert(orAnd[key], columns[i]);
                }
                zacUkazi[i] = "(" + columns[i] + zacUkaz;
            }

            string celotniUkaz = zacUkazi[0];
            for (int i = 1; i < zacUkazi.Length; i++ )
            {
                celotniUkaz += " OR " + zacUkazi[i];
            }


            command = new SqlCommand("upo133.UkaznoIskanje", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@celotniUkaz", celotniUkaz);

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
                //ir.dostop = (string)dataRow["Dostopnost"];
                rezultati.Add(ir);
            }

            return rezultati;
        }

        public List<Service1.Knjiznice> KnjizniceSKnjigo(int id)
        {
            command = new SqlCommand("upo133.KnjizniceSKnjigo", con);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.AddWithValue("@KnjigaId", id);

            DataSet ds = new DataSet("Rezultat");
            SqlDataAdapter sql = new SqlDataAdapter(command);
            sql.Fill(ds);

            List<Service1.Knjiznice> rezultati = new List<Service1.Knjiznice>();
            foreach (DataRow dataRow in ds.Tables[0].Rows)
            {
                Service1.Knjiznice knj = new Service1.Knjiznice();
                knj.naziv = (string)dataRow["Naziv"];
                knj.kraj = (string)dataRow["Kraj"];
                knj.X = (double)dataRow["X"];
                knj.Y = (double)dataRow["Y"];
                knj.dostopnost = (string)dataRow["Dostopnost"];
                knj.stevilo = (int)dataRow["Stevilo"];
                knj.stevilo = (int)dataRow["StIzposojenih"];
                rezultati.Add(knj);
            }

            return rezultati;
            //http://maps.google.com/maps?q=tehni%C5%A1ke+fakultete+maribor&hl=sl&ie=UTF8&ll=46.668287,15.380859&spn=0.942383,2.705383&sll=37.0625,-95.677068&sspn=34.808514,86.572266&t=h&hq=tehni%C5%A1ke+fakultete&hnear=Maribor,+Slovenija&z=9
        }
    }
}