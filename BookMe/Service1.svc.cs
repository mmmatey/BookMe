using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace BookMe
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    public class Service1 : IService1
    {

        public string JSONData(string id)
        {
            Book knjiga = new Book("Knjiga test", "Desa", 1990, "sloven", "poezi", "dostopno na spletu");
            string ret = "";
            if (id == "DODAJ")
            {
                ret = knjiga.AddNewBook();
            }
            else if (id == "UREDI")
            {
                ret = knjiga.Update("Knjiga test", "Desa", 2000, "sloven", "poezi", "dostopno na spletu");
            }
            else if(id=="IZPIS")
            {
                ret = knjiga.ToString();
            }
            else if(id=="DELETE")
            {
                ret = knjiga.Delete(5);
            }
            return ret;
        }
    }
}
