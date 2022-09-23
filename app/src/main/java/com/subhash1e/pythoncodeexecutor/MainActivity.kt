package com.subhash1e.pythoncodeexecutor

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    lateinit var btnRun: Button
    lateinit var etCode:EditText
    lateinit var tvResult: TextView
    lateinit var tvReset: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        val py = Python.getInstance()
        val module = py.getModule("main_")
        btnRun.setOnClickListener {
            try {
                createFile(it)
                val bytes = module.callAttr("x_x"
//                                            findViewById<EditText>(R.id.etX).text.toString(),
//                                            findViewById<EditText>(R.id.etY).text.toString()
                )
//                    .toJava(ByteArray::class.java)
                //print(bytes)
                tvResult.text = bytes.toString()

//                Toast.makeText(this, bytes.toString(), Toast.LENGTH_LONG).show()

            } catch (e: PyException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
        tvReset.setOnClickListener {
            etCode.setText("")
            tvResult.text = ""
        }
    }

    private fun initViews() {
        btnRun = findViewById(R.id.button)
        etCode = findViewById(R.id.textView)
        tvResult = findViewById(R.id.textView2)
        tvReset = findViewById(R.id.textView3)
        tvResult.setMovementMethod(ScrollingMovementMethod())
    }

    private fun createFile(view: View) {
        val data: String = this.etCode.getText().toString()
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput("main_2.py", MODE_PRIVATE)
            fos.write(data.toByteArray(StandardCharsets.UTF_8))
            fos.flush()
//            mStatus.setText("file saved")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}