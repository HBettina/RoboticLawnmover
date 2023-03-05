public class Controller {
    private RobotLawnmover robotLawnmover = new RobotLawnmover();
    private final Garden garden = new Garden();
    private int[][] areaOfGarden = garden.getAreaOfGarden();

    public Garden createGarden() {
        Garden garden1 = new Garden();
        return garden1;
    }

    public void printAreaOfGarden(int[][] areaOfGarden) {
        for (int i = 0; i < areaOfGarden.length; i++) {
            for (int j = 0; j < areaOfGarden[i].length; j++) {
                System.out.print(areaOfGarden[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setStartPositionOfRobotLawnmover(int[][] areaOfGarden, int[] startPosition) {
        int startRow = startPosition[0];
        int startColumn = startPosition[1];
        areaOfGarden[startRow][startColumn] = robotLawnmover.getRLSign();
    }

    public int[] cutTheGrass(int[] startPosition, int[][] areaOfGarden) {
        int[] endPosition = new int[2];
        for (int i = 0; countTheNotCuttedAreas(areaOfGarden) != 0; i++) {
            int startRow = startPosition[0];
            int startColumn = startPosition[1];
            // It tries to go to the right, if it can and the grass is not cutted in the next place,
            // it moves one to the right and the grass in the start position is cutted.
            if (startColumn < areaOfGarden[startRow].length-1 && areaOfGarden[startRow][startColumn + 1] != 1) {
                endPosition = new int[]{startRow, startColumn + 1};
                areaOfGarden[startRow][startColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                startPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
                // If it cannot go to the right, it tries go down if there is area of the garden down
                // and if the start row does not contain noncutted area
            } else if (areaOfGarden.length - 1 > startRow && areaOfGarden[startRow + 1].length - 1 >= startColumn
                    && !containsTheCurrantRowNotCuttedArea(areaOfGarden, startRow)
                    && areaOfGarden.length - 1 > startRow) {
                endPosition = new int[]{startRow + 1, startColumn};
                areaOfGarden[startRow][startColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                startPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
            } else {
                // if it cannot go to the right and down, it goes to the left
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
    private int countTheNotCuttedAreas(int[][] areaOfGarden) {
        int numberOfNotCuttedArea = 0;
        for (int[] row : areaOfGarden) {
            for (int element : row) {
                if (element == 0) {
                    numberOfNotCuttedArea++;
                }
            }
        }
        return numberOfNotCuttedArea;
    }
    private boolean containsTheCurrantRowNotCuttedArea(int[][] areaOfGarden, int indexOfTheRow){
        int[] row = areaOfGarden[indexOfTheRow];
        int numberOfNotCuttedArea = 0;
        for (int i = 0; i < row.length; i++) {
            if(row[i] == 0) {
                numberOfNotCuttedArea++;
            }
        }
        return numberOfNotCuttedArea != 0;
    }
    public void goToTheStart(int[][] areaOfGarden, int[] startPosition) {
        int[] endPosition = new int[2];
        for (int i = 0; areaOfGarden[0][0] != robotLawnmover.getRLSign(); i++) {
            int startRow = startPosition[0];
            int startColumn = startPosition[1];
            if(startColumn != 0) {
                endPosition = new int[]{startRow, startColumn - 1};
                areaOfGarden[startRow][startColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                startPosition = endPosition;
                System.out.println("The robotic lawnmover is going to the start position:");
                printAreaOfGarden(areaOfGarden);
            } else {
                endPosition = new int[]{startRow - 1, startColumn};
                areaOfGarden[startRow][startColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                startPosition = endPosition;
                System.out.println("The robotic lawnmover is going to the start position:");
                printAreaOfGarden(areaOfGarden);
            }
        }
    }
}
