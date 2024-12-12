package pl.coderslab;

import java.io.IOException;
import java.util.Scanner;
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
            while(users.userId == -1) {
                users.whereIsUser = Enums.LOGIN;
                users.login();
            }

            logger.info("Witaj " + files.getUserName(users.userId) + "!");

            projects.loadProjects();
            projects.showProjects();

            Scanner scanner = new Scanner(System.in);
            while (true)
            {
                String command = scanner.nextLine();
                if(!commands.onUserCommand(command))
                {
                    System.out.println(config.BRIGHT_BLACK + "Podana komenda nie istnieje!" + config.RESET);
                }
            }
        }).start();
    }

    public static void ShoutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Zamykanie zasobów...");
            try {
                http.clientSocket.close();
            } catch (IOException e) {
                logger.severe("Nie udało się zamknąc socketu HTTP. " + e.getMessage());
            }
        }));

        System.out.println("Zamknięcie aplikacji...");
        System.exit(0);
    }
}

