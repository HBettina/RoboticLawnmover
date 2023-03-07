public class Garden {
    private final int[][] areaOfGarden;

    public Garden() {
        // The area of the garden is set in the constructor
        // It has 8-12 rows and the length of the rows is between 1 and 10
        // It is filled with 0 (uncut area)
        int numberOfRows = (int) (Math.random() * (13 - 8) + 8);
        int[][] areaOfGarden = new int[numberOfRows][];
        for (int i = 0; i < areaOfGarden.length; i++) {
            int lengthOfTheRow = (int) (Math.random() * 10 + 1);
            areaOfGarden[i] = new int[lengthOfTheRow];
            for (int j = 0; j < lengthOfTheRow; j++) {
                areaOfGarden[i][j] = 0;
            }
        }
        this.areaOfGarden = areaOfGarden;
    }

    public int[][] getAreaOfGarden() {
        return areaOfGarden;
    }
}
