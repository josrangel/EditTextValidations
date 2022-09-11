package com.josrangel.edittextvalidations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var editText1: EditText
    lateinit var btnEvaluate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    fun initUI() {
        editText1 = findViewById(R.id.editText_1)
        btnEvaluate = findViewById(R.id.btn_evaluate)
        btnEvaluate.setOnClickListener({ evaluateEditText() })

        editText1.filters = arrayOf<InputFilter>(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isDigit(source[i])) { // Accept only letter & digits ; otherwise just return
                    editText1.setError("only numbers")
                    return@InputFilter ""
                }
            }
            null
        })
    }

    fun evaluateEditText() {
        val editTextValue = editText1.text.toString()
        if (TextUtils.isEmpty(editTextValue)) {
            editText1.error = getString(R.string.error_empty)
        } else {
            editText1.error = null
        }
    }
}