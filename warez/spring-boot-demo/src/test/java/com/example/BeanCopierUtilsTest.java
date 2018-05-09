package com.example;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @author:wenshiwei
 * @description:
 * @date:2018/01/06
 */
public class BeanCopierUtilsTest {

    public static void main(String[] args) {
        InvoiceBusinessBillLedgerQueryMO invoiceAddMO = JSON.parseObject(null, InvoiceBusinessBillLedgerQueryMO.class);
        System.out.println(invoiceAddMO);

        PageQuery<InvoiceBusinessBillLedgerQueryMO> moPageQuery = new PageQuery<InvoiceBusinessBillLedgerQueryMO>() {{
            setCurrentPage(9);
            setPageSize(3);
            setData(new InvoiceBusinessBillLedgerQueryMO() {{
                setAccountId(1L);
                setBeginDate(new Date());
                setRfBillNo("001");
                setVerificationStatus(1);
            }});
        }};

        PageQuery<InvoiceBusinessBillLedgerQueryMO> pageQuery = new PageQuery<>();
        BeanCopierUtils.clonePropertise(moPageQuery, pageQuery);
        InvoiceBusinessBillLedgerQueryMO data = pageQuery.getData();
        data.setAccountId(89L);
        //BeanCopierUtils.copyPropertise(moPageQuery, pageQuery);
        System.out.println("===================");
    }
}
