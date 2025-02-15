package application.bookstore;

import application.bookstore.controllers.LoginController;
import application.bookstore.models.Author;
import application.bookstore.models.Role;
import application.bookstore.models.User;
import application.bookstore.views.LoginView;
import application.bookstore.views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    public static void main(String[] args) {
        seedData();
        launch(args);
    }

    private static void seedData() {
        User admin = new User("admin", "1", Role.ADMIN);
        User manager = new User("manager", "1", Role.MANAGER);
        User librarian = new User("librarian", "1", Role.LIBRARIAN);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(User.FILE_PATH));
            outputStream.writeObject(admin);
            outputStream.writeObject(manager);
            outputStream.writeObject(librarian);
            System.out.println("Wrote users to the file users.dat successfully");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Author.FILE_PATH))) {
            outputStream.writeObject(new Author("Test1", "Test1"));
            outputStream.writeObject(new Author("Test2", "Test2"));
            outputStream.writeObject(new Author("Test3", "Test3"));

            System.out.println("Wrote authors to the file users.dat successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        LoginView loginView = new LoginView();
        new LoginController(loginView, new MainView(stage), stage);
        Scene scene = new Scene(loginView.getView(), 600, 600);
        stage.setTitle("Bookstore");
        stage.setScene(scene);
        stage.show();
    }

}
