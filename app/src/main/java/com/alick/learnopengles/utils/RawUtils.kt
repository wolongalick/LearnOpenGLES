package com.alick.learnopengles.utils

import androidx.annotation.RawRes
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * @createTime 2022/6/10 13:11
 * @author 崔兴旺  1607009565@qq.com
 * @description
 */
class RawUtils {
    companion object {
        fun getRawContent(@RawRes rawResId: Int): String {
            val sb = StringBuilder()
            var inputStream: InputStream? = null
            var inputStreamReader: InputStreamReader? = null
            try {
                inputStream = AppHolder.getApp().resources.openRawResource(rawResId)
                inputStreamReader = InputStreamReader(inputStream, Charsets.UTF_8)
                val br = BufferedReader(inputStreamReader)
                var str: String?
                while (br.readLine().also { str = it } != null) {
                    sb.append(str).append("\n")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                inputStream?.close()
                inputStreamReader?.close()
            }
            return sb.toString()
        }
    }
}