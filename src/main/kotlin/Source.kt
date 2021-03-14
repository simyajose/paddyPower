import models.GameResult
import models.GameResult.Companion.fromString
import java.lang.Exception
import kotlin.streams.toList

object Source {
    private const val SOURCE_FILE_NAME = "GameResults_PPB.csv"

    fun readGameResults(): List<GameResult>? {
        return try {
            val inputStream = Source::class.java.getResourceAsStream(SOURCE_FILE_NAME)
            val reader = inputStream.bufferedReader()

            //remove header row
            reader.readLine()

            reader.use {
                it.lines()
                    .map { line -> fromString(line) }
                    .toList()
            }

        } catch (ex: Exception) {
            println("Failed to read game results data due to error ${ex.message}")
            null
        }
    }
}