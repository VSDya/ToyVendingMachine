package UI;

import MVP.IView;

import java.util.Scanner;

public class User_View implements IView {
    Scanner sc;

    public User_View(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public String menu() {
        System.out.println("""
                Введите:
                1 - добавить игрушку
                2 - начать розыгрыш
                3 - забрать приз
                4 - посмотреть на игрушки
                5 - изменить вероятности выпадения
                6 - посмотреть на выйгрыши
                7 - выход""");
        return sc.nextLine();
    }

    @Override
    public String creating_the_toy() {
        System.out.println("Введите название, колличество и вероятность выпадение игрушки(в долях, где 0 => 0%, a 1 => 100%) через запятую:");
        return sc.nextLine();
    }

    public String change_probability() {
        System.out.println("Введите название игрушки, вероятность выпадения которой вы хотите поменять и новую вероятность(через запятую): ");
        return sc.nextLine();
    }

    public String prize_name() {
        System.out.println("Введите название игрушки, которую вы хотите забрать: ");
        return sc.nextLine();
    }
}
