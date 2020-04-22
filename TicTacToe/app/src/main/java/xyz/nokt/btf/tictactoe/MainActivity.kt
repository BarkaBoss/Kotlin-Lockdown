package xyz.nokt.btf.tictactoe

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClicked(view: View) {
        val btnSelected = view as Button
        var cellID = 0

        when(btnSelected.id)
        {
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }
        playGame(cellID, btnSelected)
    }

    var playerOne = ArrayList<Int>()
    var playerTwo = ArrayList<Int>()

    var  activePlayer = 1

    fun playGame(cellID:Int, btnSelected:Button)
    {
        if (activePlayer == 1)
        {
            btnSelected.setText("X")
            btnSelected.setBackgroundResource(R.drawable.player_one)
            btnSelected.setTextColor(Color.WHITE)
            //btnSelected.setBackgroundColor(Color.MAGENTA)
            playerOne.add(cellID)

            activePlayer = 2
        }else{
            btnSelected.setText("O")
            btnSelected.setTextColor(Color.WHITE)
            btnSelected.setBackgroundResource(R.drawable.player_two)
            playerTwo.add(cellID)

            activePlayer = 1
        }

        Log.i("CellID", cellID.toString())
        btnSelected.isEnabled = false
        checkWinner()
    }

    fun checkWinner()
    {
        Log.i("checkWinner", "Method Called")
        var winner = -1

        //row1
        if (playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)){
            winner = 1
        }
        if (playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3)){
            winner = 2
        }

        //row2
        if (playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)){
            winner = 1
        }
        if (playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6)){
            winner = 2
        }

        //row3
        if (playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)){
            winner = 1
        }
        if (playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9)){
            winner = 2
        }

        //col1
        if (playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)){
            winner = 1
        }
        if (playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7)){
            winner = 2
        }

        //col2
        if (playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)){
            winner = 1
        }
        if (playerTwo.contains(2) && playerTwo.contains(5) && playerTwo.contains(8)){
            winner = 2
        }

        //col3
        if (playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)){
            winner = 1
        }
        if (playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9)){
            winner = 2
        }

        //diag1
        if (playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9)){
            winner = 1
        }
        if (playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9)){
            winner = 2
        }

        //diag1
        if (playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7)){
            winner = 1
        }
        if (playerTwo.contains(3) && playerTwo.contains(5) && playerTwo.contains(7)){
            winner = 2
        }

        if (winner != -1)
        {
            if (winner == 1)
            {
                Toast.makeText(this, "Player 1 Wins", Toast.LENGTH_LONG).show()
                finish()
                startActivity(intent)
            }else{
                Toast.makeText(this, "Player 2 Wins", Toast.LENGTH_LONG).show()
                finish()
                startActivity(intent)
            }
        }
    }
}
