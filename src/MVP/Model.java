package MVP;

import Classes.Toy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private final List<Toy> toyList;
    private final List<String> prizePull;
    private final Random random;
    private final Path prizeDatabasePath;

    public Model() {
        this(Path.of("src", "Prize_database", "Database.txt"));
    }

    public Model(Path prizeDatabasePath) {
        toyList = new ArrayList<>();
        prizePull = new ArrayList<>();
        random = new Random();
        this.prizeDatabasePath = prizeDatabasePath;
    }

    public List<String> getPrizePull() {
        return prizePull;
    }

    public List<Toy> getToyList() {
        return toyList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Toy toy : toyList) {
            result.append(String.format(
                    "Name: %s, Quantity: %d, Draw probability: %.2f%%",
                    toy.getName(), toy.getQuantity(), toy.getProbability()));
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    public void addToy(Toy toy) {
        if (toy == null) {
            throw new IllegalArgumentException("Toy cannot be null");
        }
        if (toy.getQuantity() <= 0) {
            throw new IllegalArgumentException("Toy quantity must be greater than zero");
        }
        if (toy.getProbability() < 0) {
            throw new IllegalArgumentException("Draw probability cannot be negative");
        }
        toyList.add(toy);
    }

    public Toy drawPrize() {
        List<Toy> availableToys = toyList.stream()
                .filter(toy -> toy.getQuantity() > 0 && toy.getProbability() > 0)
                .toList();

        if (availableToys.isEmpty()) {
            throw new IllegalStateException("No toys are available for the draw");
        }

        double totalWeight = availableToys.stream()
                .mapToDouble(toy -> toy.getProbability() * toy.getQuantity())
                .sum();

        double token = random.nextDouble(totalWeight);
        double cumulativeWeight = 0;

        for (Toy toy : availableToys) {
            cumulativeWeight += toy.getProbability() * toy.getQuantity();
            if (token < cumulativeWeight) {
                addToPrizePull(toy);
                return toy;
            }
        }

        throw new IllegalStateException("Failed to select a prize");
    }

    public void setProbability(String toyName, float newProbability) {
        if (newProbability < 0) {
            throw new IllegalArgumentException("Draw probability cannot be negative");
        }

        Toy toy = findToy(toyName);
        toy.setProbability(newProbability);
    }

    public void addToPrizePull(Toy toy) {
        if (toy == null) {
            throw new IllegalArgumentException("Toy cannot be null");
        }
        if (toy.getQuantity() <= 0) {
            throw new IllegalStateException("Toy is out of stock");
        }

        toy.setQuantity(toy.getQuantity() - 1);
        prizePull.add(toy.getName());
        toyList.removeIf(item -> item.getQuantity() <= 0);
    }

    public boolean claimPrize(String prizeName) {
        if (!prizePull.remove(prizeName)) {
            return false;
        }

        try {
            Path parent = prizeDatabasePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(
                    prizeDatabasePath,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                writer.write(prizeName);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            prizePull.add(prizeName);
            throw new IllegalStateException("Could not save the claimed prize", e);
        }
    }

    private Toy findToy(String toyName) {
        return toyList.stream()
                .filter(toy -> toy.getName().equalsIgnoreCase(toyName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Toy not found: " + toyName));
    }
}
