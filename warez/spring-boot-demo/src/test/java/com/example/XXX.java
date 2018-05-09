package com.example;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wenshiwei on 2017/5/18.
 */
public class XXX implements Serializable {

//    public static void main(String[] args) {
//        BigDecimal b1=new BigDecimal("99.89");
//        BigDecimal b2=new BigDecimal("1.36");
//        BigDecimal b3=new BigDecimal("15.11");
//        BigDecimal b4 = b1.subtract(b2).subtract(b3);
//        System.out.println(b4);
//    }

    public static void main(String[] args) {
        Long l1=1L;
        System.out.println(l1.hashCode()%32);
        Integer i1=1;
        System.out.println(i1.hashCode()%32);

    }

    private int id;
    private String name;
    private Date date;

    @Override
    public String toString() {
        return "XXX{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
