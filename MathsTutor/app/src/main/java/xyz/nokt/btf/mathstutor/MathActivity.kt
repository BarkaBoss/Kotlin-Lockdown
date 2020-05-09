package xyz.nokt.btf.mathstutor

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_math.*
import java.util.*

class MathActivity : AppCompatActivity() {

    var miniNumber:Int = 0
    var maxiNumber:Int = 20
    var points:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)

        checkArithmeticChoice(miniNumber, maxiNumber)
    }

    private fun checkArithmeticChoice(sNumb:Int, lNumb:Int)
    {
        if (intent.hasExtra(MainActivity.ADD))
        {
            doAddition(sNumb, lNumb)
        }else if(intent.hasExtra(MainActivity.SUB)){
            doSubtraction(sNumb, lNumb)
            //Toast.makeText(this, intent.getStringExtra(MainActivity.SUB), Toast.LENGTH_SHORT).show()
        }else if(intent.hasExtra(MainActivity.MUL)){
            doMultiplication(sNumb, lNumb)
            //Toast.makeText(this, intent.getStringExtra(MainActivity.MUL), Toast.LENGTH_SHORT).show()
        }else if(intent.hasExtra(MainActivity.DIV)){
            doDivision(sNumb, lNumb)
            //Toast.makeText(this, intent.getStringExtra(MainActivity.DIV), Toast.LENGTH_SHORT).show()
        }
    }

    fun rangeSelected(view: View) {
        when {
            rbRange1.isChecked -> {
                miniNumber = 0
                maxiNumber = 20
                rbRange1.setBackgroundResource(R.drawable.numb_range_sel)
                rbRange4.setBackgroundResource(R.drawable.numb_range)
                rbRange2.setBackgroundResource(R.drawable.numb_range)
                rbRange3.setBackgroundResource(R.drawable.numb_range)
                rbRange5.setBackgroundResource(R.drawable.numb_range)
                checkArithmeticChoice(0, 20)
            }
            rbRange2.isChecked -> {
                miniNumber = 20
                maxiNumber = 40
                rbRange2.setBackgroundResource(R.drawable.numb_range_sel)
                rbRange1.setBackgroundResource(R.drawable.numb_range)
                rbRange4.setBackgroundResource(R.drawable.numb_range)
                rbRange3.setBackgroundResource(R.drawable.numb_range)
                rbRange5.setBackgroundResource(R.drawable.numb_range)
                checkArithmeticChoice(20, 40)
            }
            rbRange3.isChecked -> {
                miniNumber = 40
                maxiNumber = 60
                rbRange3.setBackgroundResource(R.drawable.numb_range_sel)
                rbRange1.setBackgroundResource(R.drawable.numb_range)
                rbRange2.setBackgroundResource(R.drawable.numb_range)
                rbRange4.setBackgroundResource(R.drawable.numb_range)
                rbRange5.setBackgroundResource(R.drawable.numb_range)
                checkArithmeticChoice(40, 60)
            }
            rbRange4.isChecked -> {
                miniNumber = 60
                maxiNumber = 80
                rbRange4.setBackgroundResource(R.drawable.numb_range_sel)
                rbRange1.setBackgroundResource(R.drawable.numb_range)
                rbRange2.setBackgroundResource(R.drawable.numb_range)
                rbRange3.setBackgroundResource(R.drawable.numb_range)
                rbRange5.setBackgroundResource(R.drawable.numb_range)
                checkArithmeticChoice(60, 80)
            }
            rbRange5.isChecked -> {
                miniNumber = 80
                maxiNumber = 100
                rbRange5.setBackgroundResource(R.drawable.numb_range_sel)
                rbRange1.setBackgroundResource(R.drawable.numb_range)
                rbRange2.setBackgroundResource(R.drawable.numb_range)
                rbRange3.setBackgroundResource(R.drawable.numb_range)
                rbRange4.setBackgroundResource(R.drawable.numb_range)
                checkArithmeticChoice(80, 100)
            }
        }
    }

    private fun doDivision(minNumber:Int, maxNumber:Int)
    {
        var num2:Int = getRandomNumbRange(minNumber, maxNumber)
        var num1:Int = getRandomNumbRange(num2, maxNumber)
        var ans = num1 / num2

        tvSymbol.text = "/  "
        tvParamOne.text = num1.toString()
        tvParamTwo.text = num2.toString()

        var rnd = Random()
        var answerIndex = getRandomNumbRange(0,3)
        if (answerIndex == 0){
            opOne.text = ans.toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }else if (answerIndex == 1)
        {
            opTwo.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }else if (answerIndex == 2)
        {
            opThree.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }else if (answerIndex == 3)
        {
            opFour.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }
    }

    private fun doMultiplication(minNumber:Int, maxNumber:Int)
    {
        var num2:Int = getRandomNumbRange(minNumber, maxNumber)
        var num1:Int = getRandomNumbRange(num2, maxNumber)
        var ans = num1 * num2

        tvSymbol.text = "x"
        tvParamOne.text = num1.toString()
        tvParamTwo.text = num2.toString()

        var rnd = Random()
        var answerIndex = getRandomNumbRange(0,3)
        if (answerIndex == 0){
            opOne.text = ans.toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
        }else if (answerIndex == 1)
        {
            opTwo.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
        }else if (answerIndex == 2)
        {
            opThree.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
        }else if (answerIndex == 3)
        {
            opFour.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber*maxNumber, ans).toString()
        }
    }

    private fun doSubtraction(minNumber:Int, maxNumber:Int)
    {
        var num2:Int = getRandomNumbRange(minNumber, maxNumber)
        var num1:Int = getRandomNumbRange(num2, maxNumber)
        var ans = num1 - num2

        tvSymbol.text = "-"
        tvParamOne.text = num1.toString()
        tvParamTwo.text = num2.toString()

        var rnd = Random()
        var answerIndex = getRandomNumbRange(0,3)
        if (answerIndex == 0){
            opOne.text = ans.toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }else if (answerIndex == 1)
        {
            opTwo.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }else if (answerIndex == 2)
        {
            opThree.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }else if (answerIndex == 3)
        {
            opFour.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber, ans).toString()
        }
    }

    private fun doAddition(minNumber:Int, maxNumber:Int)
    {
        var num2:Int = getRandomNumbRange(minNumber, maxNumber)
        var num1:Int = getRandomNumbRange(num2, maxNumber+1)
        var ans = num1 + num2

        tvParamOne.text = num1.toString()
        tvParamTwo.text = num2.toString()

        var rnd = Random()
        var answerIndex = getRandomNumbRange(0,3)
        if (answerIndex == 0){
            opOne.text = ans.toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
        }else if (answerIndex == 1)
        {
            opTwo.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
        }else if (answerIndex == 2)
        {
            opThree.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
        }else if (answerIndex == 3)
        {
            opFour.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,minNumber, maxNumber+maxNumber, ans).toString()
        }
    }

    private fun getRandomWithExclusion(rnd: Random, start: Int, end: Int, vararg exclude: Int): Int {
        var random = start + rnd.nextInt(end - start + 1 - exclude.size)
        for (ex in exclude) {
            if (random < ex) {
                break
            }
            random++
        }
        return random
    }

    private fun getRandomNumbRange(min:Int, max:Int):Int
    {
        if (min >= max)
        {
            //getRandomNumbRange(min, max)
            throw IllegalArgumentException("Max must be greater than Min")
        }

        var rand = Random()
        return rand.nextInt((max-min)+1) +min
    }

    fun clickedOp(view: View)
    {
        var btnClicked = view as TextView
        var ans:Int
        if (intent.hasExtra(MainActivity.ADD)) {
            when (btnClicked.id) {
                opOne.id -> {
                    ans = tvParamOne.text.toString().toInt() + tvParamTwo.text.toString().toInt()
                    if (opOne.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString() +" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doAddition(miniNumber, maxiNumber)
                }
                opTwo.id -> {
                    ans = tvParamOne.text.toString().toInt() + tvParamTwo.text.toString().toInt()
                    if (opTwo.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doAddition(miniNumber, maxiNumber)
                }
                opThree.id -> {
                    ans = tvParamOne.text.toString().toInt() + tvParamTwo.text.toString().toInt()
                    if (opThree.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doAddition(miniNumber, maxiNumber)
                }
                opFour.id -> {
                    ans = tvParamOne.text.toString().toInt() + tvParamTwo.text.toString().toInt()
                    if (opFour.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }

                    doAddition(miniNumber, maxiNumber)
                }
            }
        }

        if (intent.hasExtra(MainActivity.SUB)) {
            when (btnClicked.id) {
                opOne.id -> {
                    ans = tvParamOne.text.toString().toInt() - tvParamTwo.text.toString().toInt()
                    if (opOne.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doSubtraction(miniNumber, maxiNumber)
                }
                opTwo.id -> {
                    ans = tvParamOne.text.toString().toInt() - tvParamTwo.text.toString().toInt()
                    if (opTwo.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doSubtraction(miniNumber, maxiNumber)
                }
                opThree.id -> {
                    ans = tvParamOne.text.toString().toInt() - tvParamTwo.text.toString().toInt()
                    if (opThree.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doSubtraction(miniNumber, maxiNumber)
                }
                opFour.id -> {
                    ans = tvParamOne.text.toString().toInt() - tvParamTwo.text.toString().toInt()
                    if (opFour.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doSubtraction(miniNumber, maxiNumber)
                }
            }
        }
        if (intent.hasExtra(MainActivity.MUL)) {
            when (btnClicked.id) {
                opOne.id -> {
                    ans = tvParamOne.text.toString().toInt() * tvParamTwo.text.toString().toInt()
                    if (opOne.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doMultiplication(miniNumber, maxiNumber)
                }
                opTwo.id -> {
                    ans = tvParamOne.text.toString().toInt() * tvParamTwo.text.toString().toInt()
                    if (opTwo.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doMultiplication(miniNumber, maxiNumber)
                }
                opThree.id -> {
                    ans = tvParamOne.text.toString().toInt() * tvParamTwo.text.toString().toInt()
                    if (opThree.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doMultiplication(miniNumber, maxiNumber)
                }
                opFour.id -> {
                    ans = tvParamOne.text.toString().toInt() * tvParamTwo.text.toString().toInt()
                    if (opFour.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doMultiplication(miniNumber, maxiNumber)
                }
            }
        }
        if (intent.hasExtra(MainActivity.DIV)) {
            when (btnClicked.id) {
                opOne.id -> {
                    ans = tvParamOne.text.toString().toInt() / tvParamTwo.text.toString().toInt()
                    if (opOne.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doDivision(miniNumber, maxiNumber)
                }
                opTwo.id -> {
                    ans = tvParamOne.text.toString().toInt() / tvParamTwo.text.toString().toInt()
                    if (opTwo.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doDivision(miniNumber, maxiNumber)
                }
                opThree.id -> {
                    ans = tvParamOne.text.toString().toInt() / tvParamTwo.text.toString().toInt()
                    if (opThree.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doDivision(miniNumber, maxiNumber)
                }
                opFour.id -> {
                    ans = tvParamOne.text.toString().toInt() / tvParamTwo.text.toString().toInt()
                    if (opFour.text.toString().toInt() == ans) {
                        points += 1
                        tvPoints.text = points.toString()+" Points"
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                    doDivision(miniNumber, maxiNumber)
                }
            }
        }
    }
}
