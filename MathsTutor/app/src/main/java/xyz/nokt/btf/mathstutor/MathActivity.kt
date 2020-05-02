package xyz.nokt.btf.mathstutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)

        if (intent.hasExtra(MainActivity.ADD))
        {
            Toast.makeText(this, intent.getStringExtra(MainActivity.ADD), Toast.LENGTH_SHORT).show()
        }else if(intent.hasExtra(MainActivity.SUB)){
            Toast.makeText(this, intent.getStringExtra(MainActivity.SUB), Toast.LENGTH_SHORT).show()
        }else if(intent.hasExtra(MainActivity.MUL)){
            Toast.makeText(this, intent.getStringExtra(MainActivity.MUL), Toast.LENGTH_SHORT).show()
        }else if(intent.hasExtra(MainActivity.DIV)){
            Toast.makeText(this, intent.getStringExtra(MainActivity.DIV), Toast.LENGTH_SHORT).show()
        }

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
