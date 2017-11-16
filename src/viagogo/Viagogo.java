package viagogo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Viagogo {

    static Event[][] eventGrid;

    static List<Event> listOfEvents;

    final static int gridBoundaryPositive = 10;
    final static int gridBoundaryNegative = -10;
    final static int terminationNumber = 11;
    final static int mappingUserInputToGrid = 10;
    final static int outputLimit = 4;
    static Integer xCo = null;
    static Integer yCo = null;
    static boolean terminationFlag;

    public static void main(String[] args) {
        eventGrid = new Event[20][20];
        listOfEvents = new ArrayList<>();
        terminationFlag = false;
        xCo = null;
        yCo = null;
        Scanner in = new Scanner(System.in);

        populateGrid();
        System.out.println("Follow the instructions to find the closest event based on your input or type 11 to exit application.");

        do {
            System.out.println("Type in X coordinate ranging from -10 to 10. Or type in 11 to exit application.");

            while (xCo == null) {
                getUserXInput(in);
            }
            System.out.println("Type in Y coordinate ranging from -10 to 10. Or type in 11 to exit application");
            while (yCo == null) {
                getUserYInput(in);
            }
            calculateManhattanDistance(xCo + mappingUserInputToGrid, yCo + mappingUserInputToGrid);
            outputDistance(xCo, yCo);
            xCo = null;
            yCo = null;
        } while (!terminationFlag);
    }

    //Gets the X coordinate from the user
    public static void getUserXInput(Scanner in) {
        try {
            xCo = Integer.parseInt(in.next());
            if (xCo < gridBoundaryNegative || xCo > terminationNumber) {
                xCo = null;
                System.out.println("Bad Input. X coordinate can only be in the range of -10 to 10");
            }
            if (xCo == terminationNumber) {
                terminationFlag = true;
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Bad Input. Type a number ranging from -10 to 10");
        }
    }

    //Outputs the 5 closest events to the console as per the instructions.
    //Sorts the array based on the distance from the users input.
    public static void outputDistance(int x, int y) {
        listOfEvents.sort(new EventComporator());
        System.out.println("Closest events to " + x + "," + y);
        for (int i = 0; i < listOfEvents.size(); i++) {
            System.out.println(listOfEvents.get(i).toString());
            if (i == outputLimit) {
                break;
            }
        }
    }

    //This populates the grid. You can adjust the variable to increase/decrease
    //the amount of events in the world.
    private static void populateGrid() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int randomEventToPutInGrid = (int) (Math.random() * 50 + 0);
                if (randomEventToPutInGrid % 5 == 0) {
                    eventGrid[i][j] = randomSeed(i, j);
                    eventGrid[i][j].setX(i);
                    eventGrid[i][j].setY(j);
                    listOfEvents.add(eventGrid[i][j]);
                }
            }
        }
    }

    //This produces a random price and ticket for each Event object.
    //The id used is the concatenated version of the coordinates to ensure uniqueness.
    //The IDs are unique as only 1 event can be in 1 location.
    private static Event randomSeed(int i, int j) {
        String id = i + "" + j;
        double price = (Math.random() * 50 + 1);
        DecimalFormat df = new DecimalFormat("#.00");
        price = Double.valueOf(df.format(price));
        int tickets = (int) (Math.random() * 50 + 0);

        return new Event(id, price, tickets);
    }

    //This method takens in the coordinates that the user has chosen.
    //It then calculates the distance from this point to each event.
    //After calculating, it updates the distance variable in the Event object
    private static void calculateManhattanDistance(int x, int y) {
        listOfEvents.stream().forEach((Event posOfEvent) -> {
            int xPositionOfEvent = posOfEvent.getX();
            int yPositionOfEvent = posOfEvent.getY();
            int distance = Math.abs(x - xPositionOfEvent) + Math.abs(y - yPositionOfEvent);
            eventGrid[xPositionOfEvent][yPositionOfEvent].setDistance(distance);
        });
    }

    //Gets the users Y coodinate input.
    private static void getUserYInput(Scanner in) {
        try {
            yCo = Integer.parseInt(in.next());
            if (yCo < gridBoundaryNegative || yCo > terminationNumber) {
                yCo = null;
                System.out.println("Bad Input. Y coordinate can only be in the range of -10 to 10");
            }
            if (yCo == terminationNumber) {
                terminationFlag = true;
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Bad Input. Type a number ranging from -10 to 10");
        }
    }
}
