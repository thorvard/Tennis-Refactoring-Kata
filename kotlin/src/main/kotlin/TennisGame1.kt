import kotlin.math.abs

class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var m_score1: Int = 0
    private var m_score2: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            m_score1 += 1
        else
            m_score2 += 1
    }

    override fun getScore() = when {
        isDraw() -> mapDrawMessage()
        isAdvantageOrGamePoint() -> mapScoreToWinnerOrAdvantage()
        else -> mapCurrentGameScore()
    }

    private fun mapCurrentGameScore(): String {
        var tempScore1: Int
        var score1 = ""
        for (i in 1..2) {
            if (i == 1)
                tempScore1 = m_score1
            else {
                score1 += "-"
                tempScore1 = m_score2
            }
            score1 += mapPlayerScore(tempScore1)
        }
        return score1
    }

    private fun mapPlayerScore(playerScore: Int): String =
        when (playerScore) {
            0 -> "Love"
            1 -> "Fifteen"
            2 -> "Thirty"
            else -> "Forty"
        }

    private fun mapScoreToWinnerOrAdvantage(): String {
        val scoreDifference = m_score1 - m_score2
        return when (abs(m_score1 - m_score2)) {
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

    private fun isAdvantageOrGamePoint() = m_score1 >= 4 || m_score2 >= 4

    private fun isDraw() = m_score1 == m_score2

    private fun mapDrawMessage(): String {
        return when (m_score1) {
            0 -> "Love-All"
            1 -> "Fifteen-All"
            2 -> "Thirty-All"
            else -> "Deuce"
        }
    }
}
