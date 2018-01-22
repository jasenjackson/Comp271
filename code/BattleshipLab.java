import java.util.*;
import java.util.Scanner;
public class BattleshipLab
{
   Scanner input = new Scanner(System.in);
   public static final boolean DEBUG = false;
   public static void breakln()
   {
      System.out.println("_____________________________________");
      System.out.println("");
   }
   public static void createBoard(String[][] board)
   {
      for(int r = 0; r < board.length; r++)
      {
         for(int c = 0; c < board[0].length; c++)
         {
            board[r][c] = "~";
         }
      }
   }
   public static void showBoard(String[][] board)
   {
      breakln();
      for(int r = 0; r < board.length; r++)
      {
         if(DEBUG == true)
         {
            for(int c = 0; c < board[0].length; c++)
            {
               System.out.print(" "+board[r][c]);
            }
            System.out.println("");
         }
         else
         {
            for(int c = 0; c < board[0].length; c++)
            {
               if(board[r][c].equals("S"))
               {
                  System.out.print(" "+"~");
               }
               else
               {
                  System.out.print(" "+board[r][c]);
               }
            }
            System.out.println("");
         }
      }
      breakln();
   }
   public static void createShip(String[][] board,int size)
   {
      if(Math.random() < 0.5)
      {
         int col = (int)(Math.random()*5);
         int row = (int)(Math.random()*7);
         for(int i = 0; i<size; i++)
         {
            board[row][col+i] = "S";
         }
      }
      else
      {
         int col = (int)(Math.random()*7);
         int row = (int)(Math.random()*5);
         for(int i = 0; i<size; i++)
         {
            board[row+i][col] = "S";
         }
      }
   }
   public static int userFire(String[][] board, int hits, int torps)
   {
      Scanner input = new Scanner(System.in);
      int row,col;
      System.out.println("You have: "+ torps +" torpedos left!");
      System.out.println("Select a row to fire in: ");
      row = input.nextInt();
      while(row > 8 || row < 1) // Error checking for row
      {
         System.out.println("Enter a valid row (1 -> 8)");
         row = input.nextInt();
      }
      System.out.println("Select a column to fire in: ");
      col = input.nextInt();
      while(col > 8 || col < 1) // Error checking for column
      {
         System.out.println("Enter a valid col (1 -> 8)");
         col = input.nextInt();
      }
      if(board[row-1][col-1].equals("S"))
      {
         hits ++;
         System.out.println("~~~~~~~ HIT ~~~~~~~");
         board[row-1][col-1] = "!";
      }
      else
      {
         System.out.println("~~~~~~~ MISS ~~~~~~~");
         board[row-1][col-1] = "M";
      }
      return hits;
   }
   public static void finall(int hits, int torps)
   {
      if(hits < 4)
         System.out.println("Sorry, but you lost because you didn't sink the ship.");
      if(torps < 1)
         System.out.println("You have lost all your torpedos");
      else
         if(hits >= 4)
         {
            System.out.println("You have beaten the game battleship, Thanks for playing!");
         }
      System.out.println("Good game, well played!");
   }
   public static void main(String[] arg)
   {
      String[][] board = new String[8][8];
      createBoard(board);
      createShip(board, 4);
      int torps = 15;
      int hits = 0;
      /// Starting real stuff
      while(torps > 0 && hits < 4)
      {
         showBoard(board);
         hits = userFire(board, hits, torps);
         torps --;
      }
      finall(hits, torps);
   }
 }
