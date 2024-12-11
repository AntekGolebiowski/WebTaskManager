package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.logging.Logger;

public class users {
    private static final Logger logger = LoggerManager.getLogger();

    static int userId = -1;
    static Enums whereIsUser = Enums.NOWHERE;

    public static boolean login() {
        //todo: zamiast podawać login poproś o imię i nazwisko i zamień je na login
        System.out.print("Podaj login: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        while (username.length() < config.USERNAME_MIN_LENGTH || username.contains(",")) {
            System.out.println(config.RED + "Podano nieprawidłowy login!" + config.RESET);
            System.out.print("Podaj login: ");
            username = scanner.nextLine();
        }

        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        while (password == null || password.isEmpty()) {
            System.out.println(config.RED + "Podano nieprawidłowe hasło!" + config.RESET);
            System.out.print("Podaj hasło: ");
            password = scanner.nextLine();
        }

        if(files.loginUserPassword(username, password))
        {
            userId = files.getUserId(username);
            return true;
        }else {
            logger.warning("Podano nieprawidłowe hasło!");
        }
        return false;
    }

    public static void createAdminAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(config.BLUE + "Tworzenie konta administratora" + config.RESET);
        System.out.print("Wprowadź login: ");
        String username = scanner.nextLine();
        while (username.length() < config.USERNAME_MIN_LENGTH || username.contains(",")) {
            System.out.println(config.RED + "Podano nieprawidłowy login! Login musi zawierać conajmniej " + config.USERNAME_MIN_LENGTH + " znaki." + config.RESET);
            System.out.print("Wprowadź login: ");
            username = scanner.nextLine();
        }

        System.out.print("Wprowadź hasło: ");
        String password = scanner.nextLine();
        while (password.length() < config.PASSWORD_MIN_LENGTH) {
            System.out.println(config.RED + "Podano nieprawidłowy hasło! Hasło musi zawierać conajmniej " + config.PASSWORD_MIN_LENGTH + " znaki." + config.RESET);
            System.out.print("Wprowadź hasło: ");
            password = scanner.nextLine();
        }
        password = hashPassword(password);

        System.out.print("Wprowadź imię: ");
        String name = scanner.nextLine();
        while (name.length() < config.NAME_MIN_LENGTH || name.contains(",")) {
            System.out.println(config.RED + "Podano nieprawidłowy imię! Imię musi zawierać conajmniej " + config.NAME_MIN_LENGTH + " znaki." + config.RESET);
            System.out.print("Wprowadź login: ");
            name = scanner.nextLine();
        }

        System.out.print("Wprowadź naziwsko: ");
        String surname = scanner.nextLine();
        while (surname.length() < config.SURNAME_MIN_LENGTH || surname.contains(",")) {
            System.out.println(config.RED + "Podano nieprawidłowy imię! Imię musi zawierać conajmniej " + config.SURNAME_MIN_LENGTH + " znaki." + config.RESET);
            System.out.print("Wprowadź login: ");
            surname = scanner.nextLine();
        }

        System.out.println("Czy chcesz utworzyć przykładową bazę użytkowników oraz zadań?");
        System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
        String sample = scanner.nextLine();
        while (sample == null || sample.isEmpty() || (!"tak".equalsIgnoreCase(sample) && !"nie".equalsIgnoreCase(sample))) {
            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
            sample = scanner.nextLine();
        }
        boolean sampleFiles = "tak".equalsIgnoreCase(sample);

        logger.info("Rozpoczynam instalację systemu...");
        if(files.install(sampleFiles)) {
            logger.info("Utworzono wszystkie pliki...");
        }else{
            logger.severe("Instalacja zakończona niepowodzeniem, praca programu nie może być kontynuowana!");
            Main.ShoutDown();
        }
        Path path = Paths.get("users.csv");
        try {
            Files.writeString(path, "0," + username + "," + password + "," + name + "," + surname + ",true\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            logger.severe("Utworzenie pliku użytkowników zakończyło się niepowodzeniem! Nie można kontynuować pracy w aplikacji." + e.getMessage());
            Main.ShoutDown();
        }

        System.out.println(config.BLUE + "Instalacja zakończona powodzeniem, możesz się zalogować." + config.RESET);
        logger.info("Instalacja zakończona powodzeniem, możesz się zalogować.");
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean validatePassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
