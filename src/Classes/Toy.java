package Classes;

public class Toy extends Abstract_toy {
    private int quantity;
    private float probability;

    public Toy(String name, int quantity, float probability) {
        super(name);
        this.probability = probability;
        this.quantity = quantity;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
