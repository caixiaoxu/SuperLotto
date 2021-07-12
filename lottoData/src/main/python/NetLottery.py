import requests
from lxml import etree
from java import jclass


def requestLottery(lastNper):
    # 获取期数列表
    r = requests.get("http://kaijiang.500.com/dlt.shtml")
    html = etree.HTML(r.text)
    npers = html.xpath('//div[@class="iSelectList"]/a/text()')
    print(npers)

    list = []
    # 循环取网页数据
    for nper in npers:
        if (lastNper is not None) and (lastNper == nper):
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
            LotteryNumber = jclass("com.lsy.lottodata.db.entity.LotteryNumber")  # 用自己的包名
            list.append(LotteryNumber("%s" % nper, "%s" % (result[0]), "%s" % (result[1]),
                                      "%s" % (result[2]), "%s" % (result[3]), "%s" % (result[4]),
                                      "%s" % (result[5]), "%s" % (result[6])))
    return list
