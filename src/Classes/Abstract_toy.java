package Classes;

public abstract class Abstract_toy {
    private static int nextId = 1;
    private final int id;
    private String name;

    protected Abstract_toy(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Toy name cannot be empty");
        }
        this.id = nextId++;
        this.name = name.trim();
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Toy name cannot be empty");
        }
        this.name = name.trim();
    }
}
