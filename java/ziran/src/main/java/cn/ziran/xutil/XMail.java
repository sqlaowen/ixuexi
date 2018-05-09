package cn.ziran.xutil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;

public class XMail {

	public static void main(String[] args) throws Exception {
//  //new一个URL对象  
//  URL url = new URL("http://upload.cankaoxiaoxi.com/2016/0329/1459215379762.jpg");  
//  //打开链接  
//  HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
//  //设置请求方式为"GET"  
//  conn.setRequestMethod("GET");  
//  //超时响应时间为30秒  
//  conn.setConnectTimeout(30 * 1000);  
//  //通过输入流获取图片数据  
//  InputStream inStream = conn.getInputStream();
	  //
//    System.out.println(
//        "http://dlsw.baidu.com/sw-search-sp/soft/3a/12350/QQ_8.2.17724.0_setup.1459155849.exe"
//            .matches("^(http|https|ftp)://([\\w.]+\\/?)\\S*"));
//    Pattern pattern2 = Pattern.compile("^(http|ftp|https):\\/\\/([\\w.]+\\/?)\\S*");
//    Matcher matcher2 = pattern2.matcher("http://dlsw.baidu.com/sw-search-sp/soft/3a/12350/QQ_8.2.17724.0_setup.1459155849.exe");
//	  System.out.println(matcher2.matches());
	  		//
	  MailObj obj = new MailObj();
		obj.setMailHost("smtp.qq.com");
//	  obj.setMailHost("server.ngrok.cc");
		obj.setMailPort("10125");
		obj.setMailProtocol("smtp");
		obj.setUserName("ziran@ziran.com");
		obj.setPassword("123456");
		obj.setFrom("ziran@ziran.com");
		obj.setToArr(new String[] { "ime@ziran.com","309685769@qq.com","ziran@ziran.com" });
//		obj.setCcArr(new String[] { "309685769@qq.com","821388282@qq.com","353234669@qq.com","740301308@qq.com","1532047696@qq.com","1142662964@qq.com","1295153419@qq.com","869528117@qq.com" });
		obj.setCcArr(new String[] { "309685769@qq.com"});
		obj.setBccArr(new String[] { "iyou@ziran.com","309685769@qq.com" });
		obj.setSubject("测试邮箱a13");
		String html="<h1>测试邮箱</h1>"
		    +"<a href=\"http://dldir1.qq.com/qqfile/qq/QQ8.4/18380/QQ8.4.exe\">qq下载</a>"
		    +"<p>还有附件</p>";
		obj.setContent(html);
		
//		obj.setAttachFileArr(new String[] { "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png"
//		    ,"D:/xx.jpg"
//		});
		sendMail(obj);
		
	//	getMail(obj);
	}

	public static void sendMail(MailObj mailObj) throws Exception {
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", mailObj.getMailHost());//smtp服务器
		prop.setProperty("mail.smtp.port", mailObj.getMailPort());//smtp端口
		prop.setProperty("mail.transport.protocol", mailObj.getMailProtocol());//发邮件协议
		prop.setProperty("mail.smtp.auth", "true");//是否smtp认证
		prop.setProperty("mail.store.protocol", "pop3"); // 收邮件协议      
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		// 2、通过session得到transport对象
		Transport ts = session.getTransport();
		// 3、连上邮件服务器
		ts.connect(mailObj.getMailHost(), mailObj.getUserName(), mailObj.getPassword());

		// 4、创建邮件
		Message message = createMixedMail(session, mailObj);
		// 5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	 //接受邮件  
  public static void getMail(MailObj mailObj){  
    Properties props = new Properties();
//    props.setProperty("mail.host", mailObj.getMailHost());
//    props.setProperty("mail.transport.protocol", mailObj.getMailProtocol());
//    props.setProperty("mail.smtp.auth", "true");
    // 使用JavaMail发送邮件的5个步骤
    String host="127.0.0.1";
    final String un="ime@ziran.com";
    final String pwd="123456";
    // 1、创建session
          // 创建邮件会话  
      Session session = Session.getDefaultInstance(props, new Authenticator(){  
          @Override  
          public PasswordAuthentication getPasswordAuthentication() {  
              return new PasswordAuthentication(un, pwd);  
          }  
      });  

        
      try {  
          // 获取邮箱的pop3存储  
          Store store = session.getStore("smtp");  
          store.connect(host, un, pwd);  

          // 获取inbox文件  
          Folder folder = store.getFolder("INBOX");  
          folder.open(Folder.READ_ONLY);  //打开，打开后才能读取邮件信息  

          // 获取邮件消息  
          Message message[] = folder.getMessages();  

          for (int i=0, n=message.length; i<n; i++) {  
              System.out.println(i + ": " + message[i].getFrom()[0]  
                                             + "\t" + message[i].getSubject());  
              try {  
                  message[i].writeTo(System.out);  
              } catch (IOException e) {  
                  e.printStackTrace();  
              }  

          }  

          // 关闭资源  
          folder.close(false);  
          store.close();  
            
      } catch (MessagingException e) {  
          e.printStackTrace();  
      }  
        
      System.out.println("GetMail Process Over!");  

  } 
	
	public static MimeMessage createMixedMail(Session session, MailObj mailObj) throws Exception {
	// 创建邮件
    MimeMessage message = new MimeMessage(session);

    // 发件人
    message.setFrom(new InternetAddress(mailObj.getFrom(),"自然"));
    // 收件人
    if (mailObj.getToArr() != null && mailObj.getToArr().length > 0){
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(StringUtils.join(mailObj.getToArr(), ',')));
    }
    // 抄送
    if (mailObj.getCcArr() != null && mailObj.getCcArr().length > 0)
      message.setRecipients(Message.RecipientType.CC,
          InternetAddress.parse(StringUtils.join(mailObj.getCcArr(), ',')));
    // 密送
    if (mailObj.getBccArr() != null && mailObj.getBccArr().length > 0)
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(StringUtils.join(mailObj.getBccArr(), ',')));
    // 发送时间
    message.setSentDate(new Date());
    // 主题
    message.setSubject(mailObj.getSubject());

    // 附件
    if (mailObj.getAttachFileArr() != null && mailObj.getAttachFileArr().length > 0) {
      // 正文
      MimeBodyPart text = new MimeBodyPart();
      text.setContent(mailObj.getContent(), "text/html;charset=UTF-8");

      MimeMultipart mp = new MimeMultipart();

      for (String str : mailObj.getAttachFileArr()) {
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = null;
        if (str.matches("^(http|https|ftp)://([\\w.]+\\/?)\\S*")) {
          dh = new DataHandler(new URL(str));
        } else {
          if (new File(str).exists()) {
            dh = new DataHandler(new FileDataSource(str));
          }
          //dh = new DataHandler(new FileDataSource(str));
        }
        if (null != dh) {
          attach.setDataHandler(dh);
          attach.setFileName(MimeUtility.encodeText(dh.getName()));
  
          mp.addBodyPart(attach);
        }
      }
      mp.addBodyPart(text);
      mp.setSubType("mixed");

      message.setContent(mp);
      message.saveChanges();
    } else {
      message.setContent(mailObj.getContent(), "text/html;charset=UTF-8");
    }

     message.writeTo(new FileOutputStream("MixedMail.eml"));
    // 返回创建好的的邮件
    return message;
	}

	public static class MailObj {
		private String mailHost = "smtp.qq.com"; // 邮件服务器
		private String mailPort = "25"; // 端口

		private String mailProtocol = "smtp"; // 协议

		private String userName;
		private String password;

		private String from; // 发件人
		private String[] toArr; // 收件人
		private String[] ccArr; // 抄送给...
		private String[] bccArr; // 密送给...
		private String subject; // 主题

		private String[] attachFileArr; // 附件
		private String content; // 邮件内容

		public String getMailHost() {
			return mailHost;
		}

		public void setMailHost(String mailHost) {
			this.mailHost = mailHost;
		}

		public String getMailPort() {
			return mailPort;
		}

		public void setMailPort(String mailPort) {
			this.mailPort = mailPort;
		}

		public String getMailProtocol() {
			return mailProtocol;
		}

		public void setMailProtocol(String mailProtocol) {
			this.mailProtocol = mailProtocol;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String[] getToArr() {
			return toArr;
		}

		public void setToArr(String[] toArr) {
			this.toArr = toArr;
		}

		public String[] getCcArr() {
			return ccArr;
		}

		public void setCcArr(String[] ccArr) {
			this.ccArr = ccArr;
		}

		public String[] getBccArr() {
			return bccArr;
		}

		public void setBccArr(String[] bccArr) {
			this.bccArr = bccArr;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String[] getAttachFileArr() {
			return attachFileArr;
		}

		public void setAttachFileArr(String[] attachFileArr) {
			this.attachFileArr = attachFileArr;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
