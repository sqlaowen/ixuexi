using System.Text;
using System.Collections;
using System.IO;
using System.Net;
using System.Net.Mail;

namespace com.admin.common
{
    /// <summary>
    /// 发送邮件的功能类
    /// </summary>
    public class MailHelper
    {
        /// <summary>
        /// 发送邮件的方法
        /// </summary>
        /// <param name="strto">接收地址(多个接收地址用逗号分隔)</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        public static void SendEmail(string strto, string strSubject, string strBody, bool isHtmlFormat)
        {
            SendEmail(strto, strSubject, strBody, isHtmlFormat, new ArrayList());
        }

        /// <summary>
        /// 发送邮件的方法
        /// </summary>
        /// <param name="strto">接收地址(多个接收地址用逗号分隔)</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        /// <param name="files">附件文件的集合</param>
        public static void SendEmail(string strto, string strSubject, string strBody, bool isHtmlFormat, ArrayList files)
        {
            SendEmail(strto, "", "", strSubject, strBody, isHtmlFormat, files);
        }

        /// <summary>
        /// 发送邮件的方法(只发送一封邮件，支持多个收件人和抄送人)
        /// </summary>
        /// <param name="strto">接收地址(多个接收地址用英文半角逗号分隔)</param>
        /// <param name="strcc">抄送地址(多个抄送地址用英文半角逗号分隔)</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        /// <param name="files">附件文件的集合</param>
        public static void SendEmail(string strto, string strcc, string strSubject, string strBody, bool isHtmlFormat,
                                     ArrayList files)
        {
            SendEmail(strto, strcc, "", strSubject, strBody, isHtmlFormat, files);
        }
        /// <summary>
        /// 发送邮件的方法(只发送一封邮件，支持多个收件人和抄送人)
        /// </summary>
        /// <param name="strto">接收地址(多个接收地址用逗号分隔)</param>
        /// <param name="strcc">抄送地址(多个抄送地址用逗号分隔)</param>
        /// <param name="strbc">密送地址（多个密送地址用英文半角逗号分隔）</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        /// <param name="files">附件文件的集合</param>
        public static void SendEmail(string strto, string strcc, string strbc, string strSubject, string strBody, bool isHtmlFormat, ArrayList files)
        {
            SendEmail("smtp.qq.com", "1278734149@qq.com", "3edc#EDC", strto, strcc, strbc, strSubject, strBody, isHtmlFormat, files);
        }


        /// <summary>
        /// 发送邮件的方法
        /// </summary>
        /// <param name="strSmtpServer">邮件服务器地址</param>
        /// <param name="strFrom">发送地址</param>
        /// <param name="strFromPass">发送密码</param>
        /// <param name="strto">接收地址(多个接收地址用逗号分隔)</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        public static void SendEmail(string strSmtpServer, string strFrom, string strFromPass, string strto, string strSubject, string strBody, bool isHtmlFormat)
        {
            SendEmail(strSmtpServer, strFrom, strFromPass, strto, strSubject, strBody, isHtmlFormat, new ArrayList());
        }
        
        /// <summary>
        /// 发送邮件的方法
        /// </summary>
        /// <param name="strSmtpServer">邮件服务器地址</param>
        /// <param name="strFrom">发送地址</param>
        /// <param name="strFromPass">发送密码</param>
        /// <param name="strto">接收地址(多个接收地址用逗号分隔)</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        /// <param name="files">附件文件的集合</param>
        internal static void SendEmail(string strSmtpServer, string strFrom, string strFromPass, string strto, string strSubject, string strBody, bool isHtmlFormat, ArrayList files)
        {
            SmtpClient client = new SmtpClient(strSmtpServer);
            client.UseDefaultCredentials = false;
            client.Credentials = new NetworkCredential(strFrom, strFromPass);
            client.DeliveryMethod = SmtpDeliveryMethod.Network;

            string[] strtos = strto.Split(new char[] { ',' });
            for (int i = 0; i < strtos.Length; i++)
            {
                try
                {
                    if (strtos[i].Trim().Length > 0)
                    {
                        MailMessage message = new MailMessage(strFrom, strtos[i].Trim(), strSubject, strBody);
                        message.BodyEncoding = Encoding.Default;
                        message.IsBodyHtml = isHtmlFormat;

                        for (int j = 0; j < files.Count; j++)
                        {
                            if (File.Exists(files[j].ToString()))
                            {
                                message.Attachments.Add(new Attachment(files[j].ToString()));
                            }
                        }

                        client.Send(message);
                    }
                }
                catch
                {
                    continue;
                }
            }
        }

        /// <summary>
        /// 发送邮件的方法(只发送一封邮件，支持多个收件人和抄送人)
        /// </summary>
        /// <param name="strSmtpServer">邮件服务器地址</param>
        /// <param name="strFrom">发送地址</param>
        /// <param name="strFromPass">发送密码</param>
        /// <param name="strto">接收地址(多个接收地址用逗号分隔)</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        /// <param name="files">附件文件的集合</param>
        public static void SendEmail(string strSmtpServer, string strFrom, string strFromPass, string strto,
                                     string strcc, string strSubject, string strBody, bool isHtmlFormat, ArrayList files)
        {
            SendEmail(strSmtpServer, strFrom, strFromPass, strto, strcc, "", strSubject, strBody, isHtmlFormat, files);
        }

        /// <summary>
        /// 发送邮件的方法(只发送一封邮件，支持多个收件人和抄送人)
        /// </summary>
        /// <param name="strSmtpServer">邮件服务器地址</param>
        /// <param name="strFrom">发送地址</param>
        /// <param name="strFromPass">发送密码</param>
        /// <param name="strto">接收地址(多个接收地址用逗号分隔)</param>
        /// <param name="strcc">抄送地址(多个抄送地址用逗号分隔)</param>
        /// <param name="strbc">密送地址(多个密送地址用英文半角逗号分隔)</param>
        /// <param name="strSubject">邮件主题</param>
        /// <param name="strBody">邮件内容</param>
        /// <param name="isHtmlFormat">邮件内容是否以html格式发送</param>
        /// <param name="files">附件文件的集合</param>
        public static void SendEmail(string strSmtpServer, string strFrom, string strFromPass, string strto, string strcc, string strbc, string strSubject, string strBody, bool isHtmlFormat, ArrayList files)
        {
            SmtpClient client = new SmtpClient(strSmtpServer);
            client.UseDefaultCredentials = false;
            client.Credentials = new NetworkCredential(strFrom, strFromPass);
            client.DeliveryMethod = SmtpDeliveryMethod.Network;

            MailMessage message = new MailMessage(strFrom, strto);
            message.Subject = strSubject;
            message.Body = strBody;
            message.BodyEncoding = Encoding.Default;
            message.IsBodyHtml = isHtmlFormat;

            foreach (object t in files)
            {
                if (File.Exists(t.ToString()))
                {
                    message.Attachments.Add(new Attachment(t.ToString()));
                }
            }

            string[] strtos = strto.Split(new char[] { ',' });
            message.To.Clear();
            foreach (string t in strtos)
            {
                if (!string.IsNullOrEmpty(t.Trim()))
                {
                    message.To.Add(new MailAddress(t.Trim()));
                }
            }

            string[] strccs = strcc.Split(new char[] { ',' });
            foreach (string t in strccs)
            {
                if (!string.IsNullOrEmpty(t.Trim()))
                {
                    message.CC.Add(new MailAddress(t.Trim()));
                }
            }

            string[] arrBc = strbc.Split(new char[] { ',' });
            foreach (string t in arrBc)
            {
                if (!string.IsNullOrEmpty(t.Trim()))
                {
                    message.Bcc.Add(new MailAddress(t.Trim()));
                }
            }

            client.Send(message);
        }

    }
}
