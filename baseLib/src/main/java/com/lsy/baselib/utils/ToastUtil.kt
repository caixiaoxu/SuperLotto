package com.lsy.baselib.utils

import android.content.Context
import android.widget.Toast

/**
 * @author Xuwl
 * @date 2021/7/1
 *
 */
object ToastUtil {
    fun showToast(context: Context, msg: String) {
        showShortToast(context, msg)
    }

    fun showShortToast(context: Context, msg: String) {
        showBaseToast(context, msg, Toast.LENGTH_SHORT)
    }

    fun showLongToast(context: Context, msg: String) {
        showBaseToast(context, msg, Toast.LENGTH_LONG)
    }

    fun showBaseToast(context: Context, msg: String, duration: Int) {
        Toast.makeText(context, msg, duration).show()
    }
}