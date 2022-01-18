package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeFactory {

	static TicTacToeGame standard3X3Game() {
		TicTacToeSymbol cross = new TicTacToeSymbol("X");
		TicTacToeSymbol nought = new TicTacToeSymbol("O");
		TicTacToeBoard board = new TicTacToeBoard(3);
		Player player1 = new Player("Avishek");
		Player player2 = new Player("Amit");
		return new TicTacToeGame(board, Arrays.asList(player1, player2), Arrays.asList(cross, nought));
	}

	static TicTacToeGame customGame() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter board size: ");
		int size = sc.nextInt();

		TicTacToeSymbol cross = new TicTacToeSymbol("X");
		TicTacToeSymbol nought = new TicTacToeSymbol("O");
		TicTacToeBoard board = new TicTacToeBoard(size);
		Player player1 = new Player("Avishek");
		Player player2 = new Player("Amit");
		return new TicTacToeGame(board, Arrays.asList(player1, player2), Arrays.asList(cross, nought));
	}
}
