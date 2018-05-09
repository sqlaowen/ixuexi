#coding=utf-8

import urllib
import httplib2


htp = httplib2.Http()
url='http://fbc.soa.pop.jd.local/manager/doLogin.action'
body = {'username': 'root', 'password': '123456'}
headers = {'Content-type': 'application/x-www-form-urlencoded'}
response, content = htp.request(url, 'POST', headers=headers, body=urllib.urlencode(body))

#print response
#print '------------------------------------------------------------------------------------\r\n'*10
#print content

headers = {'Cookie': response['set-cookie']} #获取cookie

f = file('d:/order.txt','r')
for line in f:
    urlfmt = 'http://fbc.soa.pop.jd.local/manager/console_resetSyncStatusAndSendToFeeDetail.action?orderId=%s&type=service'
    url= urlfmt % line.replace('\r\n','')
    response, content = htp.request(url, 'GET', headers=headers)  #使用获取到的cookie
    print content

f.close()