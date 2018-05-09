package cn.ziran.xspi;

import java.util.Iterator;

public class SpiTest {

    /**
     * @param args
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
                                          ClassNotFoundException {
        Iterator it = sun.misc.Service.providers(SPIService.class);
        while (it.hasNext()) {
            SPIService service = (SPIService) it.next();
            service.test();
        }
    }
}
