# coding=utf-8

import json
import urllib

import httplib2

htp = httplib2.Http()
# url = 'http://fin.soa.pop.jd.com/manager/doLogin.action'
# body = {'username': 'root', 'password': '123456'}
# headers = {'Content-type': 'application/x-www-form-urlencoded'}
# response, content = htp.request(url, 'POST', headers=headers, body=urllib.parse.urlencode(body))
#
# headers = {'Cookie': response['set-cookie']}  # 获取cookie

headers = {
    'Accept': 'application/json, text/javascript, */*; q=0.01',
    'Accept-Encoding': 'gzip, deflate',
    'Accept-Language': 'zh-CN,zh;q=0.8',
    'Connection': 'keep-alive',
    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    'Cookie': '__jdu=1516074267691534591049; TrackID=11A2DpFgEB0PW8-xooa7xZlyzUgDlgJSWy6QI88RVK11NsgQ7rXDZb6xAmaLcRqncZm-nNAYUMzitkshoVy3BtDswIMuiTSE8Oa3HJEDiuoQ; pinId=iGZp4k6NELg; 3AB9D23F7A4B3C9B=U35E564XI3GUNPQ5LU3WOLVBC3LHZP25WQXP35HFIAODOCBUUFYCZWSIHFB4SSHQSLYYQZSX7SIC6HUY3VSKFCRYKI; ipLoc-djd=1-72-2799-0; __jdv=78157059%7Cdirect%7C-%7Cnone%7C-%7C1520843287401; __jda=122270672.1516074267691534591049.1516074268.1520843326.1521345586.8; PCSYCityID=1; PHPSESSID=nh259amed08smjt0lpadebq7t3; erp1.jd.com=6085E2E38D868EC90ACF4D759F6534148B89516854203380F481EEBABEE6375316332F4D940A79BA1E547220E77C131D9E6D25FF67CFF04844DE733C87F5B3A457F0859C53DF3B9ABCBB983CBB630576; sso.jd.com=bbdcb0cb3da9490c9993e56efbde3418',
    'Host': 'dbquery.jd.com',
    'Origin': 'http://dbquery.jd.com',
    'Referer': 'http://dbquery.jd.com/home/addTab.php?id=18152',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36',
    'X-Requested-With': 'XMLHttpRequest'
}

# popfinbase id=15211
tableIdDict = {0: 15207, 1: 15208, 2: 18152, 3: 18153, 4: 15212, 5: 15213, 6: 18154, 7: 18155, 8: 15218, 9: 15219,
               10: 18156, 11: 18157, 12: 15220, 13: 15221, 14: 18158, 15: 18159, 16: 15224, 17: 15225, 18: 18160,
               19: 18161, 20: 15228, 21: 15229, 22: 18162, 23: 18163, 24: 15232, 25: 15233, 26: 18164, 27: 18165,
               28: 15236, 29: 15237, 30: 18166, 31: 18167}

f = open('d:/accountId.txt', 'w');
strFmt = '%s'

url = 'http://dbquery.jd.com/home/ajaxQueryData'

# 要查询的数据
sqlFmt = 'select id,rf_bill_no,account_id,uuid from xstore_rf_bill_info%s where detail_count=0 and bill_status=2 and id>%s order by id asc'
sqlFmt = "select id,account_id,order_type,rf_busi_id,rf_bill_detail_no,rf_busi_type  from xstore_bill_detail%s where created<'2018-01-27' and id>%s order by id asc"
# 任务表
sqlFmt = "select id,uuid,account_id,rf_bill_detail_no,task_type,task_status,rf_bill_type,rf_bill_no,handle_time,handle_num,created,modified,remark from xstore_binvoice_task%s where task_type ='DETAIL_PREPARE' and task_status =1 and id>%s order by id asc"
# 核销申请单表
sqlFmt = "select id,bill_code,account_id,verification_amount,invoice_amount_total,invoice_count,status,reject_reason,created,audit_time,modified,remark,buy_company_name,buy_taxpayer_number,buy_company_address,buy_company_phone,buy_bank_name,buy_bank_account,sell_company_name,sell_taxpayer_number,sell_company_address,sell_company_phone,sell_bank_name,sell_bank_account,applicant,approve from xstore_verification_bill%s where id>%s order by id asc"
# 核销通过
sqlFmt = "select t1.id,t1.bill_code,t1.verification_amount,t1.audit_time,t2.rf_bill_no ,t2.amount from xstore_verification_bill%s t1 left join xstore_verification_flow%s t2 on t1.id = t2.bill_id and t1.account_id = t2.account_id where t1.status = 20 AND t1.id>%s order by t1.id asc"

sqlFmt = 'select id,account_id from fin_accounts_business_relation where system_source=13 and id>%s'

# 要查询的数据总条数
sqlCountFmt = 'select count(1) from xstore_bill_detail%s where status=5'
sqlCountFmt = "select count(1) from xstore_bill_detail%s where created<'2018-01-27'"
# 任务表
sqlCountFmt = "select count(1) from xstore_binvoice_task%s where task_type ='DETAIL_PREPARE' and task_status =1"
# 核销申请单表
sqlCountFmt = "select count(1) from xstore_verification_bill%s"
# 核销通过
sqlCountFmt = "select count(1) from xstore_verification_bill%s t1 left join xstore_verification_flow%s t2 on t1.id = t2.bill_id and t1.account_id = t2.account_id where t1.status = 20"

zongshu = 0
# 外循环查总数

id = 0
# 内循环查数据
for j in range(0, 2):
    sql = sqlFmt % (str(id))
    body = {'id': 15211, 'sql': sql}
    try:
        response, content = htp.request(url, 'POST', headers=headers, body=urllib.parse.urlencode(body))
        xlist = json.loads(content);
        lenx = len(xlist['rows'])
        print('----------------->table:' + ' ,total:' + str(lenx) + ' ,page:' + str(j + 1))
        id = xlist['rows'][lenx - 1]['id']
        for x in xlist:
            if x == 'rows':
                for r in xlist[x]:
                    f.write(strFmt % (r['account_id']))
                    f.write('\n')
    except Exception as ei:
        print('----------------->inner error')
        print(ei)

print('----------------->zongshu:' + str(zongshu))
f.flush()
f.close()
