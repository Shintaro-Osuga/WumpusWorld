import java.util.ArrayList;
import java.math.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntBinaryOperator;


public class ProceduralGameBoard {
    private int[][] intBoard;
    private Room[][] board;

    private int boardSize;

    public ProceduralGameBoard(int boardSize)
    {
        this.boardSize = boardSize;
        board = new Room[boardSize][boardSize];
        intBoard = new int[boardSize][boardSize];
        initializeBoard();
        generateSafeSpaces();
        printBoard();
    }

    private void generateSafeSpaces()
    {
        int numSafeSpaces = getRandNumSafeSpaces();
        int row = ThreadLocalRandom.current().nextInt(0, boardSize);
        int col = ThreadLocalRandom.current().nextInt(0, boardSize);
        int[] currentLocation = {row, col};
        int numMovesMade = 0;

        System.out.println("The number of safe spaces is " + numSafeSpaces);

        while(numMovesMade <= numSafeSpaces)
        {
            intBoard[currentLocation[0]][currentLocation[1]] = 1;
            boolean madeMove = false;
            while(madeMove == false)
            {
                int randNum = ThreadLocalRandom.current().nextInt(0,4);
                int numMoves = ThreadLocalRandom.current().nextInt(1,4);
                System.out.println("current location: "+currentLocation[0] + ","+currentLocation[1]);
                System.out.println("randNum: "+randNum);
                System.out.println("numMovesMade: "+numMovesMade);
                System.out.println("numMoves: "+numMoves);
                
                row = currentLocation[0];
                col = currentLocation[1];
                
                switch(randNum)
                {
                    case 0:
                        int[] tempLLoc = checkRight(row, col, numMoves);
                        System.out.println("tempLLoc: "+tempLLoc[0]+","+tempLLoc[1]);
                        // if(tempLLoc[0] != currentLocation[0] || tempLLoc[1] != currentLocation[1])
                        if(tempLLoc != currentLocation)
                        {
                            System.out.println("checking left, left location: "+checkLeft(row, col, numMoves)[0]+","+checkLeft(row, col, numMoves)[1]);
                            currentLocation = moveLeft(row, col, numMoves);
                            madeMove = true;
                            numMovesMade++;
                        }
                        break;
                    case 1:
                        int[] tempRLoc = checkRight(row, col, numMoves);
                        System.out.println("tempRLoc: "+tempRLoc[0]+","+tempRLoc[1]);
                        // if(tempRLoc[0] != currentLocation[0] || tempRLoc[1] != currentLocation[1])
                        if(tempRLoc != currentLocation)
                        {
                            System.out.println("checking right"+checkRight(row, col, numMoves)[0]+","+checkRight(row, col, numMoves)[1]);
                            currentLocation = moveRight(row, col, numMoves);
                            madeMove = true;
                            numMovesMade++;
                        }
                        break;
                    case 2:
                        int[] tempULoc = checkRight(row, col, numMoves);
                        System.out.println("tempULoc: "+tempULoc[0]+","+tempULoc[1]);
                        // if(tempULoc[0] != currentLocation[0] || tempULoc[1] != currentLocation[1])
                        if(tempULoc != currentLocation)
                        {
                            System.out.println("checking Up"+checkUp(row, col, numMoves)[0]+","+checkUp(row, col, numMoves)[1]);
                            currentLocation = moveUp(row, col, numMoves);
                            madeMove = true;
                            numMovesMade++;
                        }
                        break;
                    case 3:
                        int[] tempDLoc = checkRight(row, col, numMoves);
                        System.out.println("tempDLoc: "+tempDLoc[0]+","+tempDLoc[1]);
                        // if(tempDLoc[0] != currentLocation[0] || tempDLoc[1] != currentLocation[1])
                        if(tempDLoc != currentLocation)
                        {
                            System.out.println("checking Down"+checkDown(row, col, numMoves)[0]+","+checkDown(row, col, numMoves)[1]);
                            currentLocation = moveDown(row, col, numMoves);
                            madeMove = true;    
                            numMovesMade++;
                        }
                        break;
                }
            }

            System.out.println("Move to " + currentLocation[0]+","+currentLocation[1]);
            printBoard();
        }
    }

    private int[] moveUp(int row, int col, int num)
    {
        int i = 0;
        while(i < num && row-1 >= 0 && intBoard[row-1][col] == 0)
        {
            i++;
            row -= 1;
            intBoard[row][col] = 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    private int[] checkUp(int row, int col, int num)
    {
        int i = 0;
        while(i < num && row-1 >= 0 && intBoard[row -1][col] == 0)
        {
            i++;
            row -= 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    private int[] moveDown(int row, int col, int num)
    {
        int i = 0;
        while(i < num && row+1 < boardSize && intBoard[row+1][col] == 0)
        {
            i++;
            row += 1;
            intBoard[row][col] = 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    private int[] checkDown(int row, int col, int num)
    {
        int i = 0;
        while(i < num && row+1 < boardSize && intBoard[row+1][col] == 0)
        {
            i++;
            row += 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    private int[] moveRight(int row, int col, int num)
    {
        int i = 0;
        while(i < num && col+1 < boardSize && intBoard[row][col+1] == 0)
        {
            i++;
            col += 1;
            intBoard[row][col] = 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    private int[] checkRight(int row, int col, int num)
    {
        int i = 0;
        while(i < num && col+1 < boardSize && intBoard[row][col+1] == 0)
        {
            i++;
            col += 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    private int[] moveLeft(int row, int col, int num)
    {
        int i = 0;
        while(i < num && col-1 >= 0 && intBoard[row][col-1] == 0)
        {
            i++;
            col -= 1;
            intBoard[row][col] = 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    private int[] checkLeft(int row, int col, int num)
    {
        int i = 0;
        while(i < num && col-1 >= 0 && intBoard[row][col-1] == 0)
        {
            i++;
            col -= 1;
        }
        int[] moved = {row, col};
        return moved;
    }

    
    private int getRandNumSafeSpaces()
    {
        int totalSize = (int) Math.pow(boardSize, 2);
        int min = totalSize - boardSize - ThreadLocalRandom.current().nextInt(boardSize, boardSize*2);
        int max = totalSize - boardSize;
        return (int) ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private void initializeBoard()
    {
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                intBoard[i][j] = 0;
            }
        }
    }

    private void printBoard()
    {
        System.out.print("  ");
        for(int i = 0; i < boardSize; i++)
        {
            System.out.print(" "+i+" ");
        }
        System.out.println();
        for(int i = 0; i < boardSize; i++)
        {
            System.out.println("------------------");
            System.out.print(i +" ");
            for(int j = 0; j < boardSize; j++)
            {
                System.out.print(" "+ intBoard[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("------------------");
    }
}
