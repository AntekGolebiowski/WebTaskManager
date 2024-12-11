package pl.coderslab;

import java.nio.file.*;
import java.util.logging.Logger;

public class files {
    private static final Logger logger = LoggerManager.getLogger();

    public static boolean systemCheck()
    {
        //todo: jeśli brak pliku użytkowników zwróc false
        //todo: weryfikacja poprawności innych plików
        Path path = Paths.get("users.csv");
        if(!Files.exists(path))
        {
            logger.warning("Nie znaleziono pliku użytkowników. Rozpoczynam instalację.");
            return false;
        }
        logger.info("Weryfikacja plików zakończona powodzeniem.");
        return true;
    }
}
