package tictactoe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class Player {
	private String name;

	public Move getMove() {
		Scanner sc = new Scanner(System.in);
		System.out.println(name + ", Please enter cell row: ");
		int row = sc.nextInt();
		System.out.println(name + ", Please enter cell col: ");
		int col = sc.nextInt();

		return new Move(row, col);
	}
}
