package com.lsy.superlotto.bindingAdapter

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion

/**
 * @author Xuwl
 * @date 2021/6/22
 *
 */
@BindingAdapter("htmlText")
fun setHtmlText(textView: TextView, text: String?) {

}

@BindingConversion
fun convertColorToDrawable(color: Int) = ColorDrawable(color)

