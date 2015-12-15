package task3;

public class Point {
    private int location;
    private int maxLocation;

    public Point(int locationValue, int maxLocationValue) {
	location = locationValue;
	maxLocation = maxLocationValue + 1;
	location = location % maxLocation;
    }

    public void setLocation(int value) {
	location = (value + maxLocation) % maxLocation;
    }

    public int getLocation() {
	return location;
    }

    public void setMaxLocation(int value) {
	maxLocation = value + 1;
	location = location % maxLocation;
    }

    public int getMaxLocation() {
	return maxLocation - 1;
    }
}