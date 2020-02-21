/**
 * The <code>Organism</code> simulates a node representing an Organism
 *
 * @author Jonathan Yap
 *    e-mail: jonathan.yap@stonybrook.edu
 *    Stony Brook ID: 112946304
 *    CSE214.R04
 **/
public class OrganismNode {
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;
    private OrganismNode parent;
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;

    /**
     * Constructs an empty OrganismNode object.
     * <dt>Postcondition</dt>
     *      An empty OrganismNode object has been created.
     */
    public OrganismNode() {
        left = null;
        right = null;
        middle = null;
        name = null;
        parent = null;
    }

    /**
     * Constructs an OrganismNode object with initialized name, isPlant,
     * isHerbivore, and isCarnivore.
     * @param name The name of the OrganismNode object
     * @param isPlant A boolean representing whether the object is a plant.
     * @param isHerbivore A boolean representing whether the object is an
     *                    herbivore
     * @param isCarnivore A boolean representing whether the object is a
     *                    carnivore.
     * <dt>Postcondition</dt>
     *      An OrganismNode object has been instantiated with the given
     *      parameters.
     */
    public OrganismNode(String name, boolean isPlant, boolean isHerbivore,
                        boolean isCarnivore) {
        this.name = name;
        this.isPlant = isPlant;
        this.isHerbivore = isHerbivore;
        this.isCarnivore = isCarnivore;
        parent = null;
    }

    /**
     * <dt>Preconditions</dt>
     *      The node is not a plant, there are positions available, and the
     *      prey corresponds to the node's diet.
     * Adds prey to an OrganismNode object.
     * @param preyNode The node to be added as prey.
     * @throws PositionNotAvailableException
     * @throws IsPlantException
     * @throws DietMismatchException
     * <dt>Postcondition</dt>
     *      The preyNode has been added as a prey to the given object.
     */
    public void addPrey(OrganismNode preyNode) throws
      PositionNotAvailableException, IsPlantException, DietMismatchException{
        if (this.isPlant == true) {
            throw new IsPlantException();
        }
        if (this.isHerbivore() && !this.isCarnivore() && !preyNode.isPlant()) {
            throw new DietMismatchException();
        }
        if (!this.isHerbivore() && preyNode.isPlant()) {
            throw new DietMismatchException();
        }
        if (this.left == null) {
            this.left = preyNode;
            this.left.parent = this;
        }
        else if (this.middle == null) {
            this.middle = preyNode;
            this.middle.parent = this;
        }
        else if (this.right == null) {
            this.right = preyNode;
            this.right.parent = this;
        }
        else {
            throw new PositionNotAvailableException();
        }
    }

    /**
     * Returns the left node of the object.
     * @return the left node of the object.
     */
    public OrganismNode getLeft() {
        return left;
    }

    /**
     * Returns the middle node of the object.
     * @return the middle node of the object.
     */
    public OrganismNode getMiddle() {
        return middle;
    }
    public OrganismNode getRight() {
        return right;
    }

    /**
     * Returns the parent node of the object.
     * @return the parent node of the object.
     */
    public OrganismNode getParent() {
        return parent;
    }

    /**
     * Sets the left of node of the object to the parameter OrganismNode
     * @param left The node to be set to the left of the object.
     */
    public void setLeft(OrganismNode left) {
        this.left = left;
    }

    /**
     * Sets the middle of node of the object to the parameter OrganismNode
     * @param middle The node to be set to the middle of the object.
     */
    public void setMiddle(OrganismNode middle) {
        this.middle = middle;
    }

    /**
     * Sets the right of node of the object to the parameter OrganismNode
     * @param right The node to be set to the right of the object.
     */
    public void setRight(OrganismNode right) {
        this.right = right;
    }

    /**
     * Returns the name of the OrganismNode
     * @return The name of the Organism
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the boolean value of whether the Organism is a plant or not
     * @return the boolean value of whether the Organism is a plant or not
     */
    public boolean isPlant() {
        return isPlant;
    }

    /**
     * Returns the boolean value of whether the Organism is an herbivore or not
     * @return the boolean value of whether the Organism is an herbivore or not
     */
    public boolean isHerbivore() {
        return isHerbivore;
    }
    /**
     * Returns the boolean value of whether the Organism is a carnivore or not
     * @return the boolean value of whether the Organism is a carnivore or not
     */
    public boolean isCarnivore() {
        return isCarnivore;
    }

    /**
     * Sets the object as an herbivore
     * <dt>Postcondition</dt>
     *      The object has been set as an herbivore
     */
    public void setHerbivore() {
        this.isHerbivore = true;
    }

    /**
     * Set the object as a carnivore
     * <dt>Postcondition</dt>
     *      The object has been set as an carnivore
     */
    public void setCarnivore() {
        this.isCarnivore = true;
    }
}
