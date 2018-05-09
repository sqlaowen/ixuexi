package com.warez;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by iyou on 2016/12/15.
 */
public class SocketHTTPTest {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public void sendGet() throws IOException {

        String path = "https://item.jd.com/3717578.html";
        path="http://blog.csdn.net/jia20003/article/details/17104791";
        URL url = new URL(path);
        // HTTP协议
       socket = new Socket(url.getHost(), url.getDefaultPort());
        // HTTPS协议
       // socket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(url.getHost(), url.getDefaultPort());
//        SocketAddress dest = new InetSocketAddress(url.getHost(), url.getDefaultPort());
//        socket.connect(dest);
        OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream());
        bufferedWriter = new BufferedWriter(streamWriter);

        bufferedWriter.write("GET " + path + " HTTP/1.1\r\n");
        bufferedWriter.write("Host: " + url.getHost() + "\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();

        BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "gbk"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }

    public void sendPost() throws IOException {
        String path = "https://item.jd.com/3717578.html";
        URL url = new URL(path);
        String data = URLEncoder.encode("name", "gbk") + "=" + URLEncoder.encode("gloomyfish", "gbk") + "&" +
                URLEncoder.encode("age", "gbk") + "=" + URLEncoder.encode("32", "gbk");
        // String data = "name=zhigang_jia";
//        socket = new Socket();
//        SocketAddress dest = new InetSocketAddress(url.getHost(), url.getDefaultPort());
//        socket.connect(dest);
        socket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(url.getHost(), url.getDefaultPort());

        OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream(), "gbk");
        bufferedWriter = new BufferedWriter(streamWriter);

        bufferedWriter.write("POST " + path + " HTTP/1.1\r\n");
        bufferedWriter.write("Host: " + url.getHost() + "\r\n");
        bufferedWriter.write("Content-Length: " + data.length() + "\r\n");
        bufferedWriter.write("Content-Type: application/x-www-form-urlencoded\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();

        BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "gbk"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }

    public static void main(String[] args) {
        SocketHTTPTest td = new SocketHTTPTest();
        try {
            td.sendGet(); //send HTTP GET Request

//            td.sendPost(); // send HTTP POST Request
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
