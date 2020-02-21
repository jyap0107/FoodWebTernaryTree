/**
 * The <code>OrganismTree</code> simulates a food web of Organism Nodes
 *
 * @author Jonathan Yap
 *    e-mail: jonathan.yap@stonybrook.edu
 *    Stony Brook ID: 112946304
 *    CSE214.R04
 **/
public class OrganismTree {
    private OrganismNode root;
    private OrganismNode cursor;
    public static int i = 0;

    /**
     * Constructs the apex predator, the root of the OrganismTree
     * @param apexPredator the root of the OrganismTree
     * <dt>Postcondition</dt>
     *     The root of the OrganismTree has been instantiated
     */
    public OrganismTree(OrganismNode apexPredator) {
        root = apexPredator;
        cursor = apexPredator;
    }

    /**
     * Resets the cursor to the root.
     * <dt>Postcondition</dt>
     *      The cursor is set to the root.
     */
    public void cursorReset() {
        cursor = root;
    }

    /**
     * Returns the cursor
     * @return the cursor
     */
    public OrganismNode getCursor() {
        return cursor;
    }

    /**
     * <dt>Precondition</dt>
     *      name references a direct child of the cursor
     * Moves the cursor to the designated child.
     * @param name The name of the child to move the cursor to.
     * @throws IllegalArgumentException
     */
    public void moveCursor(String name) throws IllegalArgumentException {
        if (cursor.getLeft().getName().equals(name)) {
            cursor = cursor.getLeft();
        }
        else if (cursor.getMiddle().getName().equals(name)) {
            cursor = cursor.getMiddle();
        }
        else if (cursor.getRight().getName().equals(name)) {
            cursor = cursor.getRight();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Lists the direct prey of the Organism at the cursor
     * @return A String representing the prey.
     * @throws IsPlantException
     */
    public String listPrey() throws IsPlantException {
        if (cursor.isPlant()) {
            throw new IsPlantException();
        }
        String preys = cursor.getName();
        if (cursor.getLeft() != null) {
            preys += " -> " + cursor.getLeft().getName();
        }
        if (cursor.getMiddle() != null) {
            preys += " , " + cursor.getMiddle().getName();
        }
        if (cursor.getRight() != null) {
            preys += " , " + cursor.getRight().getName();
        }
        return preys;
    }

    /**
     * Lists the food chain from the root to the OrganismNode at the cursor
     * @return A string representing the food chain to the cursor node.
     */
    public String listFoodChain() {
        OrganismNode cursor2 = cursor;
        String s = cursor.getName();
        while (cursor2.getParent() != null) {
            cursor2 = cursor2.getParent();
            s = cursor2.getName() + " => " + s;
        }
        return s;
    }

    /**
     * Prints the OrganismTree with a specific starting indentation
     * <dt>Postcondition</dt>
     *      The OrganismTree has been printed
     */
    public void printOrganismTree() {
        printPreorder(cursor, 0);
    }
    /**
     * Prints the OrganismTree
     * @param root The root to start the printing of the tree
     * @param indent The number of indentations to have per line
     * <dt>Postcondition</dt>
     *      The OrganismTree has been printed
     */
    public void printPreorder(OrganismNode root, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("     ");
        }
        if (!root.isPlant()) {
            System.out.println("|- " + root.getName());
        }
        else {
            System.out.println("- " + root.getName());
        }
        if (root.getLeft() != null) {
            printPreorder(root.getLeft(), indent + 1);
        }
        if (root.getMiddle() != null) {
            printPreorder(root.getMiddle(), indent + 1);
        }
        if (root.getRight() != null) {
            printPreorder(root.getRight(), indent + 1);
        }
    }

    /**
     * Puts all plants under the OrganismNode of the cursor into a String
     * @return A String of all plants under the OrganismNode of the cursor
     */
    public String listAllPlants() {
        i = 0;
        String[] plantList = new String[100];
        addPlants(plantList, cursor);
        String plants = "";
        for (int j = 0; j < i; j++) {
            if (j == 0) {
                plants += plantList[j];
            }
            else {
                plants += ", " + plantList[j];
            }
        }
        return plants;
    }

    /**
     * Adds plants to a plantList of a tree
     * @param plantList An Array holding the names of plants of the tree
     * @param root The root of the tree
     */
    public void addPlants(String[] plantList, OrganismNode root) {
        if (root.isPlant() && i <= 100) {
            plantList[i] = root.getName();
            //System.out.println("At index " + i + ": " + plantList[i]);
            i++;
        }
//        else {
//            System.out.println("- " + root.getName());
//        }
        if (root.getLeft() != null) {
            addPlants(plantList, root.getLeft());
        }
        if (root.getMiddle() != null) {
            addPlants(plantList, root.getMiddle());
        }
        if (root.getRight() != null) {
            addPlants(plantList, root.getRight());
        }
    }

    /**
     * Searches through the tree to see if an organism has been repeated within
     * it.
     * @param root The node to begin searching at
     * @param target The String to check duplicates for
     * @return A boolean value representing whether or not the OrganismNode
     * has a duplicate
     */
    public boolean findName(OrganismNode root, String target) {
        if (root.getName().equals(target)) {
            return true;
        }
        if (root.getLeft() != null) {
            //System.out.println(root.getLeft().getName() + " " + target);
            if (root.getLeft().getName().equals(target)) {
                return true;
            }
            else {
                findName(root.getLeft(), target);
            }
        }
        if (root.getMiddle() != null) {
            if (root.getMiddle().getName().equals(target)) {
                return true;
            }
            else {
                findName(root.getMiddle(), target);
            }
        }
        if (root.getRight() != null) {
            if (root.getRight().getName().equals(target)) {
                return true;
            }
            else {
                findName(root.getRight(), target);
            }
        }
        return false;
    }

    /**
     * Adds an animal child to the node at the cursor.
     * @param name The name of the animal child
     * @param isHerbivore A boolean representing if the organism is an herbivore
     * @param isCarnivore A boolean representing if the organism is a carnivore.
     * @throws IllegalArgumentException
     * @throws PositionNotAvailableException
     * <dt>Postcondition</dt>
     *      The node has an animal child added, if there is room, it
     *      is not a duplicate, and it matches the parent diet.
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean
      isCarnivore) throws IllegalArgumentException,
            PositionNotAvailableException {
        if (findName(root, name)) {
            throw new IllegalArgumentException();
        }
        if (cursor.getRight() != null) {
            throw new PositionNotAvailableException();
        }
        else {
            OrganismNode newNode = new OrganismNode(name, false,
              isHerbivore, isCarnivore);
            try {
                cursor.addPrey(newNode);
                System.out.println(name + " successfully added as a " +
                        "child to " + cursor.getName());
            }
            catch(IsPlantException e) {
                System.out.println("Error: Cannot add prey to a plant.");
            }
            catch (DietMismatchException e) {
                System.out.println("Error: Cannot add an animal as a prey to " +
                        "an herbivore.");
            }
        }
    }

    /**
     * Adds a plant child to the node at the cursor
     * @param name The name of the plant to add
     * @throws IllegalArgumentException
     * @throws PositionNotAvailableException
     * <dt>Postcondition</dt>
     *     A plant child has been added to the node, if there is room, it is
     *     not a duplicate, and it matches the parent diet.
     */
    public void addPlantChild(String name) throws IllegalArgumentException,
            PositionNotAvailableException {
        if (findName(root, name)) {
            throw new IllegalArgumentException();
        }
        if (cursor.getRight() != null) {
            throw new PositionNotAvailableException();
        }
        else {
            try {
                OrganismNode newNode = new OrganismNode(name, true, false,
                  false);
                cursor.addPrey(newNode);
                System.out.println(name + " successfully added as a " +
                        "child to " + cursor.getName());
            }
            catch(IsPlantException e) {
                System.out.println("Error: Cannot add prey to a plant.");
            }
            catch(DietMismatchException e) {
                System.out.println("Error: Cannot add plant as a prey to a " +
                        "carnivore.");
            }
        }
    }

    /**
     * <dt>Precondition</dt>
     *      name references a direct child of the cursor
     * Removes a given child from the node represented by the cursor
     * @param name The name of the child to remove
     * @throws IllegalArgumentException
     * <dt>Postcondition</dt>
     *      The child has been removed from the node, if it has been found.
     */
    public void removeChild(String name) throws IllegalArgumentException {
        if (cursor.getLeft() != null) {
            if (cursor.getLeft().getName().equals(name)) {
                cursor.setLeft(null);
                if (cursor.getMiddle() != null) {
                    cursor.setLeft(cursor.getMiddle());
                }
                if (cursor.getRight() != null) {
                    cursor.setMiddle(cursor.getRight());
                    cursor.setRight(null);
                }
            }
        }
        if (cursor.getMiddle() != null) {
            if (cursor.getMiddle().getName().equals(name)) {
                cursor.setMiddle(null);
                if (cursor.getRight() != null) {
                    cursor.setMiddle(cursor.getRight());
                    cursor.setRight(null);
                }
            }
        }
        if (cursor.getRight() != null) {
            if (cursor.getRight().getName().equals(name)) {
                cursor.setRight(null);
            }
        }
    }

}
