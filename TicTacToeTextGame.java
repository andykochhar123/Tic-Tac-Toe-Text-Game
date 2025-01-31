// 0 1 2
// 3 4 5
// 6 7 8

/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    
	    // loading tic-tac-toe grid
		ArrayList <ArrayList <Character>> grid = new ArrayList<>();
		grid = loadGrid();
		printGrid(grid);
		
		/**
		 * player one has X
		 * player two has O
		 * */
		ArrayList <Integer> playerOneMoves = new ArrayList<>();
		ArrayList <Integer> playerTwoMoves = new ArrayList<>();
		
		/**
		 * player one is false
		 * player two is true
		 * 
		 * move will start at X
		 * */
		boolean playerTurn = false;
		char playerChar = 'X';
		
		// both starting off as false as game has not started yet
		boolean playerOneCheck = checkWin(playerOneMoves);
		boolean playerTwoCheck = checkWin(playerTwoMoves);
		boolean tieCheck = checkTie(grid);
		
		while (playerOneCheck || playerTwoCheck || tieCheck)
		{
		    int playerMove = 0;
		    Scanner playerInput = new Scanner(System.in);
		    
		    // player one's turn
		    if (playerTurn == false)
		    {
		        playerChar = 'X';
		        System.out.print("Player 1, enter your move: ");
		        
		        playerMove = playerInput.nextInt();
		        
		        if (playerMove < 0 || playerMove > 9)
		        {
		            System.out.println("Input is out of bounds");
		        }
		        
		        else if (playerOneMoves.contains(playerMove)
		        || playerTwoMoves.contains(playerMove))
		        {
		            System.out.println("Input has already been taken");
		        }
		        
		        else
		        {
		            playerOneMoves.add(playerMove);
		            
		            playerTurn = true;
		        }
		        
		    }
		    
		    // player two's turn
		    else
		    {
		        playerChar = 'O';
		        System.out.print("Player 2, enter your move: ");
		        
		        playerMove = playerInput.nextInt();
		        
		        if (playerMove < 0 || playerMove > 9)
		        {
		            System.out.println("Input is out of bounds");
		        }
		        
		        else if (playerOneMoves.contains(playerMove)
		        || playerTwoMoves.contains(playerMove))
		        {
		            System.out.println("Input has already been taken");
		        }
		        
		        else
		        {
		            playerTwoMoves.add(playerMove);
		            
		            playerTurn = false;
		        }
		    }
		    
		    // refreshing screen by clearing console
		    System.out.print("\033[H\033[2J");
		    System.out.flush();
		    
		    // reloading grid after turn is taken
		    grid = reloadGrid(playerOneMoves, playerTwoMoves);
		    printGrid(grid);
		    
		    // checking for player wins
		    playerOneCheck = checkWin(playerOneMoves);
		    playerTwoCheck = checkWin(playerTwoMoves);
		    
		    // checking for tie
		    tieCheck = checkTie(grid);
		}
		
		if (playerOneCheck == true)
		{
		    System.out.println("Game over: Player 1 wins");
		}
		
		else if (playerTwoCheck == true)
		{
		    System.out.println("Game over: Player 2 wins");
		}
		
		else
		{
		    System.out.println("Game over: Tie");
		}
	}
	
	public static boolean checkTie(ArrayList <ArrayList <Character>> grid)
	{
	    boolean check = true;
	    
	    for (ArrayList <Character> row : grid)
	    {
	        if (row.contains('*'))
	        {
	            check = false;
	        }
	    }
	    
	    return check;
	}
	
	public static ArrayList <ArrayList <Character>> loadGrid()
	{
	    ArrayList <ArrayList <Character>> grid = new ArrayList<>();
	    
	    for (int i = 0; i < 3; i++)
	    {
	        ArrayList <Character> row = new ArrayList<>();
	        for (int j = 0; j < 3; j++)
	        {
	            row.add('*');
	        }
	        grid.add(row);
	    }
	    
	    return grid;
	}
	
	public static ArrayList <ArrayList<Character>> reloadGrid(ArrayList <Integer> playerOne,
	ArrayList <Integer> playerTwo)
	{
	    ArrayList <ArrayList <Character>> grid = new ArrayList<>();
	    
	    for (int i = 0; i < 9; i++)
	    {
	        int row = 0;
	        
	        if (0 <= i <= 2)
	        {
	            row = 0;
	        }
	        
	        else if (3 <= i <= 5)
	        {
	            row = 1;
	        }
	        
	        else
	        {
	            row = 2;
	        }
	        
	        if (playerOne.contains(i))
	        {
	            grid.get(row).add('X');
	        }
	        
	        else if (playerTwo.contains(i))
	        {
	            grid.get(row).add('O');
	        }
	        
	        else
	        {
	            grid.get(row).add('*');
	        }
	    }
	    
	    return grid;
	}
	
	public static void printGrid(ArrayList <ArrayList <Character>> grid)
	{
	    for (int i = 0; i < grid.size(); i++)
	    {
	        for (int j = 0; j < grid.get(i).size(); j++)
	        {
	            System.out.print(grid.get(i).get(j) + " ");
	        }
	        System.out.println();
	    }
	}
	
	public static boolean checkWin(ArrayList <Integer> playerMoves)
	{
	    boolean check = false;
	    
	    for (int i = 0; i < playerMoves.size(); i++)
	    {
	        for (int j = i + 1; j < playerMoves.size(); j++)
	        {
	            for (int k = j + 1; k < playerMoves.size(); k++)
	            {
	                if (i < playerMoves.size() && j < playerMoves.size() 
	                && k < playerMoves.size())
	                {
	                    // checking horizontal
	                    if ((i == 0 && j == 1 && k == 2) || (i == 3 && j == 4 && k == 5)
	                    || (i == 6 && j == 7 && k == 8))
	                    {
	                        check = true;
	                    }
	                    
	                    // checking vertical
	                    else if ((i == 0 && j == 3 && k == 6) || (i == 1 && j == 4 && k == 7)
	                    || (i == 2 && j == 5 && k == 8))
	                    {
	                        check = true;
	                    }
	                    
	                    // checking diagonal
	                    else if ((i == 0 && j == 4 && k == 8) || (i == 2 && j == 4 && k == 6))
	                    {
	                        check = true;
	                    }
	                }
	            }
	        }
	    }
	    
	    return check;
	}
}