import requests
from lxml import etree
import sqlite3


def requestLottery(dbPath, tableName):
    # 获取期数列表
    r = requests.get("http://kaijiang.500.com/dlt.shtml")
    html = etree.HTML(r.text)
    npers = html.xpath('//div[@class="iSelectList"]/a/text()')
    print(npers)

    # 打开数据库连接
    db = sqlite3.connect(dbPath)
    cursor = db.cursor()

    # 获取最新一条
    sql = "SELECT * FROM %s ORDER BY nper DESC LIMIT 1" % (tableName)
    cursor.execute(sql)
    last = cursor.fetchone()
    print("之前最新一条：", last)

    try:
        # 循环取网页数据
        count = 0
        for nper in npers:
            if (last is not None) and (last[0] == nper):
                break

            print("当前期数：", nper)
            # 网页请示
            r = requests.get("http://kaijiang.500.com/shtml/dlt/%s.shtml" % (nper))
            # print('网页' + r.text)
            # 解析
            html = etree.HTML(r.text)
            # 取值
            result = html.xpath('//div[@class="ball_box01"]/ul/li/text()')
            print('网页值:', result)
            if 7 == len(result):
                # 写入
                sql = "INSERT OR IGNORE INTO %s (nper,num1,num2,num3,num4,num5,num6,num7) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')" % (
                    tableName, nper, result[0], result[1], result[2], result[3], result[4],
                    result[5],
                    result[6])
                print("sql语句:" + sql)
                cursor.execute(sql)
            count += 1
            if 30 == count:
                db.commit()
                count = 0
    finally:
        # 关闭
        cursor.close()
        db.commit()
        db.close()
