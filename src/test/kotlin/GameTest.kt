import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GameTest {
    private lateinit var game: Game

    @BeforeEach
    fun setUp() {
        game = Game(5) // Set a smaller round count for faster tests
    }

    @Test
    fun `test RandomMoveStrategy`() {
        val strategy = RandomMoveStrategy()
        val move = strategy.chooseMove()
        assert(move == Move.ROCK || move == Move.PAPER || move == Move.SCISSORS)
    }

    @Test
    fun `test DeterministicMoveStrategy`() {
        val strategy = DeterministicMoveStrategy(Move.ROCK)
        assertEquals(Move.ROCK, strategy.chooseMove())
    }

    @Test
    fun `test chooseMove`() {
        val strategy = DeterministicMoveStrategy(Move.ROCK)
        val player = Player("Test Player", strategy)
        assertEquals(Move.ROCK, player.chooseMove())
    }

    @Test
    fun `test play with deterministic players rock & paper`() {
        val playerA = Player("Player A", DeterministicMoveStrategy(Move.ROCK)) // Player A always ROCK
        val playerB = mock<Player>() // Mock Player B for controlled behavior

        whenever(playerB.chooseMove()).thenReturn(Move.PAPER) // Mock Player B to always choose PAPER

        game.play(listOf(playerA, playerB)) // Pass mocked and real players

        assertEquals(0, game.playerAWins)
        assertEquals(5, game.playerBWins) // Expect Player B to win all rounds
        assertEquals(0, game.draws)
    }

    @Test
    fun `test play with deterministic players scissors & paper`() {
        val playerA = Player("Player A", DeterministicMoveStrategy(Move.SCISSORS)) // Player A always SCISSORS
        val playerB = mock<Player>() // Mock Player B for controlled behavior

        whenever(playerB.chooseMove()).thenReturn(Move.PAPER) // Mock Player B to always choose PAPER

        game.play(listOf(playerA, playerB)) // Pass mocked and real players

        assertEquals(5, game.playerAWins) // Expect Player A to win all rounds
        assertEquals(0, game.playerBWins)
        assertEquals(0, game.draws)
    }

    @Test
    fun `test play with deterministic players rock & scissors`() {
        val playerA = Player("Player A", DeterministicMoveStrategy(Move.ROCK)) // Player A always ROCK
        val playerB = mock<Player>() // Mock Player B for controlled behavior

        whenever(playerB.chooseMove()).thenReturn(Move.SCISSORS) // Mock Player B to always choose SCISSORS

        game.play(listOf(playerA, playerB)) // Pass mocked and real players

        assertEquals(5, game.playerAWins) // Expect Player A to win all rounds
        assertEquals(0, game.playerBWins)
        assertEquals(0, game.draws)
    }

    @Test
    fun `test draw paper & paper`() {
        val playerA = Player("Player A", DeterministicMoveStrategy(Move.PAPER)) // Player A always PAPER
        val playerB = mock<Player>() // Mock Player B for controlled behavior

        whenever(playerB.chooseMove()).thenReturn(Move.PAPER) // Mock Player B to always choose PAPER

        game.play(listOf(playerA, playerB)) // Pass mocked and real players

        assertEquals(0, game.playerAWins)
        assertEquals(0, game.playerBWins)
        assertEquals(5, game.draws)
    }

    @Test
    fun `test play with random player`() {
        val playerA = Player("Player A", DeterministicMoveStrategy(Move.SCISSORS)) // Player A always SCISSORS
        val playerB = Player("Player B", RandomMoveStrategy()) // Player B chooses randomly

        game.play(listOf(playerA, playerB))
        game.printResults()
        // We can't assert specific wins due to randomness in Player A's strategy.
        // However, we can check that the game state is updated correctly.
        assertTrue(game.playerAWins + game.playerBWins + game.draws == 5)
    }

}