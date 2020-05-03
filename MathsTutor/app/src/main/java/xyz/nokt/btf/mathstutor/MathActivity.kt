package xyz.nokt.btf.mathstutor

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_math.*
import java.util.*

class MathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)


        if (intent.hasExtra(MainActivity.ADD))
        {
            doAddition()
        }else if(intent.hasExtra(MainActivity.SUB)){
            Toast.makeText(this, intent.getStringExtra(MainActivity.SUB), Toast.LENGTH_SHORT).show()
        }else if(intent.hasExtra(MainActivity.MUL)){
            Toast.makeText(this, intent.getStringExtra(MainActivity.MUL), Toast.LENGTH_SHORT).show()
        }else if(intent.hasExtra(MainActivity.DIV)){
            Toast.makeText(this, intent.getStringExtra(MainActivity.DIV), Toast.LENGTH_SHORT).show()
        }

    }

    private fun doAddition()
    {
        var num1:Int = getRandomNumbRange(0, 50)
        var num2:Int = getRandomNumbRange(0, 50)
        var ans = num1 + num2
        var start = 0
        var end = 100

        tvParamOne.text = num1.toString()
        tvParamTwo.text = num2.toString()

        var rnd = Random()
        var answerIndex = getRandomNumbRange(0,3)
        if (answerIndex == 0){
            opOne.text = ans.toString()
            opTwo.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,start, end, ans).toString()
        }else if (answerIndex == 1)
        {
            opTwo.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,start, end, ans).toString()
        }else if (answerIndex == 2)
        {
            opThree.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opFour.text = getRandomWithExclusion(rnd,start, end, ans).toString()
        }else if (answerIndex == 3)
        {
            opFour.text = ans.toString()
            opOne.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opTwo.text = getRandomWithExclusion(rnd,start, end, ans).toString()
            opThree.text = getRandomWithExclusion(rnd,start, end, ans).toString()
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
            throw IllegalArgumentException("Max must be greater than Min")
        }

        var rand = Random()
        return rand.nextInt((max-min)+1) +min
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == MainActivity.RESULT_CODE)
        {
            if (data!!.getStringExtra(MainActivity.ADD) != null)
            {
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
            }else if (data!!.getStringExtra(MainActivity.SUB) != null)
            {
                Toast.makeText(this, "Sub", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
