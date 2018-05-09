#coding=utf-8

import re

#path : 源sql文件
#dbcount : 共分多少库
#tbcount : 共分多少表
def shard(path, dbcount, tbcount):
    f=file(path,'r+')
    s=f.read()
    arr1 = re.findall(r'(create[ ]+table[ ]+\w+[^;]*)',s) # 查询表创建语句
    arr2 = re.findall(r'alter[ ]+table[ ]+\w+.*;', s) # 查询表名comment语句
    f.close()
    '''
    for i in range(0, len(arr1)):
        print arr1[i]
        print '--------------------------'
        print arr2[i]
        print '=========================='
    '''
    f1=file('d:/x.txt','w')
    for i in range(0, dbcount): #共分多少个库
        dbname='price_'+ str(i)
        sql='CREATE DATABASE IF NOT EXISTS '+dbname+' default charset utf8 COLLATE utf8_general_ci '
        sql+='; \r\n'
        for j in range(0, tbcount): #共分多少个表
            if(i == j%dbcount):
                for x in range(0, len(arr1)):
                    c=re.compile(r'create[ ]+table[ ]+(\w+)[^;]*')
                    tbname = c.search(arr1[x]).group(1)
                    sql+=arr1[x].replace(tbname,'IF NOT EXISTS `'+dbname+'`.`'+tbname+'_'+str(j)+'`')
                    sql+='; \r\n'

        sql+='--------------------------------------------------------------------------------\r\n'*5
        f1.write(sql)
        f1.flush()
    f1.close()


shard('D:/sql.txt', 8, 64)