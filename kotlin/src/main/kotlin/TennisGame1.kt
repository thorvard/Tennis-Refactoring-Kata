import kotlin.math.abs

class TennisGame1 : TennisGame {

    private var scorePlayerOne: Int = 0
    private var scorePlayerTwo: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            scorePlayerOne += 1
        else
            scorePlayerTwo += 1
    }

    override fun getScore() = when {
        isDraw() -> mapDrawMessage()
        isAdvantageOrGamePoint() -> mapScoreToWinnerOrAdvantage()
        else -> mapCurrentGameScore()
    }

    private fun mapCurrentGameScore() = mapPlayerScore(scorePlayerOne) + "-" + mapPlayerScore(scorePlayerTwo)

    private fun mapPlayerScore(playerScore: Int): String =
        when (playerScore) {
            0 -> "Love"
            1 -> "Fifteen"
            2 -> "Thirty"
            else -> "Forty"
        }

    private fun mapScoreToWinnerOrAdvantage(): String {
        val scoreDifference = scorePlayerOne - scorePlayerTwo
        return when (abs(scorePlayerOne - scorePlayerTwo)) {
            1 -> mapScoreToAdvantage(scoreDifference)
            else -> mapScoreToWinner(scoreDifference)
        }
    }

    private fun mapScoreToWinner(scoreDifference: Int): String =
        when {
            scoreDifference >= 2 -> "Win for player1"
            else -> "Win for player2"
        }

    private fun mapScoreToAdvantage(scoreDifference: Int): String =
        when (scoreDifference) {
            1 -> "Advantage player1"
            else -> "Advantage player2"
        }

    private fun isAdvantageOrGamePoint() = scorePlayerOne >= 4 || scorePlayerTwo >= 4

    private fun isDraw() = scorePlayerOne == scorePlayerTwo

    private fun mapDrawMessage(): String {
        return when (scorePlayerOne) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
    }
}
