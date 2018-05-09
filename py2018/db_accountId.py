# coding=utf-8

import json
import math
import urllib

import httplib2

htp = httplib2.Http()

headers = {
    'Accept': 'application/json, text/javascript, */*; q=0.01',
    'Accept-Encoding': 'gzip, deflate',
    'Accept-Language': 'zh-CN,zh;q=0.8',
    'Connection': 'keep-alive',
    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    'Cookie': '__jdu=1516074267691534591049; 3AB9D23F7A4B3C9B=U35E564XI3GUNPQ5LU3WOLVBC3LHZP25WQXP35HFIAODOCBUUFYCZWSIHFB4SSHQSLYYQZSX7SIC6HUY3VSKFCRYKI; __jda=95931165.1516074267691534591049.1516074268.1521345586.1522144879.9; __jdv=95931165|settle.shop.7fresh.com|-|referral|-|1522144879260; pinId=BENivHYk9u_XgZHD7j7zXA; pin=test10201; unick=test10201; _tp=C6i1d6BcLKK8BUGZixxs0Q%3D%3D; _pst=test10201; TrackID=1coOGagl32VamU-Abri4tvcbRWo_gPwX5RjqKe4goxRcH4-TUp_1oXCD6P-mbR8l14xF8mfgz5-aR-QcOx2yV9LpJ97v4aMkF5WKRmtIW0Es; PHPSESSID=i577p9pjnaoe2cdqb702056d15; erp1.jd.com=E0F4BBBCF7BB4186D08FFB4C1EB315E536AB059B6471D6AB108198F6BBA6DA3EE780CCD7EA96AF41B0201DD0658273D5C8FDEC286A10F5987979A66B49CDFE25C9BC11BCE70DD0DBCFD402D9089470A3; sso.jd.com=0f13668e0adb418082dee2d6e6327c1d',
    'Host': 'dbquery.jd.com',
    'Origin': 'http://dbquery.jd.com',
    'Referer': 'http://dbquery.jd.com/home/addTab.php?id=18152',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36',
    'X-Requested-With': 'XMLHttpRequest'
}


def convert_n_bytes(n, b):
    bits = b * 8
    return (n + 2 ** (bits - 1)) % 2 ** bits - 2 ** (bits - 1)


def convert_4_bytes(n):
    return convert_n_bytes(n, 4)


def getHashCode(s):
    h = 0
    n = len(s)
    for i, c in enumerate(s):
        h = h + ord(c) * 31 ** (n - 1 - i)
    return convert_4_bytes(h)


def getSharding(str1):
    # hashCode
    hc = getHashCode(str1)
    # 绝对值
    hcAbs = math.fabs(hc)
    # 分表结果
    return int(hcAbs % 32)


f1 = open('d:/xstore_rf_bill_info.txt', 'w')
strFmt = '%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s'

url = 'http://dbquery.jd.com/home/ajaxQueryData'

# 业务单据
sqlFmt = 'select id,rf_bill_type,uuid,rf_bill_no,detail_count,account_id,begin_date,end_date,verification_status,should_verification_amount,real_verification_amount,frozen_verification_amount,verification_date,bill_status,rf_bill_finish_time,created,modified,remark,payment_status,rf_bill_payment_time,return_status from xstore_rf_bill_info%s where rf_bill_no="%s"'

list1 = ('33146110000N9002_33146110000'
         ,'33413010000N16040_33413010000'
         ,'62112713_32548710000'
         ,'34292310000N22060_34292310000'
         ,'65627664_30986310000'
         ,'65297283_32667710000'
         ,'34600710000N22050_34600710000'
         ,'33816210000N22113_33816210000'
         ,'66351808_30986310000'
         ,'63800177_32667710000'
         ,'33312310000N9052_33312310000'
         ,'71076047_33072210000'
         ,'33092510000N9101_33092510000'
         ,'33372010000N9068_33372010000'
         ,'61948973_32496110000'
         ,'33146110000N22073_33146110000'
         ,'33312310000N22106_33312310000'
         ,'34600710000N9021_34600710000'
         ,'71076170_33312310000'
         ,'60979222_32243710000'
         ,'74895457_33772910000'
         ,'73769081_33998810000')

for line in list1:
    arr1 = line.split('_')
    accountId = arr1[1]
    rfBillNo = arr1[0]
    tableN0 = int(math.fabs(getHashCode(accountId)) % 32)
    sql = sqlFmt % (str(tableN0), rfBillNo)
    #print(sql)
    body = {'id': 31607, 'sql': sql}
    try:
        response, content = htp.request(url, 'POST', headers=headers, body=urllib.parse.urlencode(body))
        #print(content)
        xlist = json.loads(content);
        lenx = len(xlist['rows'])
        if 0 == lenx:
            print(sql)
            continue
        # print('-------------->line:' + line.strip())
        for x in xlist:
            if x == 'rows':
                for r in xlist[x]:
                    f1.write(
                        strFmt % (r['id'],r['rf_bill_type'],r['uuid'],r['rf_bill_no'],r['detail_count'],r['account_id'],r['begin_date'],r['end_date'],r['verification_status'],r['should_verification_amount'],r['real_verification_amount'],r['frozen_verification_amount'],r['verification_date'],r['bill_status'],r['rf_bill_finish_time'],r['created'],r['modified'],r['remark'],r['payment_status'],r['rf_bill_payment_time'],r['return_status']))
                    f1.write('\n')
    except Exception as ei:
        print('----------------->i error')
        print(ei)

f1.flush()
f1.close()
