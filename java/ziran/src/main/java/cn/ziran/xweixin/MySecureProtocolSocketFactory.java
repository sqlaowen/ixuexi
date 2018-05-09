package cn.ziran.xweixin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

/**
 * 忽略数字证书
 * 
 * @author
 */
public class MySecureProtocolSocketFactory implements SecureProtocolSocketFactory {
  SSLContext sslContext = null;

  private SSLContext createSSLContext() {
    try {
      SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, new TrustManager[] {new TrustAnyTrustManager()}, new SecureRandom());
      return sslContext;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private SSLContext getSSLContext() {
    if (this.sslContext == null) {
      this.sslContext = createSSLContext();
    }
    return this.sslContext;
  }


  @Override
  public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
      throws IOException, UnknownHostException {
    return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
  }

  @Override
  public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
    return getSSLContext().getSocketFactory().createSocket(host, port);
  }

  @Override
  public Socket createSocket(String host, int port, InetAddress localAddress, int localPort)
      throws IOException, UnknownHostException {
    return getSSLContext().getSocketFactory().createSocket(host, port, localAddress, localPort);
  }

  @Override
  public Socket createSocket(String host, int port, InetAddress localAddress, int localPort,
      HttpConnectionParams params)
      throws IOException, UnknownHostException, ConnectTimeoutException {
    if (params == null) {
      throw new IllegalArgumentException("Parameters may not be null");
    }
    int timeout = params.getConnectionTimeout();
    SocketFactory socketfactory = getSSLContext().getSocketFactory();
    if (timeout == 0) {
      return socketfactory.createSocket(host, port, localAddress, localPort);
    } else {
      Socket socket = socketfactory.createSocket();
      SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
      SocketAddress remoteaddr = new InetSocketAddress(host, port);
      socket.bind(localaddr);
      socket.connect(remoteaddr, timeout);
      return socket;
    }
  }



  private static class TrustAnyTrustManager implements X509TrustManager {
    /**
     * 重写验证方法，取消检测SSL
     */
    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
        throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
        throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
      return new X509Certificate[] {};
    }
  }
}
