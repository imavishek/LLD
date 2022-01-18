package tictactoe;

import java.util.List;

public class TicTacToeGame implements Game {
	TicTacToeBoard ticTacToeBoard;
	List<Player> players;
	List<TicTacToeSymbol> symbols;
	int currentPlayerIndex;

	public TicTacToeGame(TicTacToeBoard ticTacToeBoard, List<Player> players, List<TicTacToeSymbol> symbols) {
		this.ticTacToeBoard = ticTacToeBoard;
		this.players = players;
		this.symbols = symbols;
	}

	@Override
	public void initialize() {
		currentPlayerIndex = 0;
	}

	@Override
	public Boolean isDraw() {
		return ticTacToeBoard.isFull();
	}

	@Override
	public Boolean hasWinner() {
		return ticTacToeBoard.getIsRowCaptured() || ticTacToeBoard.getIsColCaptured() || ticTacToeBoard.getIsDiagonalCaptured();
	}

	@Override
	public void displayGameState() {
		ticTacToeBoard.display();
	}

	@Override
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	@Override
	public Boolean makeMove(Move move) {
		return ticTacToeBoard.set(move.getRow(), move.getCol(), getCurrentPlayerSymbol());
	}

	@Override
	public void updateCurrentPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}

	private TicTacToeSymbol getCurrentPlayerSymbol() {
		return symbols.get(currentPlayerIndex);
	}
}
