package MVP;

import Classes.Toy;

import java.util.List;

public class Presenter {
    private final IView view;
    private final Model model;

    public Presenter(IView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void menuProcessing() {
        boolean running = true;

        while (running) {
            String userInput = view.menu();

            try {
                switch (userInput) {
                    case "1" -> addToyToModel(view.creating_the_toy());
                    case "2" -> System.out.printf("You won: %s!%n", startDraw());
                    case "3" -> System.out.print(claimPrize());
                    case "4" -> System.out.print(model);
                    case "5" -> changeProbability(view.change_probability());
                    case "6" -> showPrizePull();
                    case "7" -> running = false;
                    default -> System.out.println("Invalid menu option!");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addToyToModel(String toyInfo) {
        String[] parts = toyInfo.toLowerCase().split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Use: name, quantity, probability");
        }

        String name = parts[0].trim();
        int quantity = Integer.parseInt(parts[1].trim());
        float probability = Float.parseFloat(parts[2].trim());

        model.addToy(new Toy(name, quantity, probability));
    }

    private void changeProbability(String input) {
        String[] parts = input.toLowerCase().split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Use: name, probability");
        }

        model.setProbability(parts[0].trim(), Float.parseFloat(parts[1].trim()));
    }

    private String startDraw() {
        Toy prize = model.drawPrize();
        return prize.getName();
    }

    private String claimPrize() {
        String prize = view.prize_name().trim();
        if (model.claimPrize(prize)) {
            return "Prize claimed: " + prize + System.lineSeparator();
        }
        return "This prize is not waiting for collection." + System.lineSeparator();
    }

    private void showPrizePull() {
        List<String> prizes = model.getPrizePull();
        if (prizes.isEmpty()) {
            System.out.println("There are no unclaimed prizes yet.");
            return;
        }

        System.out.println("Unclaimed prizes: " + String.join(", ", prizes));
    }
}
