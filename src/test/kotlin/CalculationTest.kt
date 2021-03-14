import models.GameResult
import org.junit.Test

import org.junit.Assert.*
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.test.BeforeTest

class CalculationTest {

    private val inp: List<GameResult> by lazy {
        listOf(
            GameResult(homeTeamWinner = true, awayTeamWinner = false, 100, 75),
            GameResult(homeTeamWinner = true, awayTeamWinner = false,  125, 75),
            GameResult(homeTeamWinner = true, awayTeamWinner = false,  75, 70),
            GameResult(homeTeamWinner = false, awayTeamWinner = true, 70, 75)
        )
    }

    @Test
    fun check_whether_winProb_is_calculated_correctly() {
        val res = Calculation.calcProbability(inp)
        assertEquals (res, 0.75, 0.00)
    }

    @Test
    fun check_whether_halfPointsProb_is_calculated_correctly() {
        val res = Calculation.halfPointsProbability(inp)
        assertEquals (res, 0.50, 0.00)
    }

    @Test
    fun check_whether_winMarginProb_is_calculated_correctly() {
        val res = Calculation.winMarginProbability(inp)
        assertEquals (res.home, 0.33, 0.00)
        assertEquals (res.away, 1.0, 0.00)
    }

    @Test
    fun check_whether_winMarginProb_is_calculated_with_upto10Pnts() {
        val inp = listOf(
            GameResult(homeTeamWinner = true, awayTeamWinner = false, 100, 90),
            GameResult(homeTeamWinner = true, awayTeamWinner = false,  125, 120),
            GameResult(homeTeamWinner = true, awayTeamWinner = false,  75, 25),
            GameResult(homeTeamWinner = false, awayTeamWinner = true, 25, 30),
            GameResult(homeTeamWinner = false, awayTeamWinner = true, 25, 35)
        )
        val res = Calculation.winMarginProbability(inp)
        assertEquals (res.home, 0.67, 0.00)
        assertEquals (res.away, 1.0, 0.00)
    }
}