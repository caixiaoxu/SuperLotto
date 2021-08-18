package com.frame.versionplugin

/**
 * Title : AndroidX 库
 * Author: Lsy
 * Date: 2/5/21 2:16 PM
 * Version:
 * Description: appcompat中默认引入了很多库(比如fragment库、core库、annotation库等)，如果想使用其中某个库的更新版本，可以单独引用
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"
    const val coreKtx = "androidx.core:core-ktx:1.3.1"
    const val activityKtx = "androidx.activity:activity-ktx:1.2.3"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.4"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"

    val room_version = "2.3.0"
    val room = "androidx.room:room-runtime:$room_version"
    val roomCompiler = "androidx.room:room-compiler:$room_version"
    val roomKtx = "androidx.room:room-ktx:$room_version"
    val roomRxjava3 = "androidx.room:room-rxjava3:$room_version"
    val roomGuava = "androidx.room:room-guava:$room_version"

    val paging_version = "3.0.0"
    val paging = "androidx.paging:paging-runtime:$paging_version"
    val paging_common_test = "androidx.paging:paging-common:$paging_version"
    val paging_rxjava2 = "androidx.paging:paging-rxjava2:$paging_version"
    val paging_rxjava3 = "androidx.paging:paging-rxjava3:$paging_version"
    val paging_guava = "androidx.paging:paging-guava:$paging_version"
    val paging_compose = "androidx.paging:paging-compose:1.0.0-alpha11"

    val datastore_version = "1.0.0-rc01"
    val datastore = "androidx.datastore:datastore:$datastore_version"
}