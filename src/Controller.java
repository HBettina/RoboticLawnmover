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

    public void setBeginnPositionOfRobotLawnmover(int[][] areaOfGarden, int[] beginnPosition) {
        int beginnRow = beginnPosition[0];
        int beginnColumn = beginnPosition[1];
        areaOfGarden[beginnRow][beginnColumn] = robotLawnmover.getRLSign();
    }

    public int[] cutTheGrass(int[] beginnPosition, int[][] areaOfGarden) {
        int[] endPosition = new int[2];
        for (int i = 0; countTheNotCuttedAreas(areaOfGarden) != 0; i++) {
            int beginnRow = beginnPosition[0];
            int beginnColumn = beginnPosition[1];
            // It tries to go to the right, if it can and the grass is not cutted in the next place,
            // it moves one to the right and the grass in the beginnposition is cutted.
            if (beginnColumn < areaOfGarden[beginnRow].length-1 && areaOfGarden[beginnRow][beginnColumn + 1] != 1) {
                endPosition = new int[]{beginnRow, beginnColumn + 1};
                areaOfGarden[beginnRow][beginnColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                beginnPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
                // If it cannot go to the right, it tries go down if there is area of the garden down
                // and if the beginn row does not contain noncutted area
            } else if (areaOfGarden.length - 1 > beginnRow && areaOfGarden[beginnRow + 1].length - 1 >= beginnColumn
                    && !containsTheCurrantRowNotCuttedArea(areaOfGarden, beginnRow)
                    && areaOfGarden.length - 1 > beginnRow) {
                endPosition = new int[]{beginnRow + 1, beginnColumn};
                areaOfGarden[beginnRow][beginnColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                beginnPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
            } else {
                // if it cannot go to the right and down, it goes to the left
                endPosition = new int[]{beginnRow, beginnColumn - 1};
                areaOfGarden[beginnRow][beginnColumn] = 1;
                areaOfGarden[endPosition[0]][endPosition[1]] = 3;
                beginnPosition = endPosition;
                System.out.println("The state of the garden:");
                printAreaOfGarden(areaOfGarden);
            }
        }
        return endPosition;
}
    public int countTheNotCuttedAreas(int[][] areaOfGarden) {
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
    public boolean containsTheCurrantRowNotCuttedArea(int[][] areaOfGarden, int indexOfTheRow){
        int[] row = areaOfGarden[indexOfTheRow];
        int numberOfNotCuttedArea = 0;
        for (int i = 0; i < row.length; i++) {
            if(row[i] == 0) {
                numberOfNotCuttedArea++;
            }
        }
        return numberOfNotCuttedArea != 0;
    }


}
