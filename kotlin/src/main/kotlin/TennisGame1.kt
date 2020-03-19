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

    override fun getScore(): String {
        var score = ""
        var tempScore = 0
        if (isDraw()) {
            return mapDrawMessage()
        } else if (isAdvantageOrGamePoint()) {
            return checkResult()
        } else {
            for (i in 1..2) {
                if (i == 1)
                    tempScore = m_score1
                else {
                    score += "-"
                    tempScore = m_score2
                }
                when (tempScore) {
                    0 -> score += "Love"
                    1 -> score += "Fifteen"
                    2 -> score += "Thirty"
                    3 -> score += "Forty"
                }
            }
        }
        return score
    }

    private fun checkResult(): String {
        val scoreDifference = m_score1 - m_score2
        return when(abs(m_score1 - m_score2)) {
            1 -> checkAdvantage(scoreDifference)
            else -> checkWinner(scoreDifference)
        }
    }

    private fun checkWinner(scoreDifference: Int): String =
        when {
            scoreDifference >= 2 -> "Win for player1"
            else -> "Win for player2"
        }

    private fun checkAdvantage(scoreDifference: Int): String =
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
