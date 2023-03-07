public class Controller {
    private final RobotLawnmower robotLawnmower = new RobotLawnmower();

    public void printAreaOfGarden(int[][] areaOfGarden) {
        try {
            for (int i = 0; i < areaOfGarden.length; i++) {
                for (int j = 0; j < areaOfGarden[i].length; j++) {
                    System.out.print(areaOfGarden[i][j] + " ");
                }
                System.out.println();
            }
            Thread.sleep(1000); //waits 1 second
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStartPositionOfRobotLawnmower(int[][] areaOfGarden) {
        int[] startPosition = robotLawnmower.getStartPosition();
        int startRow = startPosition[0];
        int startColumn = startPosition[1];
        areaOfGarden[startRow][startColumn] = robotLawnmower.getRLSign();
    }

    public int[] cutTheGrass(int[] startPosition, int[][] areaOfGarden) {
        int[] endPosition = new int[2];
        while (countTheUncutFields(areaOfGarden) != 0) {
            int startRow = startPosition[0];
            int startColumn = startPosition[1];
            // It tries to go to the right, if it can and the grass is not cut in the next field,
            // it moves one to the right and the grass in the start position gets cut.
            if (canMoveToTheRight(startColumn, areaOfGarden[startRow])) {
                endPosition = new int[]{startRow, startColumn + 1};
                areaOfGarden[startRow][startColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                startPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
                // If it cannot move to the right, it tries to go down if there is a field there
                // and if the start row does not contain uncut area.
            } else if (canMoveDown(areaOfGarden, startRow, startColumn)) {
                endPosition = new int[]{startRow + 1, startColumn};
                areaOfGarden[startRow][startColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                startPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
            } else {
                // if it cannot move to the right or down, it goes to the left
                endPosition = new int[]{startRow, startColumn - 1};
                areaOfGarden[startRow][startColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                startPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
            }
        }
        return endPosition;
    }

    private boolean canMoveDown(int[][] areaOfGarden, int startRow, int startColumn) {
        return areaOfGarden.length - 1 > startRow
                && areaOfGarden[startRow + 1].length - 1 >= startColumn
                && !containsTheCurrantRowUncutField(areaOfGarden, startRow);
    }

    private static boolean canMoveToTheRight(int startColumn, int[] areaOfGarden) {
        return startColumn < areaOfGarden.length - 1 && areaOfGarden[startColumn + 1] != 1;
    }

    private int countTheUncutFields(int[][] areaOfGarden) {
        int numberOfUncutFields = 0;
        for (int[] row : areaOfGarden) {
            for (int field : row) {
                if (field == 0) {
                    numberOfUncutFields++;
                }
            }
        }
        return numberOfUncutFields;
    }

    private boolean containsTheCurrantRowUncutField(int[][] areaOfGarden, int indexOfTheRow) {
        int[] row = areaOfGarden[indexOfTheRow];
        int numberOfUncutFields = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0) {
                numberOfUncutFields++;
            }
        }
        return numberOfUncutFields != 0;
    }

    public void goToTheStart(int[][] areaOfGarden, int[] startPosition) {
        int[] endPosition;
        while (areaOfGarden[0][0] != robotLawnmower.getRLSign()) {
            int startRow = startPosition[0];
            int startColumn = startPosition[1];
            if (startColumn != 0) {
                endPosition = new int[]{startRow, startColumn - 1};
            } else {
                endPosition = new int[]{startRow - 1, startColumn};
            }
            areaOfGarden[startRow][startColumn] = 1;
            areaOfGarden[endPosition[0]][endPosition[1]] = 3;
            startPosition = endPosition;
            System.out.println("The robotic lawnmower is moving to the start position:");
            printAreaOfGarden(areaOfGarden);
        }
    }
}


