package Classes;

public abstract class Abstract_toy {
    protected int id = 0;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public Abstract_toy(String name) {
        this.id += 1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
