import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by wenshiwei on 2017/6/2.
 */
public class XXX {

    private static Logger log = LoggerFactory.getLogger(XXX.class);

    public static void main(String[] args) throws Exception {

        BigDecimal b1 = new BigDecimal(0.5);
        BigDecimal b2 = new BigDecimal(0.2);
        System.out.println(b1.divide(b2, 2, RoundingMode.HALF_EVEN));
        BigDecimal B3 = b1.divide(b2, 2, RoundingMode.HALF_EVEN);
        B3 = B3.setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(B3);

        Long l1=Long.valueOf("2");
        if(l1>1){
            System.out.println(">>>>>>>>>>>>>>>>>>");
        } else {
            System.out.println("<<<<<<<<<<<<<<<<<<<");
        }
    }


}
