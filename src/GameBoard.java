import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {
    private Room[][] gameBoard;
    private int boardSize;
    private int[] wumpusLocation;
    private int[] goldLocation;

    public GameBoard(int boardSize)
    {
        gameBoard = new Room[boardSize][boardSize];
        this.boardSize = boardSize;
        wumpusLocation = new int[2];
        goldLocation = new int[2];
        createBoard();
        setLocations();
        updateNeighbors();
        System.out.println(gameBoard[wumpusLocation[0]][wumpusLocation[1]].getNeighbors().contains("Empty"));
        print();
    }

    private void createBoard()
    {
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                int[] tempIndex = new int[]{i,j};
                gameBoard[i][j] = new Room(tempIndex);
            }
        }
    }

    private void setPerceptLocations()
    {
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                if(gameBoard[i][j].getObstacle().getClass().equals(Empty.class) == true)
                {
                    if(gameBoard[i][j].getNeighbors().contains("Wumpus"))
                    {
                        gameBoard[i][j].getObstacle();
                    }
                }
            }
        }
    }

    private void updateNeighbors()
    {
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                findNeighbors(i, j);
            }
        }
    }

    private void findNeighbors(int row, int col)
    {
        ArrayList<Object> neighbors = new ArrayList<Object>();
        if(row-1 >= 0)
        {
            neighbors.add(gameBoard[row-1][col].getObstacle());
        }
        if(row+1 < boardSize)
        {
            neighbors.add(gameBoard[row+1][col].getObstacle());
        }
        if(col-1 >= 0)
        {
            neighbors.add(gameBoard[row][col-1].getObstacle());
        }
        if(col+1 < boardSize)
        {
            neighbors.add(gameBoard[row][col+1].getObstacle());
        }

        gameBoard[row][col].setNeighbors(neighbors);
    }

    private void setLocations()
    {
        setWumpus();
        setGoldLocation();
        setPits();
    }

    private void setPits()
    {
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                if(gameBoard[i][j].getObstacle().getClass().equals(Empty.class) && i + j != 0 && i+j != 1)
                {
                    if(ThreadLocalRandom.current().nextInt(0,100) < 15)
                    {
                        System.out.println("Placing new pit");
                        gameBoard[i][j].setObstacle(new Pit());
                    }
                }
            }
        }
    }

    private void setWumpus()
    {
        int row = ThreadLocalRandom.current().nextInt(0, boardSize);
        int col = ThreadLocalRandom.current().nextInt(0, boardSize);
    
        while(row + col  == 0 && row + col == 1)
        {
            row = ThreadLocalRandom.current().nextInt(0, boardSize);
            col = ThreadLocalRandom.current().nextInt(0, boardSize);
        }

        wumpusLocation[0] = row;
        wumpusLocation[1] = col;

        gameBoard[row][col].setObstacle(new Wumpus());
    }

    private void setGoldLocation()
    {
        int row = ThreadLocalRandom.current().nextInt(0, boardSize);
        int col = ThreadLocalRandom.current().nextInt(0, boardSize);
        while(row == wumpusLocation[0] && col == wumpusLocation[1] && row + col == 0 && row + col == 1)
        {
            row = ThreadLocalRandom.current().nextInt(0, boardSize);
            col = ThreadLocalRandom.current().nextInt(0, boardSize);
        }
    
        goldLocation[0] = row;
        goldLocation[1] = col;

        gameBoard[row][col].setObstacle(new Gold());
    }

    public void print()
    {
        System.out.print("  ");
        for(int i = 0; i < boardSize; i++)
        {
            System.out.print("   " +i+"  ");
        }
        System.out.println();
        System.out.println(" ---------------------------------");
        for(int i = 0; i < boardSize; i++)
        {
            System.out.print(i);
            System.out.print(" | ");
            for(int j = 0; j < boardSize; j++)
            {
                if(gameBoard[i][j].getObstacle().getClass().equals(Empty.class))
                {
                    System.out.print(" ");
                    if(gameBoard[i][j].getNeighbors().contains("Wumpus"))
                    {
                        System.out.print("S");
                    }else{
                        System.out.print(" ");
                    }
                    if(gameBoard[i][j].getNeighbors().contains("Pit"))
                    {
                        System.out.print("B");
                    }else{
                        System.out.print(" ");
                    }
                }else if(gameBoard[i][j].getObstacle().getClass().equals(Gold.class) == true)
                {
                    System.out.print(" G ");
                }else if(gameBoard[i][j].getObstacle().getClass().equals(Wumpus.class) == true)
                {
                    System.out.print(" W ");
                }else if(gameBoard[i][j].getObstacle().getClass().equals(Pit.class) == true)
                {
                    System.out.print(" P ");
                }
                System.out.print(" | ");
            }
            System.out.println();
            System.out.print(" ---------------------------------");
            System.out.println();
        }
    }
}
