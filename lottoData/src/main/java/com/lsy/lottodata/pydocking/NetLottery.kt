package com.lsy.lottodata.pydocking

import android.content.Context
import android.util.Log
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.db.entity.LotteryNumber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Xuwl
 * @date 2021/6/22
 *
 */
object NetLottery {

    fun initPython(context: Context) {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(context))
        }
    }

    suspend fun callLottery(lastNper: String) {
        withContext(Dispatchers.IO) {
            //抓取数据
            val pyObj = Python.getInstance().getModule("NetLottery")
                .callAttr("requestLottery", lastNper)
            val list = ArrayList<LotteryNumber>()
            pyObj.asList().forEach {
                list.add(it.toJava(LotteryNumber::class.java))
            }
            //写入数据库
            DBManager.db.lotteryNumberDao().insertLotterysTable(list)
        }
    }
}