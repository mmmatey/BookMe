using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace BookMe
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    public class Service1 : IService1
    {
        [DataContract]
        public struct IskalniRezultat
        {
            [DataMember]
            public int ID { get; set; }
            [DataMember]
            public string avtor { get; set; }
            [DataMember]
            public string naslov { get; set; }
            [DataMember]
            public string vrsta { get; set; }
            [DataMember]
            public string jezik { get; set; }
            [DataMember]
            public int leto { get; set; }
        }
            
        [DataContract]
        public struct Knjiznice
        {
            [DataMember]
            public string naziv { get; set; }
            [DataMember]
            public string kraj { get; set; }
            [DataMember]
            public double X { get; set; }
            [DataMember]
            public double Y { get; set; }
            [DataMember]
            public string dostopnost { get; set; }
            [DataMember]
            public int stevilo { get; set; }
            [DataMember]
            public int stIzposojenih { get; set; }
        }

        public Book JSONData(string id,string naslov, string avtor, string gradivo, string leto, string jezik, string zalozba)
        {
            Book knjiga = new Book(naslov, avtor, leto, jezik, gradivo, zalozba);
            string ret = "";
            if (id == "DODAJ")
            {
                ret = knjiga.AddNewBook();
            }
            else if (id == "UREDI")
            {
                ret = knjiga.Update(naslov, avtor, leto, jezik, gradivo, zalozba);
            }
            else if (id == "IZPIS")
            {
                ret = knjiga.ToString();
            }
            else if (id == "DELETE")
            {
                ret = knjiga.Delete(5);
            }

            return knjiga;
        }

        public List<IskalniRezultat> IskanjeOsnovno(string gradivo, string jezik, string niz)
        {
            Search search = new Search();
            List<IskalniRezultat> src = search.IskanjeOsnovno(jezik, gradivo, niz);

            return src;
        }

        public List<IskalniRezultat> IskanjeIzbirno(string avtor,
            string naslov, string leto, string jezik, string gradivo)
        {
            Search search = new Search();
            List<IskalniRezultat> src = search.IskanjeIzbirno(avtor, naslov, leto, jezik, gradivo);

            return src;
        }

        public List<IskalniRezultat> IskanjeUkazno(string ukaz)
        {
            Search search = new Search();
            List<IskalniRezultat> src = search.IskanjeUkazno(ukaz);

            return src;
        }

        public List<Knjiznice> KnjizniceSKnjigo(string id)
        {
            Search search = new Search();
            List<Knjiznice> src = search.KnjizniceSKnjigo(int.Parse(id));

            return src;
        }

        public bool Prijava(string upoIme, string geslo)
        {
            Account acc = new Account();
            bool pr = acc.Prijava(upoIme, geslo);

            return pr;
        }

        public bool Registracija(string upoIme, string geslo)
        {
            Account acc = new Account();
            bool pr = acc.Registracija(upoIme, geslo);

            return pr;
        }
    }
}
