package com.lsy.superlotto

import android.app.Application
import com.lsy.lottodata.db.DBManager
import com.lsy.lottodata.pydocking.NetLottery

/**
 * @author Xuwl
 * @date 2021/6/22
 *
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DBManager.initDB(applicationContext)
        NetLottery.initPython(applicationContext)
    }
}