import kotlin.random.Random

class RockPaperScissorsGame {
    var playerAWins = 0
    var playerBWins = 0
    var draws = 0

    fun playRounds(numRounds: Int) {
        repeat(numRounds) {
            val playerAChoice = getRandomChoice()
            val playerBChoice = Choice.ROCK // Player B always chooses rock

            val result = calculateResult(playerAChoice, playerBChoice)
            updateScores(result)
        }
    }

    fun getRandomChoice(): Choice {
        return when (Random.nextInt(3)) {
            0 -> Choice.ROCK
            1 -> Choice.PAPER
            else -> Choice.SCISSORS
        }
    }

    fun calculateResult(playerA: Choice, playerB: Choice): Result {
        return when {
            playerA == playerB -> Result.DRAW
            playerA.beats() == playerB -> Result.WIN
            else -> Result.LOSE
        }
    }

    fun updateScores(result: Result) {
        when (result) {
            Result.WIN -> playerAWins++
            Result.LOSE -> playerBWins++
            Result.DRAW -> draws++
        }
    }

    fun printResults() {
        println("Player A wins $playerAWins of 100 games")
        println("Player B wins $playerBWins of 100 games")
        println("Draws: $draws of 100 games")
    }
}

enum class Choice {
    ROCK, PAPER, SCISSORS;

    fun beats(): Choice {
        return when (this) {
            ROCK -> SCISSORS
            PAPER -> ROCK
            SCISSORS -> PAPER
        }
    }
}

enum class Result {
    WIN, LOSE, DRAW
}

fun main() {
    val game = RockPaperScissorsGame()
    game.playRounds(100)
    game.printResults()
}
