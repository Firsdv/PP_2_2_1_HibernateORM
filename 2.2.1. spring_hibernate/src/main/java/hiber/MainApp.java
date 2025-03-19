package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User misha = new User("Misha", "Shumaher", "misha@mail.ru");
        User fernando = new User("Fernando", "Alonso", "fedya@mail.ru");
        User kimi = new User("Kimi", "Raikkonen", "kimi@mail.ru");
        User rubens = new User("Rubens", "Baricello", "rubens@mail.ru");

        Car mers = new Car("Mercedes Benz", 112);
        Car lamba = new Car("Lamborghini", 1);
        Car bmw = new Car("BMW", 7);
        Car toyota = new Car("Toyota", 2025);

        misha.setCar(mers);
        fernando.setCar(lamba);
        kimi.setCar(bmw);
        rubens.setCar(toyota);

        mers.setUser(misha);
        lamba.setUser(fernando);
        bmw.setUser(kimi);
        toyota.setUser(rubens);

        userService.add(misha);
        userService.add(fernando);
        userService.add(kimi);
        userService.add(rubens);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println();
            System.out.println(user + " " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("Lamborghini", 1));


        context.close();
    }
}