import random
import re
import requests


def exportData(sqlFmt, sqlCountFmt, databaseId, username, password):
    '''

    :param sqlFmt: 要查询数据的sql
    :param sqlCountFmt: 查询总量的sql
    :param databaseId: 数据库所在dbquery上分配的id
    :param username: dbquery用户名
    :param password: dbquery密码
    :return: 返回文件名
    '''
    my_session = requests.session()
    login_url = "https://ssa.jd.com/sso/login?ReturnUrl=http://dbquery.jd.com/home"
    response = my_session.post(login_url, data={'username': username, 'password': password})
    if None == response.headers.get('Set-Cookie'):
        print('----请输入正确的账号')
        return ''

    url = 'http://dbquery.jd.com/home/ajaxQueryData'
    f = creteFile()
    writeTitle(my_session, f, databaseId, sqlFmt)

    # 外循环查总数
    for i in range(8, 9):
        total, page = getTotalPage(i, my_session, sqlCountFmt, databaseId)
        if 0 == page:
            print('----------------->table:' + str(i) + ' ,total:0')
            continue

        id = 0
        # 内循环查数据
        for j in range(0, page):
            sql = sqlFmt % (str(i), str(id))
            data = {'id': databaseId, 'sql': sql}
            try:
                res = my_session.post(url, data=data)
                jsonObj = res.json()
                lenx = len(jsonObj['rows'])
                print('----------------->table:' + str(i) + ' ,total:' + str(total) + ' ,page:' + str(j + 1))
                for row in jsonObj['rows']:
                    # 数据内容
                    rowStr = ''
                    for r in row:
                        rowStr = rowStr + ',' + (row[r].replace(',', '，') if None != row[r] else '')
                    f.write(rowStr[1:])
                    f.write('\n')

                id = jsonObj['rows'][lenx - 1]['id']
            except Exception as ei:
                print('----------------->inner error')
                print(ei)
    f.flush()
    f.close()

    return f.name


def getTotalPage(tableNo, my_session, sqlCountFmt, databaseId):
    '''
    获取总页面
    :param tableNo: 分表序号
    :param my_session:
    :param sqlCountFmt:
    :param databaseId:
    :return:
    '''
    sqlCount = sqlCountFmt % (str(tableNo))
    data = {'id': databaseId, 'sql': sqlCount}
    res = my_session.post('http://dbquery.jd.com/home/ajaxQueryData', data=data)
    jsonObj = res.json()
    total = int(jsonObj['rows'][0]['count(1)'])
    if 0 == total % 100:
        page = total / 100
    else:
        page = total / 100 + 1
    page = int(page)
    return (total, page)


def creteFile():
    '''
    创建文件，用于保存查询结果
    :return:
    '''
    random.seed()
    fileName = str(random.random()).replace('0.', 'file_')
    f = open('/%s.csv' % (fileName), 'w');
    return f


def writeTitle(my_session, f, databaseId, sqlFmt):
    '''
    写列标头
    :param my_session:
    :param f:
    :param databaseId:
    :param sqlFmt:
    :return:
    '''
    url = 'http://dbquery.jd.com/home/ajaxQuery'
    pattern = re.compile('.*(select.*from[ ]+\w+)', re.I | re.M | re.S)
    match = pattern.match(sqlFmt)
    sql = ''
    if match:
        sql = match.group(1)
    sql = sql + '0 where 1!=1'
    res = my_session.post(url, {'id': databaseId, 'sql': sql})
    jsonObj = res.json()
    title = ''
    for k in jsonObj['keys']:
        title = title + ',' + k['field']
    f.write(title[1:])
    f.write('\n')


if __name__ == '__main__':
    # 业务明细表
    sqlFmt = 'select * from xstore_bill_detail%s where id>%s order by id asc'
    # 业务明细表
    sqlCountFmt = 'select count(1) from xstore_bill_detail%s'

    path = exportData(sqlFmt, sqlCountFmt, 31607, 'wenshiwei', '1qw3!QW#qq')
    print(path)
