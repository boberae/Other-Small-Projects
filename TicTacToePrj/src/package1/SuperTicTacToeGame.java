package package1;

import javax.swing.JOptionPane;

/******************************************************************
Java program that will play tic-tac-toe for various board sizes.
Win conditions include "wrapping", as if the board is spherical.

@author Tony Bober
@version 1.0
 *****************************************************************/
public class SuperTicTacToeGame {

	/** Game board made of an array of enum type cell */
	private Cell[][] board;
	
	/** Size of game board */
	private int bdSize;
	
	/** Status of game: X_WON, O_WON, CATS, or IN_PROGRESS */
	private GameStatus status;
	
	/** Variable to keep track of which players turn it is */
	private Cell turn;
	
	/** Variable to keep track of which player starts the games */
	private Cell initialTurn;
	
	/** Variable to keep track of how many moves have been made */
	private int currentTurn;
	
	/** Array keeping track of which cells players have picked, and
	 * in what order */
	private int[][] prevTurns;

	/******************************************************************
    Constructor for the game creates an empty board of size bdSize, and
    an empty array to keep track of previous turns.
    
    @param bdSize Size of the game board
    @param initialTurn Which player, X or O takes the first turn 
	*****************************************************************/
	public SuperTicTacToeGame(int bdSize, Cell initialTurn) 
	{
		this.bdSize = bdSize;
		this.initialTurn = initialTurn;
		
		// Set which player goes first
		turn = initialTurn;
		
		// Create a board
		board = new Cell[bdSize][bdSize];
		
		// Initialize current turn to zero
		currentTurn = 0;
		
		// Create empty array to keep track of moves in game
		prevTurns = new int[bdSize*bdSize][2];
		
		// Prepare game board
		reset();
	}
	

	/******************************************************************
    Getter method for the board
    
    @returns Array of enum type Cell representing the game board
	*****************************************************************/
	public Cell[][] getBoard() 
	{
		return board;
	}

	
	/******************************************************************
    Getter method for the game status
    
    @returns Enum type GameStatus: X_WON, O_WON, CATS, or IN_PROGRESS
	*****************************************************************/
	public GameStatus getGameStatus() 
	{
		return status;
	}

	
	/******************************************************************
    Select method first decides if a player's move is valid by making
    sure they've selected an empty cell.  If the move is valid, the
    cell value is changed to either an X or an O, the currentTurn value 
    is incremented and the player turn is toggled.
    
    @param row Row value of Cell selected
    @param col Column value of Cell selected
	*****************************************************************/
	public void select(int row, int col)
	{	
		// If the cell is empty, change it to either an X or an O,
		// and toggle whose turn it is.  If invalid, do nothing.
		if (turn == Cell.X && board[row][col] == Cell.EMPTY)
		{
			board[row][col] = Cell.X;
			turn = Cell.O;
			prevTurns[currentTurn][0] = row;
			prevTurns[currentTurn][1] = col;
			currentTurn++;			
		}
		else if (turn == Cell.O && board[row][col] == Cell.EMPTY)
		{
			board[row][col] = Cell.O;
			turn = Cell.X;
			prevTurns[currentTurn][0] = row;
			prevTurns[currentTurn][1] = col;
			currentTurn++;
		}
	}

	
	/******************************************************************
    Checks the game board for all win conditions, or cats.
    
    @returns Enum type GameStatus: X_WON, O_WON, CATS, or IN_PROGRESS
	*****************************************************************/
	public GameStatus isWinner() 
	{
		//Check for horizontal win condition
		for (int r = 0; r < bdSize; r++)
		{
			for (int c = 0; c < bdSize; c++)
			{
				// Check for X
				if (board[r][c] == Cell.X && board[r][(c+1) % bdSize] 
						== Cell.X && board[r][(c+2) % bdSize]
								== Cell.X)
				{
					return GameStatus.X_WON;
				}
				
				// Check for O
				if (board[r][c] == Cell.O && board[r][(c+1) % bdSize] 
						== Cell.O && board[r][(c+2) % bdSize]
								== Cell.O)
				{
					return GameStatus.O_WON;
				}
			}
		}

		//Check for vertical win condition
		for (int c =0; c < bdSize; c++)
		{
			for (int r = 0; r < bdSize; r++)
			{
				// Check for X
				if (board[r][c] == Cell.X && board[(r+1) % bdSize][c] 
						== Cell.X && board[(r+2) % bdSize][c]
								== Cell.X)
				{
					return GameStatus.X_WON;
				}
				
				// Check for O
				if (board[r][c] == Cell.O && board[(r+1) % bdSize][c] 
						== Cell.O && board[(r+2) % bdSize][c]
								== Cell.O)
				{
					return GameStatus.O_WON;
				}
			}
		}

		//Check for diagonal left-to-right win condition
		for (int r =0; r < bdSize; r++)
		{
			for (int c = 0; c < bdSize; c++)
			{
				// Check for X
				if (board[r][c] == Cell.X && board[(r+1) % bdSize]
						[(c+1) % (bdSize)] == Cell.X && 
						board[(r+2) % bdSize][(c+2) % bdSize] == Cell.X)
				{
					return GameStatus.X_WON;
				}
				
				// Check for O
				if (board[r][c] == Cell.O && board[(r+1) % bdSize]
						[(c+1) % (bdSize)] == Cell.O && 
						board[(r+2) % bdSize][(c+2) % bdSize] == Cell.O)
				{
					return GameStatus.O_WON;
				}
			}
		}

		//Check for diagonal right-to-left win condition
		for (int r = 2; r < bdSize + 2; r++)
		{
			for (int c = 0; c < bdSize; c++)
			{
				//Check for X
				if (board[r % bdSize][c] == Cell.X && 
						board[(r-1) % bdSize][(c+1) % bdSize] == Cell.X 
						&& board[r-2][(c+2) % bdSize] == Cell.X)
				{
					return GameStatus.X_WON;
				}
				
				//Check for O
				if (board[r % bdSize][c] == Cell.O && 
						board[(r-1) % bdSize][(c+1) % bdSize] == Cell.O 
						&& board[r-2][(c+2) % bdSize] == Cell.O)
				{
					return GameStatus.O_WON;
				}
			}
		}

		//Check for cats. If any Cell is empty, return IN_PROGRESS
		for (int r = 0; r < bdSize; r++)
		{
			for (int c = 0; c < bdSize; c++)
			{
				if (board[r][c] == Cell.EMPTY)
				{
					return GameStatus.IN_PROGRESS;
				}
			}
		}

		return GameStatus.CATS;
	}


	/******************************************************************
    Asks the user for the size of the board.  If the user gives a non-
    integer input, a warning message is printed, and the program is
    exited.  If the user gives an illegal board value, (<3 or >9), a 
    warning message is printed, and a default board size of 3 is used.
    
    @returns Enum type GameStatus: X_WON, O_WON, CATS, or IN_PROGRESS
	*****************************************************************/
	public static int newBoard()
	{
		// Ask user for board size.  If they don't enter an integer
		// value, print them a message and exit the program.
		int boardSize = 3;
		try
		{
			boardSize = Integer.parseInt(JOptionPane.showInputDialog(
					null, "Please enter the size of your board"));
		}
		catch (NumberFormatException e)
		{
			System.out.println("Improper input type. Board size must"
					+ "be an integer value");
			System.exit(0);
		}	

		// If the user enters a board size outside of the acceptable
		// range, print them a message and use a default board size of
		// 3.
		if (boardSize < 3 || boardSize > 9)
		{
			System.out.println("Board size must be greater than 2, and"
					+ " less than 10. Default board size of 3 chosen.");
			boardSize = 3;
		}
		return boardSize;
	}

	/******************************************************************
    Asks the user which player gets the first turn.  If 'X' or 'O' is
    entered (non-case sensitive) that player gets the first turn.  If
    anything else is entered, a warning message is printed, and X
    receives the first turn.
    
    @returns Enum type Cell indicating whether X or O gets the first
    turn
	*****************************************************************/
	public static Cell newStarter()
	{
		// String entered by user
		String starter;
		
		// Cell value (either X or O) representing who goes first
		Cell firstTurn;
		
		// Ask user who starts the game
		starter = JOptionPane.showInputDialog(null, "Who starts first? "
				+ "X or O");

		// Set boolean variable xStarts to true if x is entered, false
		// if o is entered.  If anything else is entered, set xStarts
		// to true, and give the user a warning message.
		if (starter.equals("o") || starter.equals("O"))
		{
			firstTurn = Cell.O;
		}
		else if (starter.equals("x") || starter.equals("X"))
		{
			firstTurn = Cell.X;
		}
		else
		{
			firstTurn = Cell.X;
			System.out.println("Improper input! X will start first by "
					+ "default.");
		}
		return firstTurn;
	}


	/******************************************************************
    Set which player gets the first turn, clear the board for a new 
    game, and initialize array prevTurns to all zeroes to prepare for 
    storing future moves.
	*****************************************************************/
	public void reset() 
	{
		// Set which player gets first turn
		turn = initialTurn;

		// Clear board to all empty cells
		for (int r = 0; r < bdSize; r++)
		{
			for (int c = 0; c < bdSize; c++)
			{
				board[r][c] = Cell.EMPTY;
			}
		}

		// Initialize prevTurns to all zeros
		for (int i = 0; i < (bdSize*bdSize); i++)
		{
			prevTurns[i][0] = 0;
			prevTurns[i][1] = 0;
		}
	}

	
	/******************************************************************
    Using the prevTurns array, this method undoes the previous move.
    Additionally, currentTurn is decremented.  If currentTurn is zero 
    (indicating the beginning of a game) this method does nothing.
	*****************************************************************/
	public void undo()
	{
		// Do nothing if there are no moves to undo
		if (currentTurn > 0)
		{
			// Undo previous move
			int r = prevTurns[currentTurn - 1][0];
			int c = prevTurns[currentTurn - 1][1];
			board[r][c] = Cell.EMPTY;
			
			// Toggle player turn back to correct value
			if (turn == Cell.X)
			{
				turn = Cell.O;
			}
			else if (turn == Cell.O)
			{
				turn = Cell.X;
			}
			
			//Decrement current turn
			currentTurn--;
		}
	}

}

