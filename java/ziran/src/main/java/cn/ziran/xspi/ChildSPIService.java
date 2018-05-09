package cn.ziran.xspi;

public class ChildSPIService implements SPIService {

    @Override
    public void test() {
        System.out.println("==>>ChildSPIService implements SPIService test");
    }

}
