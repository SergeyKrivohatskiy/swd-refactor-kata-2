package task3;

import java.util.List;

public class Coordinates {
    private Point x;
    private Point y;
    private Direction direction;
    private List<Obstacle> obstacles;

    public Coordinates(Point xValue, Point yValue, Direction directionValue,
	    List<Obstacle> obstaclesValue) {
	x = xValue;
	y = yValue;
	direction = directionValue;
	obstacles = obstaclesValue;
    }

    public Point getX() {
	return x;
    }

    public Point getY() {
	return y;
    }

    public void setDirection(Direction value) {
	direction = value;
    }

    public Direction getDirection() {
	return direction;
    }

    public void setObstacles(List<Obstacle> value) {
	obstacles = value;
    }

    @Override
    public String toString() {
	return String.format("Coordinates (%d, %d)", x.getLocation(),
		y.getLocation());
    }

    public boolean move(int d) {
	switch (direction) {
	case EAST:
	    return move(d, 0);
	case NORTH:
	    return move(0, d);
	case SOUTH:
	    return move(0, -d);
	case WEST:
	    return move(-d, 0);
	default:
	    throw new RuntimeException("Unprocesser direction!");
	}
    }

    public boolean isThereAnyObstacleInFront() {
	if (move(1)) {
	    move(-1);
	    return false;
	}
	return true;
    }

    public boolean move(int dx, int dy) {
	if (!IsThereAnyObstacle(x.getLocation() + dx, y.getLocation() + dy)) {
	    x.setLocation(x.getLocation() + dx);
	    y.setLocation(y.getLocation() + dy);
	    return true;
	}
	return false;
    }

    private boolean IsThereAnyObstacle(int x, int y) {
	for (Obstacle obstacle : obstacles) {
	    if (obstacle.x == x && obstacle.y == y) {
		return true;
	    }
	}
	return false;
    }

    public void turnRight() {
	switch (direction) {
	case EAST:
	    direction = Direction.SOUTH;
	    break;
	case NORTH:
	    direction = Direction.EAST;
	    break;
	case SOUTH:
	    direction = Direction.WEST;
	    break;
	case WEST:
	    direction = Direction.NORTH;
	    break;
	}
    }

    public void turnLeft() {
	switch (direction) {
	case EAST:
	    direction = Direction.NORTH;
	    break;
	case NORTH:
	    direction = Direction.WEST;
	    break;
	case SOUTH:
	    direction = Direction.EAST;
	    break;
	case WEST:
	    direction = Direction.SOUTH;
	    break;
	}
    }

}