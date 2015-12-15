package task3;

public class Rover {
    private final Coordinates coordinates;

    public Rover(Coordinates coordinates) {
	this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
	return coordinates;
    }

    public boolean receiveSingleCommand(char command) {
	command = Character.toUpperCase(command);
	switch (command) {
	case 'F':
	    return coordinates.move(1);
	case 'B':
	    return coordinates.move(-1);
	case 'L':
	    coordinates.turnLeft();
	    return true;
	case 'R':
	    coordinates.turnRight();
	    return true;

	default:
	    throw new RuntimeException("Unknown command " + command);
	}
    }

    public void receiveCommands(String commands) {
	for (int commandIdx = 0; commandIdx < commands.length(); commandIdx += 1) {
	    if (!receiveSingleCommand(commands.charAt(commandIdx))) {
		break;
	    }
	}
    }

    public String getPosition() {
	if (coordinates.move(1)) {
	    coordinates.move(-1);
	    char directionChar = coordinates.getDirection().toString()
		    .charAt(0);
	    return String.format("%d X %d %c",
		    coordinates.getX().getLocation(), coordinates.getY()
			    .getLocation(), directionChar);
	} else {
	    return " NOK";
	}
    }
}
