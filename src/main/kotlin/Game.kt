enum class Move {
    ROCK, PAPER, SCISSORS
}

interface MoveStrategy {
    fun chooseMove(): Move
}

class RandomMoveStrategy : MoveStrategy {
    override fun chooseMove(): Move {
        return listOf(Move.ROCK, Move.PAPER, Move.SCISSORS).random()
    }
}

class DeterministicMoveStrategy(private val move: Move) : MoveStrategy {
    override fun chooseMove(): Move = move
}

open class Player(val name: String, private val moveStrategy: MoveStrategy) {
    open fun chooseMove(): Move {
        return moveStrategy.chooseMove()
    }
}

class Game(private val rounds: Int) {
    var playerAWins = 0
    var playerBWins = 0
    var draws = 0

    fun play(players: List<Player>) {
        repeat(rounds) {
            val moveA = players[0].chooseMove()
            val moveB = players[1].chooseMove()

            when {
                moveA == moveB -> draws++
                moveA == Move.ROCK && moveB == Move.SCISSORS -> playerAWins++
                moveA == Move.PAPER && moveB == Move.ROCK -> playerAWins++
                moveA == Move.SCISSORS && moveB == Move.PAPER -> playerAWins++
                else -> playerBWins++
            }
        }
    }

    fun printResults() {
        println("Player A wins $playerAWins of $rounds games")
        println("Player B wins $playerBWins of $rounds games")
        println("Draws: $draws of $rounds games")
    }
}

fun main() {
    val game = Game(100)
    val playerA = Player("Player A", DeterministicMoveStrategy(Move.ROCK))
    val playerB = Player("Player B", RandomMoveStrategy())
    game.play(listOf(playerA, playerB))
    game.printResults()
}
