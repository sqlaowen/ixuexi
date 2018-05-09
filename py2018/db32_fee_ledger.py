# coding=utf-8

import json
import urllib
import shardUtil

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
    'Cookie': '__jdu=1516074267691534591049; 3AB9D23F7A4B3C9B=U35E564XI3GUNPQ5LU3WOLVBC3LHZP25WQXP35HFIAODOCBUUFYCZWSIHFB4SSHQSLYYQZSX7SIC6HUY3VSKFCRYKI; __jda=95931165.1516074267691534591049.1516074268.1521345586.1522144879.9; __jdv=95931165|settle.shop.7fresh.com|-|referral|-|1522144879260; pinId=BENivHYk9u_XgZHD7j7zXA; pin=test10201; unick=test10201; _tp=C6i1d6BcLKK8BUGZixxs0Q%3D%3D; _pst=test10201; TrackID=1coOGagl32VamU-Abri4tvcbRWo_gPwX5RjqKe4goxRcH4-TUp_1oXCD6P-mbR8l14xF8mfgz5-aR-QcOx2yV9LpJ97v4aMkF5WKRmtIW0Es; PHPSESSID=i577p9pjnaoe2cdqb702056d15; erp1.jd.com=E0F4BBBCF7BB4186D08FFB4C1EB315E536AB059B6471D6AB108198F6BBA6DA3EE780CCD7EA96AF41B0201DD0658273D5C8FDEC286A10F5987979A66B49CDFE25C9BC11BCE70DD0DBCFD402D9089470A3; sso.jd.com=0f13668e0adb418082dee2d6e6327c1d',
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
f = open('d:/xstore_binvoice_fee_ledger.txt', 'w')
strFmt = '%s,%s,%s,%s,%s,%s,%s,%s,%s,%s '

url = 'http://dbquery.jd.com/home/ajaxQueryData'

# 开票费用台账表
sqlFmt = 'select id,uuid,account_id,fee_code,remark,should_billing_amount,real_billing_amount,frozen_billing_amount,created,modified from xstore_binvoice_fee_ledger%s where id>%s order by id asc'

# 开票费用台账表
sqlCountFmt = 'select count(1) from xstore_binvoice_fee_ledger%s'

zongshu = 0
# 外循环查总数
for i in range(0, 32):
    sqlCount = sqlCountFmt % (str(i))
    body = {'id': 31607, 'sql': sqlCount}
    try:
        response, content = htp.request(url, 'POST', headers=headers, body=urllib.parse.urlencode(body))
        # print(content)
        total = int(json.loads(content)['rows'][0]['count(1)'])
        # print('----------------->table:' + str(i) + ' ,total:' + str(total))
        zongshu = zongshu + total
        # page = 0
        if 0 == total % 100:
            page = total / 100
        else:
            page = total / 100 + 1
        page = int(page)
        # page = 0
        if 0 == page:
            continue
        id = 0
        # 内循环查数据
        for j in range(0, page):
            sql = sqlFmt % (str(i), str(id))
            body = {'id': 31607, 'sql': sql}
            try:
                response, content = htp.request(url, 'POST', headers=headers, body=urllib.parse.urlencode(body))
                xlist = json.loads(content);
                lenx = len(xlist['rows'])
                print('----------------->table:' + str(i) + ' ,total:' + str(lenx) + ' ,page:' + str(j + 1))
                id = xlist['rows'][lenx - 1]['id']
                for x in xlist:
                    if x == 'rows':
                        for r in xlist[x]:
                            f.write(strFmt % (
                            r['id'], r['uuid'], r['account_id'], r['fee_code'], r['remark'], r['should_billing_amount'],
                            r['real_billing_amount'], r['frozen_billing_amount'], r['created'], r['modified']))

                            f.write('\n')
            except Exception as ei:
                print('----------------->inner error')
                print(ei)
    except Exception as eo:
        print('----------------->out error')
        print(eo)
print('----------------->zongshu:' + str(zongshu))
f.flush()
f.close()
