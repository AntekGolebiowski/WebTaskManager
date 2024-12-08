package pl.coderslab;

import java.nio.file.*;

public class files {
    public static boolean systemCheck()
    {
        //todo: jeśli brak pliku użytkowników zwróc false
        //todo: weryfikacja poprawności innych plików
        Path path = Paths.get("users.csv");
        if(!Files.exists(path))
        {
            Main.logger.warning("Nie znaleziono pliku użytkowników. Rozpoczynam instalację.");
            return false;
        }
        Main.logger.info("Weryfikacja plików zakończona powodzeniem.");
        return true;
    }
}
