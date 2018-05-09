# coding=utf-8

import urllib
import httplib2
import shardUtil
import requests

my_session = requests.session();

res = my_session.post('http://ci.soa.pop.jd.local/doLogin.action',{'username':'root','password':'123456'});

# res = my_session.get('http://invoice.7fresh.com/console/saveQualication?pin=jdpos&title=北京天极峰传媒科技有限公司&mobile=a-4LWPDCMS+6bv4NuheWXD2w==&taxNo=91110117593889545R');
# print(res.text)

urlFmt='http://invoice.7fresh.com/console/saveQualication?pin=jdpos&title=%s&mobile=%s&taxNo=%s'

f=open('d:/x.csv','r')
for line in f:
    arr1=line.strip().split(',')
    url=urlFmt % tuple(arr1)
    print(url)
    # res = my_session.get(url);
    # print(res.text)


# f = open('d:/xx.txt', 'r')
# urlFmt = 'http://binvoice.7fresh.com/console/editBillDetailColumn?accountId=%s&id=%s&orderType=%s&rfBusiId=%s'
# urlFmt = 'http://binvoice.7fresh.com/console/editVerificationColumn?accountId=%s&id=%s'
# urlFmt = 'http://127.0.0.1:8080/console/execSql?sql=update xstore_bill_detail%s set invoice_method=20,remark=null,modified=now() where id=%s and invoice_method=-20'
# urlFmt = "http://binvoice.7fresh.com/console/execSql?sql=update xstore_verification_bill%s set buy_company_phone ='010-50958228' where status=10 and account_id=%s and id=%s"
#
# list1=[
#     '851,30986310000'
#     ,'901,30986310000'
#     ,'1051,30986310000'
#     ,'3852,33772910000'
#     ,'3803,34238210000'
#     ,'2651,33810410000'
#     ,'3801,37125410000'
#     ,'3455,33332310000'
#     ,'3802,37542010000'
#     ,'4002,31132310000'
#     ,'3853,33372010000'
#     ,'4001,35046210000'
#     ,'3202,33312310000'
#     ,'3753,33307810000'
#     ,'3203,33062110000'
#     ,'3452,37808910000'
#     ,'3754,33438610000'
#     ,'1809,33096510000'
#     ,'2951,34460610000'
#     ,'3008,34728510000'
#     ,'3301,33998110000'
#     ,'3752,34618510000'
#     ,'3456,33291510000'
#     ,'3453,33092510000'
# ]
# # for line in f:
# #     arr1 = line.strip().split(',')
# #     id = arr1[3]
# #     accId = arr1[0]
# #     tableNo = shardUtil.getSharding(accId)
# #     url = urlFmt % (tableNo, id)
# for line in list1:
#     arr1=line.split(',');
#     id=arr1[0]
#     accId=arr1[1]
#     tableNo=shardUtil.getSharding(accId)
#     url=urlFmt % (tableNo, accId, id)
#     print(url)
#     res = my_session.get(url);
#     print(res.text)
#     print('----------------------------------------')
#
# f.close()
