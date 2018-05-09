package com.example.demo;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: ${todo}
 * @author: wenshiwei
 * @date: 2018-03-26
 */
public class test {


    public static void main(String[] args) {

        System.out.println("2171110000".hashCode()%32);
        LinkedList list = new LinkedList() {{
            add(AreaEnum.SHANGQIU);
            add(AreaEnum.BEIJING);
            add(AreaEnum.HENAN);
            addLast(AreaEnum.SHANGHAI);
        }};

        FlowEnum flowEnum = FlowEnum.getEnum("001");
        LinkedList flow = flowEnum.getFlow();
        Object cur = flow.get(flow.indexOf(2));


    }

}
