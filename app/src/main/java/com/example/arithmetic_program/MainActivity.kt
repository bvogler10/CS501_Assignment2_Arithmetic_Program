package com.example.arithmetic_program

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private fun isNumeric(input: String): Boolean {
        return try {
            input.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val makeCalculation: Button = findViewById(R.id.calculate)

        makeCalculation.setOnClickListener {
            Log.d("MainActivity", "Button clicked")
            val operationType: RadioGroup = findViewById(R.id.operation)

            val inputOne: EditText = findViewById(R.id.input1)
            val inputTwo: EditText = findViewById(R.id.input2)

            val selectedOptionId = operationType.checkedRadioButtonId
            val selectionRadio: RadioButton = findViewById(selectedOptionId)
            if (operationType.checkedRadioButtonId == View.NO_ID) {
                Toast.makeText(this, "Please select an operation!", Toast.LENGTH_SHORT).show()
            } else if (inputOne.text.isNullOrBlank() || inputTwo.text.isNullOrBlank()) {
                Toast.makeText(this, "Please enter 2 inputs!", Toast.LENGTH_SHORT).show()
            } else if (!isNumeric(inputOne.text.toString()) || !isNumeric(inputTwo.text.toString())) {
                Toast.makeText(this, "Please enter 2 numeric inputs!", Toast.LENGTH_SHORT).show()
            } else if (inputTwo.text.toString().toDouble() == 0.0 && selectionRadio.text.toString() == "/"){
                Toast.makeText(this, "Division by zero is undefined!", Toast.LENGTH_SHORT).show()
            } else {
                val value1 = inputOne.text.toString().toDouble()
                val value2 = inputTwo.text.toString().toDouble()
                val result: TextView = findViewById(R.id.textView4)

                when (selectionRadio.text.toString()) {
                    "+" -> result.text = (value1 + value2).toString()
                    "-" -> result.text = (value1 - value2).toString()
                    "x" -> result.text = (value1 * value2).toString()
                    "/" -> result.text = (value1 / value2).toString()
                    "%" -> result.text = (value1 % value2).toString()
                }
                Log.d("MainActivity", result.text.toString())
            }
        }
    }
}