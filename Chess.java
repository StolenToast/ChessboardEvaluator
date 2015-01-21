package seitz;

import java.util.ArrayList;

public class Chess
{

	public static char getCheck(char[][] board)
	{
		char output = '-';
		int kingsLeft = 2; // There will always be either 2 or no kings
		int i = -1, j = -1;
		ArrayList<Piece> kingList = new ArrayList<Piece>();
		ArrayList<Piece> checkList = new ArrayList<Piece>();

		// Construct a list of all kings ============== //
		for (char[] row : board)
		{
			if (kingsLeft > 0)
			{
				i++;
				for (char piece : row)
				{
					j++;
					if (piece == 'k' || piece == 'K')
					{
						// Run a check on any pieces possibly checking
						System.out.println("King found " + kingsLeft);
						kingsLeft--;
						kingList.add(new Piece(piece, i, j));
						// output = testForCheck(piece, board, i, j);
					}
				}
				j = -1;
			}
		}

		char firstKingChecker;
		// char secondKingChecker;
		// If no kings are found: done, return '-'
		if (kingsLeft > 0)
		{
			return output;
		} else
		{
			// Check both kings and get the results
			firstKingChecker = testForCheck(kingList.get(0), board);

			if (firstKingChecker != '-')
			{
				return firstKingChecker;
			} else
			{
				return testForCheck(kingList.get(1), board);
			}
		}
	}

	/*
	 * Method to find if a king is in check by a piece
	 * 
	 * Pre: a character 'k' or 'K' to indicate white or black
	 * 
	 * Post: a character representing a piece that is checking this king
	 */
	@SuppressWarnings("unchecked")
	private static char testForCheck(Piece king, char[][] board)
//			ArrayList<Piece> checkList)
	{
		// List of possible opponent categories
		String crossOpponents, diagOpponents1, diagOpponents2;
		char knights, outputPiece = '-';

		// King is white if capital
		if (king.equals('K')) // Black opponents
		{
			crossOpponents = "rq"; // Can check horiz./vert.
			diagOpponents1 = "bpq"; // Can check diagonally at one space
			diagOpponents2 = "bq"; // "" at more than one space away
			knights = 'n'; // knights, special case
		} else
		{
			crossOpponents = "RQ";
			diagOpponents1 = "BPQ";
			diagOpponents2 = "BQ";
			knights = 'N';
			if (king.equals('k'))
			{
				System.out.println(" it's a k");
			}
		}

		// Begin building list of possible check pieces:
//		checkList = new ArrayList<Piece>();

		// Find any opponent pieces horizontal to King
		// for (char piece : board[king.getRow()])
		// {
		// if (piece != '.' && opponents.indexOf(piece) != -1)
		// {
		// checkList.add(new Piece(piece, i, j));
		// System.out.println(checkList.toString());
		// }
		// }

		/*
		 * With the exception of the knight pieces can only check a king through
		 * a direct line, so only evaluate the pieces directly in line with the
		 * king, not behind any other pieces.
		 */

		int i = king.getCol();
		boolean completed = false;
		char evaluatingPiece;

		// Find if piece to king's west is an enemy
		while (i > 0 && !completed)
		{
			i--;
			evaluatingPiece = board[king.getRow()][i];
			if (evaluatingPiece != '.')
			{
				if (crossOpponents.indexOf(evaluatingPiece) != -1) // Opponent
				{
//					checkList.add(new Piece(evaluatingPiece, i, king.getRow()));
					outputPiece = evaluatingPiece;
				}
				// Whether it is an opponent or not
				completed = true;
			}
		}

		// Find if piece to king's east is an enemy
		i = king.getCol();
		completed = false;
		while (i < board[0].length - 1 && !completed)
		{
			i++;
			evaluatingPiece = board[king.getRow()][i];
			if (evaluatingPiece != '.')
			{
				if (crossOpponents.indexOf(evaluatingPiece) != -1)
				{
//					checkList.add(new Piece(evaluatingPiece, i, king.getRow()));
					outputPiece = evaluatingPiece;
				}
				// Whether it is an opponent or not
				completed = true;
			}
		}

		// Find if piece to king's north is an enemy
		int j = king.getRow();
		completed = false;
		while (j > 0 && !completed)
		{
			j--;
			evaluatingPiece = board[j][king.getCol()];
			if (evaluatingPiece != '.')
			{
				if (crossOpponents.indexOf(evaluatingPiece) != -1)
				{
//					checkList.add(new Piece(evaluatingPiece, i, king.getRow()));
					outputPiece = evaluatingPiece;
				}
				// Whether it is an opponent or not
				completed = true;
			}
		}

		// Find if piece to king's south is an enemy
		j = king.getRow();
		completed = false;
		while (j < board.length - 1 && !completed)
		{
			j++;
			evaluatingPiece = board[j][king.getCol()];
			if (evaluatingPiece != '.')
			{
				if (crossOpponents.indexOf(evaluatingPiece) != -1)
				{
//					checkList.add(new Piece(evaluatingPiece, i, king.getRow()));
					outputPiece = evaluatingPiece;
				}
				// Whether it is an opponent or not
				completed = true;
			}
		}

		// Find if piece to king's northwest is an enemy
		int distance = 0; // distance away from king
		i = king.getCol();
		j = king.getRow();
		completed = false;
		while (j > 0 && i > 0 && !completed)
		{
			j--;
			i--;
			distance++;
			evaluatingPiece = board[j][i];

			if (evaluatingPiece != '.')
			{
				switch (distance)
				{
				case 1:
					if (diagOpponents1.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
						outputPiece = evaluatingPiece;
					}
					break;
				default:
					if (diagOpponents2.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
						outputPiece = evaluatingPiece;
					}
					break;
				}
				completed = true;
			}
		}

		// Find if piece to king's southwest is an enemy
		distance = 0;
		i = king.getCol();
		j = king.getRow();
		completed = false;
		while (j < board.length - 1 && i > 0 && !completed)
		{
			j++;
			i--;
			distance++;
			evaluatingPiece = board[j][i];

			if (evaluatingPiece != '.')
			{
				switch (distance)
				{
				case 1:
					if (diagOpponents1.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
						outputPiece = evaluatingPiece;
					}
					break;
				default:
					if (diagOpponents2.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
						outputPiece = evaluatingPiece;
					}
					break;
				}
				completed = true;
			}
		}

		// Find if piece to king's southeast is an enemy
		distance = 0;
		i = king.getCol();
		j = king.getRow();
		completed = false;
		while (j < board.length - 1 && i < board[0].length - 1 && !completed)
		{
			j++;
			i++;
			distance++;
			evaluatingPiece = board[j][i];

			if (evaluatingPiece != '.')
			{
				switch (distance)
				{
				case 1:
					if (diagOpponents1.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
						outputPiece = evaluatingPiece;
					}
					break;
				default:
					if (diagOpponents2.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
						outputPiece = evaluatingPiece;
					}
					break;
				}
				completed = true;
			}
		}

		// Find if piece to king's northeast is an enemy
		distance = 0;
		i = king.getCol();
		j = king.getRow();
		completed = false;
		while (j > 0 && i < board[0].length - 1 && !completed)
		{
			j--;
			i++;
			distance++;
			evaluatingPiece = board[j][i];

			if (evaluatingPiece != '.')
			{
				switch (distance)
				{
				case 1:
					if (diagOpponents1.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
					outputPiece = evaluatingPiece;
					}
					break;
				default:
					if (diagOpponents2.indexOf(evaluatingPiece) != -1)
					{
//						checkList.add(new Piece(evaluatingPiece, i, j));
						outputPiece = evaluatingPiece;
					}
					break;
				}
				completed = true;
			}
		}

		/*
		 * Special cases for knights
		 */

		j = king.getRow() - 2;
		i = king.getCol();

		if (j >= 0)
		{
			// up left
			if (i - 1 >= 0)
			{
				if (knights == board[j][i - 1])
				{
					return knights;
				}
			}
			// up right
			if (i + 1 < board[0].length - 1)
			{
				if (knights == board[j][i + 1])
				{
					return knights;
				}
			}
		}

		j = king.getRow() + 2;

		if (j < board.length - 1)
		{
			// down left
			if (i - 1 >= 0)
			{
				if (knights == board[j][i - 1])
				{
					return knights;
				}
			}
			// down right
			if (i + 1 < board[0].length - 1)
			{
				if (knights == board[j][i + 1])
				{
					return knights;
				}
			}
		}

		// Now check left and right
 
		j = king.getRow();
		i = king.getCol() - 2;

		if (i >= 0)
		{
			// left up
			if (j - 1 >= 0)
			{
				if (knights == board[j - 1][i])
				{
					return knights;
				}
			}
			// left down
			if (j + 1 < board.length - 1)
			{
				if (knights == board[j + 1][i])
				{
					return knights;
				}
			}
		}

		i = king.getCol() + 2;

		if (i < board[0].length - 1)
		{
			// right up
			if (j - 1 >= 0)
			{
				if (knights == board[j - 1][i])
				{
					return knights;
				}
			}
			// right down
			if (j + 1 < board[0].length - 1)
			{
				if (knights == board[j + 1][i])
				{
					return knights;
				}
			}
		}

		// End of everything, no check found
		return outputPiece;
	}
}

/*
 * Piece class
 * 
 * Simple class to hold information about a chess piece
 * 
 * type: piece type row, column: piece location on board
 */
class Piece
{
	private int row, column;
	private char type;

	public Piece(char type, int row, int column)
	{
		this.type = type;
		this.row = row;
		this.column = column;
	}

	public char print()
	{
		return this.getType();
	}

	@Override
	public String toString()
	{
		return this.getType() + " [" + this.getRow() + "," + this.getCol()
				+ "]";
	}

	public boolean equals(char piece)
	{
		if (this.type == piece)
		{
			return true;
		}
		return false;
	}

	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return column;
	}

	public char getType()
	{
		return type;
	}
}
