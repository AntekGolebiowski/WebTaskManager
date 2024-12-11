package pl.coderslab;

import java.util.logging.*;

public class Main {
    static final Logger logger = LoggerManager.getLogger();

    public static void main(String[] args) {
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

