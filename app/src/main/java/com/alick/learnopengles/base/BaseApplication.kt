package com.alick.learnopengles.base

import android.app.Application
import com.alick.learnopengles.utils.AppHolder

/**
 * @author 崔兴旺
 * @description
 * @date 2022/3/13 18:58
 */
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AppHolder.init(this)
    }
}