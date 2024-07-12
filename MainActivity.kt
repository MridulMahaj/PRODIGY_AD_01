package com.example.project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonDiv: Button
    private lateinit var editTextN1: EditText
    private lateinit var editTextN2: EditText
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        buttonAdd = findViewById(R.id.btn_add)
        buttonSub = findViewById(R.id.btn_sub)
        buttonMul = findViewById(R.id.btn_mul)
        buttonDiv = findViewById(R.id.btn_div)
        editTextN1 = findViewById(R.id.number1)
        editTextN2 = findViewById(R.id.number2)
        textView = findViewById(R.id.answer)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonAdd.setOnClickListener { performOperation(Operation.ADD) }
        buttonSub.setOnClickListener { performOperation(Operation.SUBTRACT) }
        buttonMul.setOnClickListener { performOperation(Operation.MULTIPLY) }
        buttonDiv.setOnClickListener { performOperation(Operation.DIVIDE) }
    }

    private fun performOperation(operation: Operation) {
        val number1 = editTextN1.text.toString().toDoubleOrNull()
        val number2 = editTextN2.text.toString().toDoubleOrNull()

        if (number1 == null || number2 == null) {
            textView.text = "Please enter valid numbers"
            return
        }

        val result = when (operation) {
            Operation.ADD -> number1 + number2
            Operation.SUBTRACT -> number1 - number2
            Operation.MULTIPLY -> number1 * number2
            Operation.DIVIDE -> if (number2 != 0.0) number1 / number2 else {
                textView.text = "Cannot divide by zero"
                return
            }
        }

        textView.text = result.toString()
    }

    private enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
