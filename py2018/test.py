import math
import os

import requests
from bs4 import BeautifulSoup
import ExportDataUtil
import re

# 取hash码
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


# print(math.fabs(-1))

# print(getHashCode('30986310000'))
# print(math.fabs(getHashCode('30986310000')) % 32)
# print(getHashCode('30986310000') % 32)
# print(getSharding('33772910000'))

print(getSharding('33776110000'))

# strDDLFmt = '''
# ALTER TABLE `xstore_bill_detail%s`
# ADD COLUMN `del_status`  int(2) NULL DEFAULT 0 COMMENT '逻辑删除状态[0:可用,1:删除]' AFTER `remark`;
# '''
# for i in range(0, 32):
#     strDDL = strDDLFmt % (str(i))
#     print(strDDL)


# # 业务明细表
# sqlFmt = 'select * from xstore_bill_detail%s where invoice_method=-20 and id>%s order by id asc'
# sqlFmt='select id,account_id from xstore_verification_bill%s where status=10 where id>%s order by id asc'
# # 业务明细表
# sqlCountFmt = 'select count(1) from xstore_bill_detail%s where invoice_method=-20'
# sqlCountFmt='select count(1) from xstore_verification_bill%s where status=10'
#
# ExportDataUtil.exportData(sqlFmt, sqlCountFmt, 31607, 'wenshiwei', '1qw3!QW#qq')


list1=('30986310000=10109=北京京东尚科技信息技术有限公司'
       ,'32243710000=20506=怡宝供应商_测试'
       ,'32496110000=20364=人参果树_test_名称和营业执照号修改'
       ,'32548710000=30606=怡宝供应商_测试'
       ,'32667710000=30681=小浣熊测试'
       ,'33072210000=30822=重庆恒都康美电子商务有限公司'
       ,'33092510000=30842=河北边氏农业科技发展有限公司'
       ,'33146110000=30848=北京鑫京香食品有限公司'
       ,'33312310000=30824=正大电子商务（浙江）有限公司'
       ,'33372010000=31322=招商局食品（深圳）有限公司'
       ,'33413010000=31123=深圳市瑞农进出口有限公司'
       ,'33772910000=31061=上海妙食商贸有限公司'
       ,'33816210000=31442=北京市金元子商贸有限公司'
       ,'33998810000=31581=质保金测试2'
       ,'34292310000=31662=杭州大热电子商务有限公司'
       ,'34600710000=31821=北京圃美多绿色食品有限公司')

# f=open('d:/业务单据明细.csv','r')
# i=0
# for line in f:
#     if 0== i:
#         i=i+1
#         continue
#     arr1=line.strip().split(',')
#     accId=arr1[15]
#     #print(accId)
#     for l1 in list1:
#         if accId == l1.split('=')[0]:
#             line=l1.replace('=',',')+','+line.strip()
#             print(line)

