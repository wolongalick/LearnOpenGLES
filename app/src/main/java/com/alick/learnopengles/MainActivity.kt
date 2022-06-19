package com.alick.learnopengles

import android.content.Intent
import android.opengl.GLES20
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnTriangle).setOnClickListener {
            startActivity(Intent(this, TriangleActivity::class.java))
        }
    }
}