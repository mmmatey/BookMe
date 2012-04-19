using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace BookMe
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {
        [OperationContract]
        [WebInvoke(Method = "GET",
           ResponseFormat = WebMessageFormat.Json,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "json/{id}/{naslov}/{avtor}/{gradivo}/{leto}/{jezik}/{zalozba}")]
        Book JSONData(string id, string naslov, string avtor, string gradivo, string leto, string jezik, string zalozba);

        [OperationContract]
        [WebInvoke(Method = "GET",
           ResponseFormat = WebMessageFormat.Json,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "json/iskanje/{avtor}/{naslov}/{leto}/{jezik}/{gradivo}/")]
        List<Service1.IskalniRezultat> IskanjeIzbirno(string avtor,
            string naslov, string leto, string jezik, string gradivo);

        [OperationContract]
        [WebInvoke(Method = "GET",
           ResponseFormat = WebMessageFormat.Json,
            BodyStyle = WebMessageBodyStyle.Wrapped,
            UriTemplate = "json/iskanje/{gradivo}/{jezik}/{niz}")]
        List<Service1.IskalniRezultat> IskanjeOsnovno(string gradivo, string jezik, string niz);
        
    }
}
