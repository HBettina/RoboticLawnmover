public class View {
    Controller controller = new Controller();
    Garden garden = new Garden();
    RobotLawnmover robotLawnmover = new RobotLawnmover();

    public void simulation() {
        controller.createGarden();
        System.out.println("The area of the garden:");
        controller.printAreaOfGarden(garden.getAreaOfGarden());
        controller.setStartPositionOfRobotLawnmover(garden.getAreaOfGarden(), robotLawnmover.getStartPosition());
        System.out.println("Garden with the start position:");
        controller.printAreaOfGarden(garden.getAreaOfGarden());
        int[] endPosition = controller.cutTheGrass(robotLawnmover.getStartPosition(), garden.getAreaOfGarden());
        System.out.println("The grass is cutted on the whole garden.");
        controller.goToTheStart(garden.getAreaOfGarden(), endPosition);
        System.out.println("The robotic lawnmover reached the start position.");

    }
}
