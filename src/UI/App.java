package UI;

import Classes.Toy;
import MVP.Model;
import MVP.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void button_click() {
        Toy bear = new Toy("bear", 1, 0.1f);
        Toy cat = new Toy("cat", 1, 0.2f);
        Toy rabbit = new Toy("rabbit", 1, 0.3f);
        List<Toy> toy_list = new ArrayList<>();
        toy_list.add(bear);
        toy_list.add(cat);
        toy_list.add(rabbit);

        Presenter presenter = new Presenter(new User_View(new Scanner(System.in)), new Model(toy_list));
        presenter.menu_processing();
    }

}
