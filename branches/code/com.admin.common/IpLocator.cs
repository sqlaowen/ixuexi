using System.Text;
using System.IO;

namespace com.admin.common
{
    public class IPLocatorHelper
    {
        public string Ret { get; set; }
        public string Start { get; set; }
        public string End { get; set; }
        public string Country { get; set; }
        public string Province { get; set; }
        public string City { get; set; }
        public string District { get; set; }
        public string Isp { get; set; }
        public string Type { get; set; }
        public string Desc { get; set; }

        public static IPLocatorHelper Get(string ipAddress, Encoding sourceEncoding)
        {
            string ip = string.Empty;
            if (sourceEncoding == null)
                sourceEncoding = Encoding.UTF8;
            using (var receiveStream = System.Net.WebRequest.Create(string.Format("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip={0}", ipAddress)).GetResponse().GetResponseStream())
            {
                using (var sr = new StreamReader(receiveStream, sourceEncoding))
                {
                    ip = sr.ReadToEnd();
                }
            }
            return !string.IsNullOrEmpty(ip) ? new System.Web.Script.Serialization.JavaScriptSerializer().Deserialize<IPLocatorHelper>(ip) : null;
        }
    }
    //使用方法
    //Detail d = IpDetail.Get("58.83.255.255", null);  
    //sponse.Write(d.Country + "<br/>");  
    //sponse.Write(d.Province + "<br/>");  
    //sponse.Write(d.City + "<br/>");  
    //sponse.Write(d.Isp); 	

}