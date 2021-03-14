package models

data class GameResult(
    val homeTeamWinner: Boolean,
    val awayTeamWinner: Boolean,
    val homeTeamPoints: Int,
    val awayTeamPoints: Int
) {
    companion object {
        fun fromString(input: String): GameResult {
            val results = input.split(delimiters = *arrayOf(","))
            return GameResult(
                homeTeamWinner = results[0].toInt() == 1,
                awayTeamWinner = results[1].toInt() == 1,
                homeTeamPoints = results[2].toInt(),
                awayTeamPoints = results[3].toInt()
            )
        }
    }
}