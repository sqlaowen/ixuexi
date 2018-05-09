package com.warez.utils;

import org.junit.Test;

/**
 * Created by wenshiwei on 2016/10/12.
 */
public class RandomGUIDTest {

    @Test
    public void test01() {
        RandomGUID myGUID = new RandomGUID();
        System.out.println("Seeding String=" + myGUID.valueBeforeMD5);
        System.out.println("rawGUID=" + myGUID.valueAfterMD5);
        System.out.println("RandomGUID=" + myGUID.toString());
        System.out.println(new RandomGUID().toString());
        System.out.println("-------------------------------------------------");
    }
}
