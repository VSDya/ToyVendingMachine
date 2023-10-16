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

    public void menu_processing() {
        boolean process = true;
        while (process) {
            String user_input = view.menu();

            try {
                switch (user_input) {
                    case "1":
                        add_a_toy_to_model(view.creating_the_toy());
                        break;

                    case "2":
                        System.out.printf("Вы выйграли %s!\n", start_a_draw());
                        break;
                    case "3":
                        System.out.printf(get_the_prize());
                        break;
                    case "4":
                        System.out.println(model.toString());
                        break;
                    case "5":
                        new_probability(view.change_probability());
                        break;
                    case "6":
                        prize_pull_info();
                        break;
                    case "7":
                        process = false;
                        break;
                    default:
                        System.out.println("Некорректный ввод!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean add_a_toy_to_model(String toy_info) {
        String[] toy_chunks = toy_info.toLowerCase().split(",");
        try {
            String toy_name = toy_chunks[0].trim();
            int toy_quantity = Integer.parseInt(toy_chunks[1].trim());
            float toy_probability = Float.parseFloat(toy_chunks[2].trim());

            return model.add_toy_to_list(new Toy(toy_name, toy_quantity, toy_probability));

        } catch (RuntimeException e) {
            throw new RuntimeException("Некорректная информация! ");
        }
    }

    private boolean new_probability(String name_and_new_probability) {
        try {
            String[] new_info = name_and_new_probability.toLowerCase().split(",");
            return model.set_new_probability(new_info);

        } catch (RuntimeException e) {
            throw new RuntimeException("Некорректное имя или вероятность!");
        }
    }

    private String start_a_draw() {
        try {
            return model.add_to_prize_pull(model.hold_a_draw(model.getToy_list()));

        } catch (RuntimeException e) {
            throw new RuntimeException("Список игрушек для розыгрыша пуст!");
        }
    }

    private String get_the_prize() {
        String prize = view.prize_name();
        if(model.add_prize_to_database(prize)){
            return "Вы выбрали " + prize + "!\n";
        }
        return "Такого выйгрыша нет!\n";
    }

    private void prize_pull_info() {
        List<String> prize_pull = model.getPrize_pull();
        if (prize_pull.isEmpty()) {
            System.out.println("Пока нет ни одного выйгрыша!\n");
        } else {
            System.out.print("Выйгрыши: ");
            for (String prize : prize_pull) {
                System.out.print(prize + ", ");
            }
            System.out.println("\n");
        }
    }
}
