package package1;

import javax.swing.*;

/******************************************************************
Super tic-tac-toe game

@author Tony Bober
@version 1.0
 *****************************************************************/
public class SuperTicTacToe {

	/** Size of game board */
	private static int bdSize;
	
	/** Which player, X or O, gets initial turn? */
	private static Cell initialTurn;

	// Main method
	public static void main (String[] args)
	{	
		// Ask user for board size and who starts
		bdSize = SuperTicTacToeGame.newBoard();
		initialTurn = SuperTicTacToeGame.newStarter();
		
		// New JFrame
		JFrame frame = new JFrame ("Super Tic Tac Toe!");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		// New SuperTicTacToe panel of size bdSize
		SuperTicTacToePanel panel = new SuperTicTacToePanel(bdSize,
				initialTurn);
		frame.getContentPane().add(panel);
		frame.setSize(1000, 750);
		frame.setVisible(true);
	}
}

