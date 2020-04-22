package xyz.nokt.btf.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var op = "*"
    var oldNumb = ""
    var isNewOp = true
    fun mathOps(view: View){
        val btnOp = view as View

        when(btnOp.id)
        {
            btnAdd.id -> {
                op = "+"
            }
            btnSub.id -> {
                op = "-"
            }
            btnDiv.id -> {
                op = "/"
            }
            btnMul.id -> {
                op = "*"
            }
        }

        oldNumb = calField.text.toString()
        isNewOp = true
    }
    fun numbBtnClicked(view: View)
    {
        if(isNewOp)
        {
            calField.setText("")
        }
        isNewOp = false

        val btnClicked = view as Button
        var btnClickedVal:String=calField.text.toString()

        when(btnClicked.id)
        {
            btnZero.id -> {
                btnClickedVal += "0"
            }
            btn1.id -> {
                btnClickedVal += "1"
            }
            btn2.id -> {
                btnClickedVal += "2"
            }
            btn3.id -> {
                btnClickedVal += "3"
            }
            btn4.id -> {
                btnClickedVal += "4"
            }
            btn5.id -> {
                btnClickedVal += "5"
            }
            btn6.id -> {
                btnClickedVal += "6"
            }
            btn7.id -> {
                btnClickedVal += "7"
            }
            btn8.id -> {
                btnClickedVal += "8"
            }
            btn9.id -> {
                btnClickedVal += "9"
            }
            btnDot.id -> {
                btnClickedVal += "."
            }
            btnPlusMinus.id -> {
                btnClickedVal = "-"+btnClickedVal
            }
        }
        calField.setText(btnClickedVal)
    }

    fun resolveCalc(view: View)
    {
        val newNumber:String = calField.text.toString()
        var result: Double? = null

        when(op)
        {
            "*" -> {
                result = oldNumb.toDouble()*newNumber.toDouble()
            }
            "+" -> {
                result = oldNumb.toDouble()+newNumber.toDouble()
            }
            "/" -> {
                result = oldNumb.toDouble()/newNumber.toDouble()
            }
            "-" -> {
                result = oldNumb.toDouble()-newNumber.toDouble()
            }
        }

        calField.setText(result.toString())
        isNewOp = true
    }

    fun divByOne(view: View)
    {
        if (!calField.text.toString().equals("") || !calField.text.toString().equals("0"))
        {
            var result = 1/calField.text.toString().toDouble()
            calField.setText(result.toString())
        }
        isNewOp = true
    }

    fun squareNum(view: View)
    {
        if (!calField.text.toString().equals("") || !calField.text.toString().equals("0"))
        {
            var result = Math.sqrt(calField.text.toString().toDouble())
            calField.setText(result.toString())
        }
        isNewOp = true
    }

    fun squareRoot(view: View)
    {
        if (!calField.text.toString().equals("") || !calField.text.toString().equals("0"))
        {

            var result = calField.text.toString().toDouble()*calField.text.toString().toDouble()
            calField.setText(result.toString())
        }
        isNewOp = true
    }
}
