package MVP;

import Classes.Toy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private List<Toy> toy_list;
    private List<String> prize_pull;

    @Override
    public String toString() {
        String res = "";
        for (Toy toy : toy_list) {
            res += String.format("Имя: %s, Колличество: %d, Шанс выйгрыша: %f", toy.getName(), toy.getQuantity(), toy.getProbability());
            res += "\n";
        }
        return res;
    }

    public List<String> getPrize_pull() {
        return prize_pull;
    }

    public void setPrize_pull(List<String> prize_pull) {
        this.prize_pull = prize_pull;
    }

    public List<Toy> getToy_list() {
        return toy_list;
    }

    public void setToy_list(List<Toy> toy_list) {
        this.toy_list = toy_list;
    }

    public Model() {
        toy_list = new ArrayList<>();
        prize_pull = new ArrayList<>();
    }

    public Model(List<Toy> toy_list) {
        this.toy_list = toy_list;
        prize_pull = new ArrayList<>();
    }

    public boolean add_toy_to_list(Toy toy) {
        toy_list.add(toy);
        return true;
    }

    public Toy hold_a_draw(List<Toy> toy_list) {
        Random random = new Random();
        float[] weigh_list = new float[toy_list.size()];
        float weights_sum = 0;
        for (int i = 0; i < toy_list.size(); i++) {
            float weight = toy_list.get(i).getProbability() * toy_list.get(i).getQuantity();
            weights_sum += weight;
            weigh_list[i] = weights_sum;
        }

        float token = random.nextFloat(0, weights_sum);

        for (int i = 0; i < weigh_list.length; i++) {
            if (token <= weigh_list[i]) {
                return toy_list.get(i);
            }
        }

        return null;
    }

    public boolean set_new_probability(String[] name_and_new_probability) {
        String name = name_and_new_probability[0];
        float new_probability = Float.parseFloat(name_and_new_probability[1]);
        for (Toy toy : toy_list) {
            if (toy.getName().equals(name))
                toy.setProbability(new_probability);
        }
        return true;
    }

    public String add_to_prize_pull(Toy toy) {
        toy.setQuantity(toy.getQuantity() - 1);
        delete_zero_quantity_toy(toy_list);
        prize_pull.add(toy.getName());
        return toy.toString();
    }

    public boolean add_prize_to_database(String chosen_prize) {
        for (String prize : prize_pull) {
            if (prize.equals(chosen_prize)) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(
                            "C:\\toy_vending_machine\\src\\Prize_database\\Database.txt", true));
                    bw.write(prize);
                    bw.newLine();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                prize_pull.remove(chosen_prize);
                return true;
            }
        }
        return false;
    }

    private void delete_zero_quantity_toy(List<Toy> toy_list) {
        toy_list.removeIf(toy -> toy.getQuantity() <= 0);
    }
}
