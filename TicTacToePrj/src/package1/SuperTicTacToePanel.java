package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************
GUI for super tic-tac-toe game

@author Tony Bober
@version 1.0
 *****************************************************************/
public class SuperTicTacToePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/** Array of JButtons representing board */
	private JButton[][] board;
	
	/** Array of Cells to display board values */
	private Cell[][] iCell;
	
	/** Quit game button */
	private JButton quitButton;
	
	/** Reset game button */
	private JButton resetButton;
	
	/** Undo previous move button */
	private JButton undoButton;
	
	/** JLabel indicating number of wins for x */
	private JLabel xWon;
	
	/** JLabel indicating number of wins for o */
	private JLabel oWon;
	
	/** JPanel with buttons and labels */
	private JPanel bottom;
	
	/** JPanel with game board */
	private JPanel center;
	
	/** ButtonListener */
	private ButtonListener listener;
	
	/** Icon used to represent player X */
	private ImageIcon xIcon;
	
	/** Icon used to represent player O */
	private ImageIcon oIcon;
	
	/** Icon used to represent empty cell */
	private ImageIcon emptyIcon;
	
	/** Win count for player X */
	private int xWinCount = 0;
	
	/** Win count for player O */
	private int oWinCount = 0;
	
	/** Board size */
	private int bdSize;
	
	/** Player who gets the first turn */
	private Cell initialTurn;
	
	/** New tic-tac-toe game */
	private SuperTicTacToeGame game;

 
	public SuperTicTacToePanel(int bdSize, Cell initialTurn) 
	{
		// Create game
		this.bdSize = bdSize;
		this.initialTurn = initialTurn;
		game = new SuperTicTacToeGame(bdSize, initialTurn);

		// Create panels for game board and buttons
		bottom = new JPanel();	
		center = new JPanel();

		// Icons for x, o, empty spaces
		xIcon = new ImageIcon ("x.png");
		oIcon = new ImageIcon ("o.jpg");
		emptyIcon = new ImageIcon ("blank.gif");

		// create listeners
		listener = new ButtonListener();

		// Create quit button
		quitButton = new JButton("Quit");
		quitButton.addActionListener(listener);

		// Create reset button
		resetButton = new JButton("Reset");
		resetButton.addActionListener(listener);

		// Create undo button
		undoButton = new JButton("Undo");
		undoButton.addActionListener(listener);
		
		// Create and display the center grid (the board) 
		createBoard();
		displayBoard();

		// Create labels for number of wins
		JLabel labxWins = new JLabel ("X Wins: ");
		JLabel laboWins = new JLabel ("O Wins: ");
		xWon = new JLabel ("" + xWinCount);
		oWon = new JLabel ("" + oWinCount);
		
		// Create bottom grid (the buttons)
		bottom.setLayout (new GridLayout(4,3));
		bottom.add(labxWins);
		bottom.add(xWon);
		bottom.add(laboWins);
		bottom.add(oWon);
		bottom.add(quitButton);
		bottom.add(resetButton);
		bottom.add(undoButton);

		// Add the two grids to the panel
		add (center, BorderLayout.CENTER);
		add (bottom, BorderLayout.EAST);

	}

	
	// Create the center grid (the board)
	private void createBoard()
	{
		center.setLayout(new GridLayout(bdSize,bdSize,3,3));
		board = new JButton[bdSize][bdSize];
		for (int row = 0; row < bdSize; row++) 
		{
			for (int col = 0; col < bdSize; col++) 
			{
				board[row][col] = new JButton ("");
				board[row][col].addActionListener(listener);
				center.add(board[row][col]);
			}
		}
	}

	// Display the center grid (the board)
	private void displayBoard() 
	{
		iCell = game.getBoard ();

		for (int r = 0; r < bdSize; r++) 
		{
			for (int c = 0; c < bdSize; c++) 
			{
				if (iCell[r][c] == Cell.O)
				{
					board[r][c].setIcon(oIcon);
				}

				if (iCell[r][c] == Cell.X)
				{
					board[r][c].setIcon(xIcon);
				}

				if (iCell[r][c] == Cell.EMPTY)
				{
					board[r][c].setIcon(emptyIcon);
				}
			}
		}
	}


	// Implement button actions
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) 
		{
			// Quit button exits game
			if (quitButton == e.getSource())
			{
				System.exit(0);
			}

			// Reset button removes old board, and asks the user for a
			// new board size, and which player starts.  A new game is
			// started from the user's entries// Checks board for all win conditions for X. If X won, a 
			// dialog message is shown, and xWinCount is incremented
			if (resetButton == e.getSource())
			{
				// Remove old board
				for (int r = 0; r < bdSize; r++)
				{
					for (int c = 0; c < bdSize; c++)
					{
						center.remove(board[r][c]);
					}
				}
					
				// Ask user for new board size, and who goes first
				bdSize = SuperTicTacToeGame.newBoard();
				initialTurn = SuperTicTacToeGame.newStarter();
				
				// Begin new game with new parameters
				game = new SuperTicTacToeGame(bdSize, initialTurn);
				
				// Create and display the new board
				createBoard();
				displayBoard();
			}
			
			// Undo button undoes previous turn
			if (undoButton == e.getSource())
			{
				game.undo();
			}

			// Selects any button on the grid pressed by user
			for (int r = 0; r < bdSize; r++) 
			{
				for (int c = 0; c < bdSize; c++) 
				{
					if (board[r][c] == e.getSource()) 
						game.select (r,c);
				}
			}

			// Displays board after it is manipulated
			displayBoard();	

			// Checks board for all win conditions for X. If X won, a 
			// dialog message is shown, and xWinCount is incremented,
			// and game is reset
			if (game.isWinner() == GameStatus.X_WON) 
			{
				JOptionPane.showMessageDialog(null, "X Won!");
				xWinCount++;
				xWon.setText("" + xWinCount);
				game.reset();
				displayBoard();
			}

			// Checks board for all win conditions for O. If O won, a 
			// dialog message is shown, and oWinCount is incremented,
			// and game is reset
			if (game.isWinner() == GameStatus.O_WON) 
			{
				JOptionPane.showMessageDialog(null, "O Won!");
				oWinCount++;
				oWon.setText("" + oWinCount);
				game.reset();
				displayBoard();
			}
			
			// Checks board for cats. If cats are present, a dialog
			// message is shown, and game is reset
			if (game.isWinner() == GameStatus.CATS) 
			{
				JOptionPane.showMessageDialog(null, "CATS!");
				game.reset();
				displayBoard();
			}

		}
	}
}