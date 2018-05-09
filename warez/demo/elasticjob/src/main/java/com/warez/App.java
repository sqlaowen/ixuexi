package com.warez;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by iyou on 2016/12/3.
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        startService();
        logger.debug("服务已启动...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            String input = "";
            if (br.ready()) {
                input = br.readLine();
            }
            if (input.equals("stop")) {
                break;
            }
        } while (br != null);
        br.close();
    }

    static void startService() {
        new ClassPathXmlApplicationContext("notenv/applicationContext.xml");
    }
}
