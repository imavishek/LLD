package tictactoe;

public interface Game {
	// Template Pattern

	void initialize();

	Boolean isDraw();

	Boolean hasWinner();

	void displayGameState();

	Player getCurrentPlayer();

	Boolean makeMove(Move move);

	void updateCurrentPlayer();

	default void play() {
		System.out.println("Game started!");
		initialize();

		do {
			updateCurrentPlayer();
			displayGameState();
			Player currentPlayer = getCurrentPlayer();
			Move move = currentPlayer.getMove();
			while (!makeMove(move)) {
				System.out.println("Invalid Move");
				move = currentPlayer.getMove();
			}
		} while (!isDraw() && !hasWinner());

		displayGameState();

		if (isDraw()) {
			System.out.println("Game is a draw");
		} else if (hasWinner()) {
			System.out.println(getCurrentPlayer().getName() + " has won the game!");
		}
	}
}
