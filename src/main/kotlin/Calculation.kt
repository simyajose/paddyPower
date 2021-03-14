import models.GameResult
import kotlin.math.roundToInt

data class WinMarginResult(val home: Double, val away: Double)

object  Calculation {

    private const val OFFSET = 0.5

    fun calcProbability(results: List<GameResult>): Double {
        val homeTeamCnt = results.count { it.homeTeamWinner }
        val homeTeamProb = homeTeamCnt/results.size.toDouble()
        return round(homeTeamProb)
    }

    private fun round(prob: Double) = (prob * 100.0).roundToInt() / 100.0

    private fun calcMedian(results: List<GameResult>): Double {
        val totalPoints = results
            .map { it.homeTeamPoints + it.awayTeamPoints }
            .sorted()

        val cnt = results.size
        return if (cnt % 2 == 0) {
             round((totalPoints[(cnt / 2) - 1] + totalPoints[(cnt / 2) ]) / 2.0 )
        } else
            totalPoints[cnt/2].toDouble()
    }

    fun halfPointsProbability(results: List<GameResult>): Double {

        val median = calcMedian(results) - OFFSET
        val cnt = results.size.toDouble()
        val overLineCnt = results.count { (it.homeTeamPoints + it.awayTeamPoints) > median }
        return round(overLineCnt/cnt)
    }

    fun winMarginProbability(results: List<GameResult>): WinMarginResult {

        val homeWinners = results.filter { it.homeTeamWinner}
        val awayWinners = results.filter { it.awayTeamWinner}

        val homeUpto10Pts = homeWinners.count { (it.homeTeamPoints - it.awayTeamPoints) <= 10 }
        val awayUpto10Pts = awayWinners.count { (it.awayTeamPoints - it.homeTeamPoints) <= 10 }

        val homeUpto10PtsProb = round( homeUpto10Pts / homeWinners.size.toDouble())
        val awayUpto10PtsProb = round( awayUpto10Pts / awayWinners.size.toDouble())

        return WinMarginResult(homeUpto10PtsProb, awayUpto10PtsProb)
    }
}