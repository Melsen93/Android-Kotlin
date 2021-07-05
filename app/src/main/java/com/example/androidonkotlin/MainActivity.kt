package com.example.androidonkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<TextView>(R.id.button)

        button.setOnClickListener {
            if (textView.text == getString(R.string.text_view_text)) {
                textView.text = getString(R.string.text_view_kotlin_text)
                forEachCycle()
            } else {
                textView.setText(R.string.text_view_text)
                closedRangeCycle()
            }
        }

        val testDataClass = TestDataClass("TestDataClass", 2021)
        val testObject = testDataClass.copy(string = "TestDataClassCopy", number = 3021)

        textView.setOnLongClickListener {
            if (textView.text != "${testDataClass.string} ${testDataClass.number}") {
                textView.text = "${testDataClass.string} ${testDataClass.number}"
                closedRangeCycleReverse()
            } else {
                textView.text = "${testObject.string} ${testObject.number}"
                closedRangeCycleReverseWithStep()
            }
            true
        }
    }


    private fun forEachCycle() {
        val arrayList = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        for (i in arrayList) {
            Log.d("forEachCycle", i.toString())
        }
    }

    private fun closedRangeCycle() {
        for (i in 1..50) {
            Log.d("closedRangeCycle", i.toString())
        }
    }

    private fun closedRangeCycleReverse() {
        for (i in 100 downTo 50) {
            Log.d("closedRangeCycleReverse", i.toString())
        }
    }

    private fun closedRangeCycleReverseWithStep() {
        for (i in 50 downTo 0 step 5) {
            Log.d("closedRangeCycleReverseWithStep", i.toString())
        }
    }
}