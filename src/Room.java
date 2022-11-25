import java.util.*;

public class Room {
    private Class obstacleType;
    private List<Class<? extends Object>> roomList;
    private Object obstacle;
    private List<String> neighbors;
    private int[] roomIndex;
    private List<String> percepts;

    public Room(Class obstacleType)
    {
        this.obstacleType = obstacleType;
        roomList = new ArrayList<Class <? extends Object>>();
    }

    public Room(int[] roomIndex)
    {
        neighbors = new ArrayList<String>();
        this.roomIndex = roomIndex;
        obstacle = new Empty();
    }

    public void setObstacle(Object obstacle)
    {
        this.obstacle = obstacle;
    }

    public boolean hasObstacle()
    {
        if(obstacle != null)
        {
            return true;
        }
        return false;
    }

    public Object getObstacle()
    {
        return obstacle;
    }

    public int[] getRoomIndex()
    {
        return roomIndex;
    }

    public void setNeighbors(List<Object> neighbors)
    {
        for(Object object : neighbors)
        {
            String name = object.getName();

            switch(name)
            {
                case "Wumpus":
                    this.neighbors.add(name);
                case "Gold":
                    this.neighbors.add(name);
                case "Pit":
                    this.neighbors.add(name);
                case "Empty":
                    this.neighbors.add(name);
            }
        }
    }

    public List<String> getNeighbors()
    {
        return neighbors;
    }

    public void setPercepts()
    {
        for(String name: neighbors)
        {
            switch(name)
            {
                case "Wumpus":
                    percepts.add("S");
                case "Pit":
                    percepts.add("B");
            }
        }
        if(obstacle.getClass().equals(Gold.class))
        {
            percepts.add("g");
        }
    }
}
