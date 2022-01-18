package tictactoe;

public class Main {
	public static void main(String[] args) {
		Game game = TicTacToeFactory.standard3X3Game();
		game.play();
	}
}
