package xyz.nokt.btf.mathstutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CH_TAG:String = "MT_CHOICE"

    companion object{
        val RESULT_CODE:Int = 252
        val ADD:String = "xyz.nokt.btf.mathstutor.ADD"
        val SUB:String = "xyz.nokt.btf.mathstutor.SUB"
        val MUL:String = "xyz.nokt.btf.mathstutor.MUL"
        val DIV:String = "xyz.nokt.btf.mathstutor.DIV"
        val TIMED:String = "xyz.nokt.btf.mathstutor.TIMED"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun choiceClicked(view: View)
    {
        val btnClicked = view as Button

        var intent = Intent(this, MathActivity::class.java)

        when(btnClicked.id)
        {
            btnAdd.id->{
                Log.i(CH_TAG, "Addition")
                if(chTimed.isChecked)
                {
                    intent.putExtra(TIMED, "timed")
                }
                intent.putExtra(ADD,"add")
                setResult(RESULT_CODE, intent)
                startActivity(intent)
            }
            btnSub.id->{
                Log.i(CH_TAG, "Subtraction")
                intent.putExtra(SUB,"sub")
                setResult(RESULT_CODE, intent)
                startActivity(intent)
            }
            btnMult.id->{
                Log.i(CH_TAG, "Multiplication")
                intent.putExtra(MUL,"mul")
                setResult(RESULT_CODE, intent)
                startActivity(intent)
            }
            btnDiv.id->{
                Log.i(CH_TAG, "Division")
                intent.putExtra(DIV,"div")
                setResult(RESULT_CODE, intent)
                startActivity(intent)
            }
        }
    }
}
