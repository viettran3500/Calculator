package com.viet.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.viet.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var inputText = ""
    private var numStartBracket = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAc.setOnClickListener {
            binding.textViewInput.text = ""
            binding.textViewOutput.text = ""
            inputText = ""
            numStartBracket = 0
        }

        binding.btn0.setOnClickListener {
            binding.textViewInput.append("0")
            inputText += "0"
        }
        binding.btn1.setOnClickListener {
            binding.textViewInput.append("1")
            inputText += "1"
        }
        binding.btn2.setOnClickListener {
            binding.textViewInput.append("2")
            inputText += "2"
        }
        binding.btn3.setOnClickListener {
            binding.textViewInput.append("3")
            inputText += "3"
        }
        binding.btn4.setOnClickListener {
            binding.textViewInput.append("4")
            inputText += "4"
        }
        binding.btn5.setOnClickListener {
            binding.textViewInput.append("5")
            inputText += "5"
        }
        binding.btn6.setOnClickListener {
            binding.textViewInput.append("6")
            inputText += "6"
        }
        binding.btn7.setOnClickListener {
            binding.textViewInput.append("7")
            inputText += "7"
        }
        binding.btn8.setOnClickListener {
            binding.textViewInput.append("8")
            inputText += "8"
        }
        binding.btn9.setOnClickListener {
            binding.textViewInput.append("9")
            inputText += "9"
        }
        binding.btnDot.setOnClickListener {
            val i = inputText.lastIndexOf(".")
            var text = ""
            if (i != -1) {
                text = inputText.substring(i + 1, inputText.length)
            }

            try {
                text.toLong()
            } catch (e: Exception) {
                binding.textViewInput.append(".")
                inputText += "."
            }
        }
        binding.btnPlus.setOnClickListener {
            if (!inputText.endsWith("+") &&
                    !inputText.endsWith("(") &&
                    inputText.isNotEmpty() &&
                    !inputText.endsWith(".") &&
                    !inputText.endsWith("-") &&
                    !inputText.endsWith("*") &&
                    !inputText.endsWith("/")) {
                binding.textViewInput.append("+")
                inputText += "+"
            }
        }
        binding.btnMinus.setOnClickListener {
            if (!inputText.endsWith(".") && !inputText.endsWith("-")) {
                binding.textViewInput.append("-")
                inputText += "-"
            }
        }
        binding.btnDivide.setOnClickListener {
            if (!inputText.endsWith("+") &&
                    !inputText.endsWith("(") &&
                    inputText.isNotEmpty() &&
                    !inputText.endsWith(".") &&
                    !inputText.endsWith("-") &&
                    !inputText.endsWith("*") &&
                    !inputText.endsWith("/")) {
                binding.textViewInput.append("/")
                inputText += "/"
            }
        }
        binding.btnMultiplication.setOnClickListener {
            if (!inputText.endsWith("+") &&
                    !inputText.endsWith("(") &&
                    inputText.isNotEmpty() &&
                    !inputText.endsWith(".") &&
                    !inputText.endsWith("-") &&
                    !inputText.endsWith("*") &&
                    !inputText.endsWith("/")) {
                binding.textViewInput.append("*")
                inputText += "*"
            }
        }
        binding.btnStartBracket.setOnClickListener {
            binding.textViewInput.append("(")
            inputText += "("
            numStartBracket++
        }
        binding.btnEndBracket.setOnClickListener {
            if (numStartBracket != 0) {
                if (!inputText.endsWith("(") &&
                        !inputText.endsWith("+") &&
                        !inputText.endsWith(".") &&
                        !inputText.endsWith("-") &&
                        !inputText.endsWith("*") &&
                        !inputText.endsWith("/")) {
                    binding.textViewInput.append(")")
                    inputText += ")"
                    numStartBracket--
                }
            }
        }

        binding.btnEqual.setOnClickListener {

            for (i in 0 until numStartBracket) {
                binding.textViewInput.append(")")
                inputText += ")"
            }

            if (inputText.endsWith(".")) {
                binding.textViewInput.append("0")
                inputText += "0"
            }

            if (binding.textViewInput.text.isEmpty()) {
                binding.textViewInput.text = "0"
                inputText = "0"
            }

            val expression = ExpressionBuilder(binding.textViewInput.text.toString()).build()
            try {
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    binding.textViewOutput.text = longResult.toString()
                } else {
                    binding.textViewOutput.text = result.toString()
                }
            } catch (e: Exception) {
                binding.textViewOutput.text = "Division by zero!"
            }

        }

        binding.btnDelete.setOnClickListener {
            if (inputText.isNotEmpty()) {
                if (inputText.endsWith(")"))
                    numStartBracket++
                if (inputText.endsWith("("))
                    numStartBracket--
                inputText = inputText.substring(0, inputText.length - 1)
                binding.textViewInput.text = inputText
            }

        }
    }
}