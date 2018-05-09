using System;
using System.IO;
using System.Web;
using System.Net;
using System.Text;
using System.Net.Security;
using System.IO.Compression;
using System.Collections.Generic;
using System.Security.Cryptography.X509Certificates;

namespace com.admin.common
{
    public class RequestHelper
    {
        /// <summary>
        /// POST请求参数
        /// </summary>
        /// <param name="parmName"></param>
        /// <returns></returns>
        public static string GetFormParm(string parmName)
        {
            return HttpContext.Current.Request.Form[parmName];
        }

        /// <summary>
        /// GET请求参数
        /// </summary>
        /// <param name="parmName"></param>
        /// <returns></returns>
        public static string GetQueryParm(string parmName)
        {
            return HttpContext.Current.Request.QueryString[parmName];
        }

		
		#region GET、POST 请求
		//默认的请求用户代理
		private static readonly string DefaultUserAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)";
        /// <summary>  
        /// 创建GET方式的HTTP请求  
        /// </summary>  
        /// <param name="url">请求的URL</param>  
        /// <param name="userAgent">请求的客户端浏览器信息，可以为空</param>  
        /// <param name="cookies">随同HTTP请求发送的Cookie信息，如果不需要身份验证可以为空</param>
        /// <param name="timeout">请求的超时时间毫秒，默认100秒</param>    
        /// <returns></returns>  
        public static HttpWebResponse CreateGetHttpResponse(string url, string userAgent = null, CookieCollection cookies = null, int timeout = 100000)
        {
            if (string.IsNullOrEmpty(url))
            {
                throw new ArgumentNullException("url");
            }
            HttpWebRequest request = WebRequest.Create(url) as HttpWebRequest;
            request.Method = "GET";
            request.UserAgent = DefaultUserAgent;
            if (!string.IsNullOrEmpty(userAgent))
            {
                request.UserAgent = userAgent;
            }
            request.Timeout = timeout;
            if (cookies != null)
            {
                request.CookieContainer = new CookieContainer();
                request.CookieContainer.Add(cookies);
            }
            return request.GetResponse() as HttpWebResponse;
        }

        /// <summary>  
        /// 创建POST方式的HTTP请求  
        /// </summary>  
        /// <param name="url">请求的URL</param>    
        /// <param name="encoding">发送HTTP请求时所用的编码</param>  
        /// <param name="userAgent">请求的客户端浏览器信息，可以为空</param>
        /// <param name="cookies">随同HTTP请求发送的Cookie信息，如果不需要身份验证可以为空</param>  
        /// <param name="timeout">请求的超时时间毫秒，默认100秒</param>   
        /// <param name="parameters">随同请求POST的参数名称及参数值字典</param> 
        /// <returns></returns>  
        public static HttpWebResponse CreatePostHttpResponse(string url, string userAgent = null, CookieCollection cookies = null, int timeout = 100000, IDictionary<string, string> parameters = null, string encoding = "UTF-8")
        {
            if (string.IsNullOrEmpty(url))
            {
                throw new ArgumentNullException("url");
            }
            if (encoding == null)
            {
                throw new ArgumentNullException("encoding");
            }
            HttpWebRequest request = null;
            //如果是发送HTTPS请求  
            if (url.StartsWith("https", StringComparison.OrdinalIgnoreCase))
            {
                ServicePointManager.ServerCertificateValidationCallback = new RemoteCertificateValidationCallback(CheckValidationResult);
                request = WebRequest.Create(url) as HttpWebRequest;
                request.ProtocolVersion = HttpVersion.Version10;
            }
            else
            {
                request = WebRequest.Create(url) as HttpWebRequest;
            }
            request.Method = "POST";
            request.ContentType = "application/x-www-form-urlencoded";

            if (!string.IsNullOrEmpty(userAgent))
            {
                request.UserAgent = userAgent;
            }
            else
            {
                request.UserAgent = DefaultUserAgent;
            }
            request.Timeout = timeout;
            if (cookies != null)
            {
                request.CookieContainer = new CookieContainer();
                request.CookieContainer.Add(cookies);
            }
            //如果需要POST数据  
            if (!(parameters == null || parameters.Count == 0))
            {
                StringBuilder sb = new StringBuilder();
                int i = 0;
                foreach (string key in parameters.Keys)
                {
                    if (i > 0)
                        sb.AppendFormat("&{0}={1}", key, parameters[key]);
                    else
                        sb.AppendFormat("{0}={1}", key, parameters[key]);
                    i++;
                }
                byte[] data = System.Text.Encoding.GetEncoding(encoding).GetBytes(sb.ToString());
                using (Stream stream = request.GetRequestStream())
                {
                    stream.Write(data, 0, data.Length);
                }
            }
            return request.GetResponse() as HttpWebResponse;
        }

        private static bool CheckValidationResult(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors errors)
        {
            return true; //总是接受  
        }

        /// <summary>
        /// 取httpWebResponse响应内容
        /// </summary>
        /// <param name="httpWebResponse">HttpWebResponse实例</param>
        /// <param name="encoding">编码方式，默认UTF-8</param>
        /// <returns></returns>
        public static string GetContentFromResponse(HttpWebResponse httpWebResponse, string encoding = "UTF-8")
        {
            string revStr = "";
            if (encoding == null)
            {
                throw new ArgumentNullException("encoding");
            }
            Stream s = httpWebResponse.GetResponseStream();
            if (httpWebResponse.ContentEncoding.ToLower().Contains("gzip"))
            {
                s = new GZipStream(s, CompressionMode.Decompress);
            }

            using (StreamReader sReder = new StreamReader(s, System.Text.Encoding.GetEncoding(encoding)))
            {
                if (httpWebResponse.StatusCode != HttpStatusCode.OK)
                    revStr = "";
                else
                    revStr = sReder.ReadToEnd();
            }
            if (httpWebResponse != null)
            {
                httpWebResponse.Close();
            }
            if (s != null)
            {
                s.Close();
                s.Dispose();
            }
            return revStr;
        }
		#endregion

        #region 读取或写入cookie
        /// <summary>
        /// 清除指定Cookie
        /// </summary>
        /// <param name="cookiename">cookiename</param>
        public static void DelCookie(string cookiename)
        {
            HttpCookie cookie = HttpContext.Current.Request.Cookies[cookiename];
            if (cookie != null)
            {
                cookie.Expires = DateTime.Now.AddYears(-3);
                HttpContext.Current.Response.Cookies.Add(cookie);
            }
        }
        /// <summary>
        /// 获取指定Cookie值
        /// </summary>
        /// <param name="cookiename">cookiename</param>
        /// <returns></returns>
        public static string GetCookie(string cookiename)
        {
            HttpCookie cookie = HttpContext.Current.Request.Cookies[cookiename];
            string str = string.Empty;
            if (cookie != null)
            {
                str = cookie.Value;
            }
            return str;
        }
        /// <summary>
        /// 添加一个Cookie（24小时过期）
        /// </summary>
        /// <param name="cookiename"></param>
        /// <param name="cookievalue"></param>
        public static void SetCookie(string cookiename, string cookievalue)
        {
            SetCookie(cookiename, cookievalue, DateTime.Now.AddDays(1.0));
        }
        /// <summary>
        /// 添加一个Cookie
        /// </summary>
        /// <param name="cookiename">cookie名</param>
        /// <param name="cookievalue">cookie值</param>
        /// <param name="expires">过期时间 DateTime</param>
        public static void SetCookie(string cookiename, string cookievalue, DateTime expires)
        {
            HttpCookie cookie = new HttpCookie(cookiename)
            {
                Value = cookievalue,
                Expires = expires
            };
            HttpContext.Current.Response.Cookies.Add(cookie);
        }
        #endregion

        /// <summary>
        /// 
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public static string UrlEncode(string str)
        {
            if (string.IsNullOrEmpty(str))
            {
                return "";
            }
            str = str.Replace("'", "");
            return HttpContext.Current.Server.UrlEncode(str);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public static string UrlDecode(string str)
        {
            if (string.IsNullOrEmpty(str))
            {
                return "";
            }
            return HttpContext.Current.Server.UrlDecode(str);
        }

        #region URL请求数据
        /// <summary>
        /// HTTP POST方式请求数据
        /// </summary>
        /// <param name="url">URL.</param>
        /// <param name="param">POST的数据</param>
        /// <returns></returns>
        public static string HttpPost(string url, string param)
        {
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(url);
            request.Method = "POST";
            request.ContentType = "application/x-www-form-urlencoded";
            request.Accept = "*/*";
            request.Timeout = 15000;
            request.AllowAutoRedirect = false;

            StreamWriter requestStream = null;
            WebResponse response = null;
            string responseStr = null;
            try
            {
                requestStream = new StreamWriter(request.GetRequestStream());
                requestStream.Write(param);
                requestStream.Close();

                response = request.GetResponse();
                if (response != null)
                {
                    StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
                    responseStr = reader.ReadToEnd();
                    reader.Close();
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                request = null;
                requestStream.Close();
                requestStream.Dispose();
                response.Close();
            }

            return responseStr;
        }

        /// <summary>
        /// HTTP GET方式请求数据.
        /// </summary>
        /// <param name="url">URL.</param>
        /// <returns></returns>
        public static string HttpGet(string url)
        {
            HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(url);
            request.Method = "GET";
            //request.ContentType = "application/x-www-form-urlencoded";
            request.Accept = "*/*";
            request.Timeout = 15000;
            request.AllowAutoRedirect = false;

            WebResponse response = null;
            string responseStr = null;

            try
            {
                response = request.GetResponse();
                if (response != null)
                {
                    StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
                    responseStr = reader.ReadToEnd();
                    reader.Close();
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                request = null;
                response.Close();
            }

            return responseStr;
        }

        /// <summary>
        /// 执行URL获取页面内容
        /// </summary>
        public static string UrlExecute(string urlPath)
        {
            if (string.IsNullOrEmpty(urlPath))
            {
                return "";
            }
            StringWriter sw = new StringWriter();
            try
            {
                HttpContext.Current.Server.Execute(urlPath, sw);
                return sw.ToString();
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                sw.Close();
                sw.Dispose();
            }
        } 
        #endregion
    }
}
