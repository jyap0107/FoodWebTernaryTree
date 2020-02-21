

import java.sql.SQLOutput;
import java.util.Scanner;

public class FoodPyramid {
    /**
     * Constructs an empty FoodPyramid Object
     */
    public FoodPyramid() {

    }
    private OrganismTree tree;

    public static void main(String[] args) {
        FoodPyramid pyramid = new FoodPyramid();
        boolean stop = false;
        boolean stopApex = false;
        boolean stopEat = false;
        Scanner in = new Scanner(System.in);
        String apex = "";
        String eat = "";
        String option = "";
        String input = "";
        while (!stopApex) {
            System.out.println("What is the name of the apex Predator?");
            apex = in.nextLine();
            if (apex.isEmpty()) {
                System.out.println("Invalid input.");
            }
            else {
                stopApex = true;
            }
        }
        while (!stopEat) {
            System.out.println("Is the organism an herbivore / a carnivore / " +
                    "an omnivore? (H / C / O): ");
            eat = in.next().toUpperCase();
            if ((eat.equals("H")) || (eat.equals("C")) || (eat.equals("O"))) {
                stopEat = true;
            }
            else {

                System.out.println("Invalid input.");
            }
        }
        System.out.println("Constructing Food Pyramid...");
        OrganismNode apexPredator = new OrganismNode(apex, false, false, false);
        if (eat.equals("H")) {
            apexPredator.setHerbivore();
        }
        if (eat.equals("C")) {
            apexPredator.setCarnivore();;
        }
        if (eat.equals("O")) {
            apexPredator.setHerbivore();
            apexPredator.setCarnivore();
        }
        pyramid.tree = new OrganismTree(apexPredator);
        while (!stop) {
            in.nextLine();
            System.out.println("Menu: \n (PC) - Create New Plant Child \n " +
                    "(AC) - Create New Animal Child \n (RC) - Remove Child \n" +
                    " (P) - Print Out Cursor's Prey \n (C) - Print Out Food " +
                    "Chain \n (F) - Print Out Food Pyramid at Cursor \n (LP) " +
                    "- List All Plants Supporting Cursor \n (R) - Reset " +
                    "Cursor to Root \n (M) - Move Cursor to Child \n (Q) " +
                    "- Quit");
            option = in.nextLine().toUpperCase();
            if (option.equals("PC")) {
                System.out.print("What is the name of the organism?: ");
                input = in.nextLine();
                if (input.isEmpty()) {
                    continue;
                }
                else {
                    try {
                        pyramid.tree.addPlantChild(input);
                    }
                    catch(PositionNotAvailableException e) {
                        System.out.println("Position not Available.");
                    }
                    catch(IllegalArgumentException e) {
                        System.out.println("Invalid Input: Organism already " +
                                "exists.");
                    }
                }
            }
            else if (option.equals("AC")) {
                System.out.print("What is the name of the organism?: ");
                input = in.nextLine();
                if (input.isEmpty()) {
                    continue;
                }
                System.out.print("Is the organism an herbivore / a carnivore " +
                        "/ an omnivore? (H / C / O): ");
                eat = in.next();
                if (eat.isEmpty()) {
                    continue;
                }
                boolean herb = false;
                boolean carn = false;
                if (eat.equals("H")) {
                    herb = true;
                }
                else if (eat.equals("C")) {
                    carn = true;
                }
                else if (eat.equals("O")) {
                    herb = true;
                    carn = true;
                }
                else {
                    System.out.println("Invalid input.");
                    continue;
                }
                try {
                    pyramid.tree.addAnimalChild(input, herb, carn);
                }
                catch(PositionNotAvailableException e) {
                    System.out.println("Position not Available.");
                }
                catch(IllegalArgumentException e) {
                    System.out.println("Invalid input: organism already " +
                            "exists.");
                }
            }
            else if (option.equals("RC")) {
                in.nextLine();
                System.out.print("What is the name of the child you want to" +
                        " delete?: ");
                input = in.nextLine();
                try {
                    pyramid.tree.removeChild(input);
                    System.out.println(input + " successfully deleted!");
                }
                catch(IllegalArgumentException e){
                    System.out.println("Invalid argument.");
                }
            }
            else if (option.equals("P")) {
                try {
                    System.out.println(pyramid.tree.listPrey());
                }
                catch(IsPlantException e) {
                    System.out.println("Invalid argument: " +
                      pyramid.tree.getCursor().getName() + " is a plant.");
                }
            }
            else if (option.equals("C")) {
                System.out.println(pyramid.tree.listFoodChain());
            }
            else if (option.equals("F")) {
                pyramid.tree.printOrganismTree();
            }
            else if (option.equals("LP")) {
                System.out.println(pyramid.tree.listAllPlants());
            }
            else if (option.equals("R")) {
                pyramid.tree.cursorReset();
                System.out.println("Cursor successfully reset to the root: " +
                  pyramid.tree.getCursor().getName());
            }
            else if (option.equals("M")) {
                System.out.print("Move to?: ");
                input = in.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Invalid argument");
                    continue;
                }
                try {
                    pyramid.tree.moveCursor(input);
                    System.out.println("Cursor successfully moved to " +
                      pyramid.tree.getCursor().getName());
                }
                catch(IllegalArgumentException e) {
                    System.out.println("Invalid argument: " + input + "is not" +
                      " a direct child of " +
                      pyramid.tree.getCursor().getName());
                }
            }
            else if (option.equals("Q")) {
                stop = true;
            }
            else {
                System.out.println("Invalid Input.");
            }
        }
    }
}
