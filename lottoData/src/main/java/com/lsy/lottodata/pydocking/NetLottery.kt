package com.lsy.lottodata.pydocking

import android.content.Context
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.lsy.lottodata.db.DBManager
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

    suspend fun callLottery(dbPath: String, tableName: String) {
        withContext(Dispatchers.IO) {
            Python.getInstance().getModule("NetLottery")
                .callAttr("requestLottery", dbPath, tableName)
        }
    }
}