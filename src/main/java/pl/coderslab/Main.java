package pl.coderslab;

import java.io.IOException;
import java.util.logging.*;

public class Main {
    static final Logger logger = Logger.getLogger("WebTaskManager");

    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setLevel(Level.WARNING);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.severe("Błąd przy tworzeniu pliku logów: " + e.getMessage());
        }
        logger.info("Uruchomiono aplikację");

        if (!files.systemCheck()) {
            users.createAdminAccount();
        }

        new Thread(() -> {
            http.runHTTP();
        }).start();
        new Thread(() -> {
            while(users.userId == 0)
            {
                users.login();
            }
        }).start();
    }

    public static void ShoutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Zamykanie zasobów...");
            //todo: zamknięcie HTTP
        }));

        System.out.println("Zamknięcie aplikacji...");
        System.exit(0);
    }
}

