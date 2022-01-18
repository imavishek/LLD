package tictactoe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicTacToeBoard {
	private TicTacToeSymbol[][] board;
	private int boardSize;
	private int filledCells;
	private Boolean isRowCaptured;
	private Boolean isColCaptured;
	private Boolean isDiagonalCaptured;

	public TicTacToeBoard(int boardSize) {
		this.boardSize = boardSize;
		filledCells = 0;
		board = new TicTacToeSymbol[boardSize][boardSize];
	}

	public void display() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == null)
					System.out.print(" ");
				else
					System.out.print(board[i][j].getSymbol());

				System.out.print(" | ");
			}
			System.out.println();
		}
	}

	public boolean isFull() {
		return filledCells == (boardSize * boardSize);
	}

	public boolean set(int row, int col, TicTacToeSymbol symbol) {
		if (validate(row, col)) {
			row--;
			col--;
			board[row][col] = symbol;
			filledCells++;
			isRowCaptured = isRowCaptured(row, symbol);
			isColCaptured = isColCaptured(col, symbol);
			isDiagonalCaptured = isDiagonalCaptured(symbol);

			return true;
		}

		return false;
	}

	public boolean validate(int row, int col) {
		return row >= 1 && row <= boardSize && col >= 1 && col <= boardSize && board[row - 1][col - 1] == null;
	}

	public boolean isRowCaptured(int row, TicTacToeSymbol symbol) {
		for (int col = 0; col < boardSize; col++) {
			if (board[row][col] != symbol)
				return false;
		}

		return true;
	}

	public boolean isColCaptured(int col, TicTacToeSymbol symbol) {
		for (int row = 0; row < boardSize; row++) {
			if (board[row][col] != symbol)
				return false;
		}

		return true;
	}

	public boolean isDiagonalCaptured(TicTacToeSymbol symbol) {
		Boolean diagonal = true;
		Boolean reverseDiagonal = true;

		for (int i = 0; i < boardSize; i++) {
			if (board[i][i] != symbol) {
				diagonal = false;
				break;
			}
		}
		int k = boardSize - 1;
		for (int i = 0; i < boardSize; i++) {
			if (board[i][k--] != symbol) {
				reverseDiagonal = false;
				break;
			}
		}

		return diagonal || reverseDiagonal;
	}
}


