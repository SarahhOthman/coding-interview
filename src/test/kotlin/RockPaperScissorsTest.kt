import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.ByteArrayOutputStream

class RockPaperScissorsTest {
    @Test
    fun testRandomChoice() {
        val game = RockPaperScissorsGame()
        val choice = game.getRandomChoice()
        assertEquals(true, choice in Choice.entries.toTypedArray())
    }

    @Test
    fun testCalculateResult() {
        val game = RockPaperScissorsGame()
        assertEquals(Result.WIN, game.calculateResult(Choice.PAPER, Choice.ROCK))
        assertEquals(Result.LOSE, game.calculateResult(Choice.ROCK, Choice.PAPER))
        assertEquals(Result.DRAW, game.calculateResult(Choice.ROCK, Choice.ROCK))
        assertEquals(Result.DRAW, game.calculateResult(Choice.PAPER, Choice.PAPER))
        assertEquals(Result.DRAW, game.calculateResult(Choice.SCISSORS, Choice.SCISSORS))
        assertEquals(Result.WIN, game.calculateResult(Choice.ROCK, Choice.SCISSORS))
        assertEquals(Result.WIN, game.calculateResult(Choice.PAPER, Choice.ROCK))
        assertEquals(Result.WIN, game.calculateResult(Choice.SCISSORS, Choice.PAPER))
        assertEquals(Result.LOSE, game.calculateResult(Choice.SCISSORS, Choice.ROCK))
        assertEquals(Result.LOSE, game.calculateResult(Choice.ROCK, Choice.PAPER))
        assertEquals(Result.LOSE, game.calculateResult(Choice.PAPER, Choice.SCISSORS))
    }

    @Test
    fun testUpdateScores() {
        val game = RockPaperScissorsGame()
        game.updateScores(Result.WIN)
        assertEquals(1, game.playerAWins)
        game.updateScores(Result.LOSE)
        assertEquals(1, game.playerBWins)
        game.updateScores(Result.DRAW)
        assertEquals(1, game.draws)
    }

    @Test
    fun testPlayRounds() {
        val game = RockPaperScissorsGame()
        game.playRounds(100)
        // Check if the total number of rounds played is 100
        assertEquals(100, game.playerAWins + game.playerBWins + game.draws)
    }
}
