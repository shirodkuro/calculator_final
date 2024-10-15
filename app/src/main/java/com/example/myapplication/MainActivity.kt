package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var regexTextView: TextView // Thêm biến này để hiển thị quá trình tính toán
    private var currentInput = ""
    private var operator = ""
    private var operand1 = 0
    private var operand2 = 0
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonDiv: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khai báo các phần tử UI
        resultTextView = findViewById(R.id.resultText)
        regexTextView = findViewById(R.id.regexText) // Kết nối với TextView regexText trong layout

        val button0: Button = findViewById(R.id.button24)
        val button1: Button = findViewById(R.id.button21)
        val button2: Button = findViewById(R.id.button20)
        val button3: Button = findViewById(R.id.button18)
        val button4: Button = findViewById(R.id.button14)
        val button5: Button = findViewById(R.id.button15)
        val button6: Button = findViewById(R.id.button16)
        val button7: Button = findViewById(R.id.button10)
        val button8: Button = findViewById(R.id.button11)
        val button9: Button = findViewById(R.id.button12)
        buttonAdd = findViewById(R.id.button19)
        buttonSub = findViewById(R.id.button17)
        buttonMul = findViewById(R.id.button13)
        buttonDiv = findViewById(R.id.button6)
        val buttonEquals: Button = findViewById(R.id.button23)
        val buttonClear: Button = findViewById(R.id.button9)

        // Xử lý sự kiện khi bấm các số
        button0.setOnClickListener { appendNumber(0) }
        button1.setOnClickListener { appendNumber(1) }
        button2.setOnClickListener { appendNumber(2) }
        button3.setOnClickListener { appendNumber(3) }
        button4.setOnClickListener { appendNumber(4) }
        button5.setOnClickListener { appendNumber(5) }
        button6.setOnClickListener { appendNumber(6) }
        button7.setOnClickListener { appendNumber(7) }
        button8.setOnClickListener { appendNumber(8) }
        button9.setOnClickListener { appendNumber(9) }

        // Xử lý sự kiện khi bấm các phép tính
        buttonAdd.setOnClickListener { setOperator("+", buttonAdd) }
        buttonSub.setOnClickListener { setOperator("-", buttonSub) }
        buttonMul.setOnClickListener { setOperator("*", buttonMul) }
        buttonDiv.setOnClickListener { setOperator("/", buttonDiv) }

        // Xử lý sự kiện khi bấm nút bằng
        buttonEquals.setOnClickListener { calculate() }

        // Xử lý sự kiện khi bấm nút xóa
        buttonClear.setOnClickListener { clear() }
    }

    // Thêm số vào chuỗi hiện tại
    private fun appendNumber(number: Int) {
        currentInput += number.toString()
        resultTextView.text = currentInput
    }

    // Chọn phép toán và highlight nút tương ứng
    private fun setOperator(op: String, selectedButton: Button) {
        operator = op
        operand1 = currentInput.toInt()
        currentInput = ""

        // Hiển thị phép toán đã chọn trong regexTextView
        regexTextView.text = "$operand1 $operator"

        // Highlight phép tính được chọn
        highlightSelectedOperator(selectedButton)
    }

    // Thực hiện phép tính
    private fun calculate() {
        operand2 = currentInput.toInt()
        var result = 0
        when (operator) {
            "+" -> result = operand1 + operand2
            "-" -> result = operand1 - operand2
            "*" -> result = operand1 * operand2
            "/" -> if (operand2 != 0) result = operand1 / operand2
        }
        resultTextView.text = result.toString()
        currentInput = result.toString()

        // Hiển thị quá trình tính toán trong regexTextView
        regexTextView.text = "$operand1 $operator $operand2 ="

        // Sau khi tính toán, reset lại màu các nút
        resetOperatorHighlight()
    }

    // Xóa màn hình
    private fun clear() {
        currentInput = ""
        operator = ""
        operand1 = 0
        operand2 = 0
        resultTextView.text = "0"
        regexTextView.text = "" // Xóa nội dung regexTextView

        // Reset màu các nút phép toán
        resetOperatorHighlight()
    }

    // Highlight nút phép toán được chọn
    private fun highlightSelectedOperator(selectedButton: Button) {
        resetOperatorHighlight() // Đầu tiên, reset tất cả các nút
        selectedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200)) // Màu "highlight"
    }

    // Reset màu nền các nút phép toán
    private fun resetOperatorHighlight() {
        // Trả về màu mặc định
        buttonAdd.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button))
        buttonSub.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button))
        buttonMul.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button))
        buttonDiv.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button))
    }
}
