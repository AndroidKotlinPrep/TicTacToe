package edu.rosehulman.fisherds.tic_tac_toe

import android.content.Context

class TicTacToeGame(private val mContext: Context) {

    private var mGameState: GameState? = null
    private val mBoard: Array<MarkType>

    private enum class MarkType {
        NONE,
        X,
        O
    }

    private enum class GameState {
        X_TURN,
        O_TURN,
        X_WIN,
        O_WIN,
        TIE_GAME
    }


    init {
        mBoard = Array(NUM_SQUARES, { MarkType.NONE })
        mGameState = GameState.X_TURN
    }

    fun pressedButtonAtIndex(buttonIndex: Int) {
        if (buttonIndex < 0 || buttonIndex >= NUM_SQUARES) {
            return    // Not a valid square location
        }
        if (mBoard[buttonIndex] != MarkType.NONE) {
            return    // Not empty
        }
        if (mGameState == GameState.X_TURN) {
            mBoard[buttonIndex] = MarkType.X
            mGameState = GameState.O_TURN
        } else if (mGameState == GameState.O_TURN) {
            mBoard[buttonIndex] = MarkType.O
            mGameState = GameState.X_TURN
        }
        checkForGameOver()
    }

    private fun checkForGameOver() {
        if (!(mGameState == GameState.X_TURN || mGameState == GameState.O_TURN)) {
            return   // The game is already over.
        }
        if (!mBoard.contains(MarkType.NONE)) {
            mGameState = GameState.TIE_GAME
        }
        val linesOf3 = arrayOfNulls<String>(8)
        linesOf3[0] = getMarkString(intArrayOf(0, 1, 2))
        linesOf3[1] = getMarkString(intArrayOf(3, 4, 5))
        linesOf3[2] = getMarkString(intArrayOf(6, 7, 8))
        linesOf3[3] = getMarkString(intArrayOf(0, 3, 6))
        linesOf3[4] = getMarkString(intArrayOf(1, 4, 7))
        linesOf3[5] = getMarkString(intArrayOf(2, 5, 8))
        linesOf3[6] = getMarkString(intArrayOf(0, 4, 8))
        linesOf3[7] = getMarkString(intArrayOf(2, 4, 6))
        for (lineOf3 in linesOf3) {
            if (lineOf3 == "XXX") {
                mGameState = GameState.X_WIN
            } else if (lineOf3 == "OOO") {
                mGameState = GameState.O_WIN
            }
        }
    }

    private fun getMarkString(indices: IntArray): String {
        var markString = ""
        for (index in indices) {
            markString += mBoard[index].name
        }
        return markString
    }

    fun stringForButtonAtIndex(buttonIndex: Int): String {
        if (buttonIndex < 0 || buttonIndex >= NUM_SQUARES) {
            return ""   // Not a valid square location
        }
        return if (mBoard[buttonIndex] == MarkType.NONE) {
            ""
        } else mBoard[buttonIndex].name
    }

    fun stringForGameState(): String {
        val r = mContext.resources
        return when (mGameState) {
            TicTacToeGame.GameState.X_TURN -> r.getString(R.string.x_turn)
            TicTacToeGame.GameState.O_TURN -> r.getString(R.string.o_turn)
            TicTacToeGame.GameState.X_WIN -> r.getString(R.string.x_win)
            TicTacToeGame.GameState.O_WIN -> r.getString(R.string.o_win)
            TicTacToeGame.GameState.TIE_GAME -> r.getString(R.string.tie_game)
            else -> "Error"
        }
    }

    companion object {
        const val NUM_SQUARES = 9
    }
}