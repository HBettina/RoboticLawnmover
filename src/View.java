public class View {
    Controller controller = new Controller();
    Garden garden = new Garden();
    RobotLawnmover robotLawnmover = new RobotLawnmover();

    public void simulation() {
        controller.createGarden();
        System.out.println("The area of the garden:");
        controller.printAreaOfGarden(garden.getAreaOfGarden());
        controller.setBeginnPositionOfRobotLawnmover(garden.getAreaOfGarden(), robotLawnmover.getBeginnPosition());
        System.out.println("Garden with the start position:");
        controller.printAreaOfGarden(garden.getAreaOfGarden());
        controller.cutTheGrass(robotLawnmover.getBeginnPosition(), garden.getAreaOfGarden());
        System.out.println("The grass is cutted on the whole garden.");

    }
}
