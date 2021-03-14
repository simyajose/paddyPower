import Calculation.calcProbability
import Calculation.halfPointsProbability
import Calculation.winMarginProbability
import Source.readGameResults

fun main() {
    println("Calculating probabilities!")

    val results = readGameResults()

    results?.let {
        val winProb = calcProbability(results)
        println("probability for homeTeam to win is $winProb")
        println("probability for awayTeam to win is ${1 - winProb}")

        val halfPointProb = halfPointsProbability(results)
        println("half point probability over the line is $halfPointProb")
        println("half point probability under the line is ${1 - halfPointProb}")

        val winMarginProb = winMarginProbability(results)
        println("Home team winning margin probability with <=10pts is ${winMarginProb.home}")
        println("Home team winning margin probability with >=11pts is ${String.format("%.2f", (1 - winMarginProb.home))}")
        println("Away team winning margin probability with <=10pts is ${winMarginProb.away}")
        println("Away team winning margin probability with >=11pts is ${String.format("%.2f", (1 - winMarginProb.away))}")

        println("calculations complete!")
    }
}


