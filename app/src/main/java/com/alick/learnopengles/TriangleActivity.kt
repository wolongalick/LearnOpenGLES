package com.alick.learnopengles

import com.alick.learnopengles.base.BaseActivity
import com.alick.learnopengles.databinding.ActivityTriangleBinding

/**
 * @author 崔兴旺
 * @description
 * @date 2022/6/19 21:58
 */
class TriangleActivity : BaseActivity<ActivityTriangleBinding>() {

    override fun initListener() {

    }

    override fun initData() {

    }

    override fun onResume() {
        super.onResume()
        viewBinding.myGLSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewBinding.myGLSurfaceView.onPause()
    }
}