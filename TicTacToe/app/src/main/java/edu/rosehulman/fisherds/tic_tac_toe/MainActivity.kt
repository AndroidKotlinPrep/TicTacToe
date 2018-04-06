package edu.rosehulman.fisherds.tic_tac_toe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mGame: TicTacToeGame = TicTacToeGame(this);
    private var mButtons: Array<Button?> = arrayOfNulls(9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        new_game_button.setOnClickListener {
            mGame = TicTacToeGame(this)
            updateView()
        }
        mButtons[0] = findViewById(R.id.button0)
        mButtons[1] = findViewById(R.id.button1)
        mButtons[2] = findViewById(R.id.button2)
        mButtons[3] = findViewById(R.id.button3)
        mButtons[4] = findViewById(R.id.button4)
        mButtons[5] = findViewById(R.id.button5)
        mButtons[6] = findViewById(R.id.button6)
        mButtons[7] = findViewById(R.id.button7)
        mButtons[8] = findViewById(R.id.button8)

    }

    private fun updateView() {
        game_state_text_view.text = mGame.stringForGameState()

        for (i in 0 until TicTacToeGame.NUM_SQUARES) {
            mButtons[i]?.text = mGame.stringForButtonAtIndex(i)
        }
    }

    fun pressedSquare(view: View) {
//        Log.d(Constants.TAG, "You pressed button " + view.tag.toString())

        mGame.pressedButtonAtIndex(Integer.valueOf(view.tag as String))
        updateView()
    }
}
