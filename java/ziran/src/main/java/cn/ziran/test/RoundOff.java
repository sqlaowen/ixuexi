package cn.ziran.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by wenshiwei on 2016/11/25.
 */
public class RoundOff {

    public static void main(String[] args) {
        System.out.println("12.5的四舍五入值：" + Math.round(12.5));
        System.out.println("-12.5的四舍五入值：" + Math.round(-12.5));
        System.out.println();

//        目前Java支持7中舍入法：
//        1、 ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。
//        2、 ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。
//        3、 ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于ROUND_UP，若为负数，舍入行为类似于ROUND_DOWN。Math.round()方法就是使用的此模式。
//        4、 ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于ROUND_DOWN；若为负数，舍入行为类似于ROUND_UP。
//        5、 HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。
//        6、 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。
//        7、 HAIL_EVEN：银行家舍入法。
        System.out.println(BigDecimal.valueOf(0.42601).setScale(3, RoundingMode.UP));
        System.out.println(BigDecimal.valueOf(0.42696).setScale(3, RoundingMode.DOWN));
        System.out.println(BigDecimal.valueOf(0.42601).setScale(3, RoundingMode.CEILING));
        System.out.println(BigDecimal.valueOf(-0.42601).setScale(3, RoundingMode.CEILING));
        System.out.println(BigDecimal.valueOf(0.42601).setScale(3, RoundingMode.FLOOR));
        System.out.println(BigDecimal.valueOf(-0.42601).setScale(3, RoundingMode.FLOOR));
        System.out.println(BigDecimal.valueOf(0.42601).setScale(3, RoundingMode.HALF_UP));
        System.out.println(BigDecimal.valueOf(0.42601).setScale(3, RoundingMode.HALF_DOWN));
        System.out.println(BigDecimal.valueOf(0.42601).setScale(3, RoundingMode.HALF_EVEN));
        System.out.println();

        BigDecimal d = new BigDecimal(100000);      //存款
        BigDecimal r = new BigDecimal(0.001875 * 3);   //利息
        BigDecimal i = d.multiply(r).setScale(2, RoundingMode.HALF_EVEN);     //使用银行家算法
        System.out.println("季利息是：" + i);
        System.out.println();

        System.out.println(new DecimalFormat("#.00").format(3.125));
        System.out.println(String.format("%.2f", 3.125));
    }
}
