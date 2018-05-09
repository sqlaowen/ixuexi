package cn.ziran.xspi;

public class ChildSPIService2 implements SPIService {

    @Override
    public void test() {
        System.out.println("==>>SPIService test2");
    }

}
