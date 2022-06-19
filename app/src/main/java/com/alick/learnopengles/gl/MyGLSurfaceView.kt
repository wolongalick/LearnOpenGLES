package com.alick.learnopengles.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class MyGLSurfaceView(context: Context, attrs: AttributeSet) : GLSurfaceView(context, attrs) {

    private val renderer: Shape

    init {
        setEGLContextClientVersion(2)
        renderer = Triangle()
        setRenderer(renderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }
}