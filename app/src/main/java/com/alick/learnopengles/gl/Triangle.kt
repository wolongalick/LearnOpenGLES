package com.alick.learnopengles.gl

import android.opengl.GLES20
import com.alick.learnopengles.R
import com.alick.learnopengles.utils.RawUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * @author 崔兴旺
 * @description
 * @date 2022/6/18 22:28
 */
class Triangle : Shape() {

    private var vertexBuffer: FloatBuffer? = null
    private var mProgram = 0

    val COORDS_PER_VERTEX = 3
    var triangleCoords = floatArrayOf(
        0.5f, 0.5f, 0.0f,  // top
        -0.5f, -0.5f, 0.0f,  // bottom left
        0.5f, -0.5f, 0.0f // bottom right
    )

    private var mPositionHandle = 0
    private var mColorHandle = 0

    private val mViewMatrix = FloatArray(16)

    //顶点个数
    private val vertexCount = triangleCoords.size / COORDS_PER_VERTEX

    //顶点之间的偏移量
    private val vertexStride = COORDS_PER_VERTEX * 4 // 每个顶点四个字节


    private val mMatrixHandler = 0

    //设置颜色，依次为红绿蓝和透明通道
    var color = floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f)

    private val vertexShaderCode = RawUtils.getRawContent(R.raw.triangle_vert)
    private val fragmentShaderCode = RawUtils.getRawContent(R.raw.triangle_frag)


    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        //将背景设置为灰色
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f)
        //申请底层空间
        val bb = ByteBuffer.allocateDirect(
            triangleCoords.size * 4
        )
        bb.order(ByteOrder.nativeOrder())

        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer!!.put(triangleCoords)
        vertexBuffer!!.position(0)
        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

        //创建一个空的OpenGLES程序
        mProgram = GLES20.glCreateProgram()
        //将顶点着色器加入到程序
        GLES20.glAttachShader(mProgram, vertexShader)
        //将片元着色器加入到程序中
        GLES20.glAttachShader(mProgram, fragmentShader)
        //连接到着色器程序
        GLES20.glLinkProgram(mProgram)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram)
        //获取顶点着色器的vPosition成员句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false,
            vertexStride, vertexBuffer
        )
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle)
    }

}