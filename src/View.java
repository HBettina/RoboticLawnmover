public class View {
    Controller controller = new Controller();
    Garden garden = new Garden();
    RobotLawnmower robotLawnmower = new RobotLawnmower();

    public void simulation() {
        // print the area of the garden
        System.out.println("The area of the garden:");
        controller.printAreaOfGarden(garden.getAreaOfGarden());
        // put the robot lawnmover to the start position (top left) and print the garden
        controller.setStartPositionOfRobotLawnmower(garden.getAreaOfGarden());
        System.out.println("Garden with the start position:");
        controller.printAreaOfGarden(garden.getAreaOfGarden());
        // cutting of the grass
        int[] endPosition = controller.cutTheGrass(robotLawnmower.getStartPosition(), garden.getAreaOfGarden());
        System.out.println("The grass is cut on the whole garden.");
        // the robot lawnmower moves back to the start position
        controller.goToTheStart(garden.getAreaOfGarden(), endPosition);
        System.out.println("The robotic lawnmower reached the start position.");

    }
}
