package pl.coderslab;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static boolean loginUserPassword(String username, String password)
    {
        Path path = Paths.get("users.csv");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int comma1 = -1;
            int comma2 = -1;

            while ((line = reader.readLine()) != null) {
                comma1 = line.indexOf(",");
                comma2 = line.indexOf(",",comma1+1);

                if (username.equals(line.substring(comma1+1, comma2).trim())) {
                    int[] separators = getSeparatorsIndex(line,",");
                    if(users.validatePassword(password,line.substring(separators[1]+1, separators[2]).trim()))
                    {
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            logger.severe("Nie udało się otworzyć pliku users.csv. " + e.getMessage());
        }
        return false;
    }

    public static int getUserId(String username)
    {
        Path path = Paths.get("users.csv");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int comma1 = -1;
            int comma2 = -1;

            while ((line = reader.readLine()) != null) {
                comma1 = line.indexOf(",");
                comma2 = line.indexOf(",",comma1+1);

                if (username.equals(line.substring(comma1+1, comma2).trim())) {
                    try {
                        return Integer.parseInt(line.substring(0, comma1).trim());
                    } catch (NumberFormatException e) {
                        logger.severe("Nie udało się pobrać id użytkownika. " + e.getMessage());
                        return -1;
                    }
                }
            }
        } catch (IOException e) {
            logger.severe("Nie udało się otworzyć pliku users.csv. " + e.getMessage());
        }
        return -1;
    }

    public static String getUserName(int id)
    {
        if(id == -1)
        {
            return null;
        }

        Path path = Paths.get("users.csv");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            String stringId = Integer.toString(id);

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stringId)) {
                    int[] comma = getSeparatorsIndex(line,",");
                    return line.substring(comma[2]+1, comma[3]) + " " + line.substring(comma[3]+1, comma[4]);
                }
            }
        } catch (IOException e) {
            logger.severe("Nie udało się otworzyć pliku users.csv. " + e.getMessage());
        }
        return null;
    }

    public static int[] getSeparatorsIndex(String string, String separator)
    {
        int count = 0;
        int index = string.indexOf(separator);
        while (index != -1) {
            count++;
            index = string.indexOf(separator, index + 1);
        }

        int[] indexes = new int[count];
        index = string.indexOf(separator);
        int i = 0;

        while (index != -1) {
            indexes[i++] = index;
            index = string.indexOf(separator, index + 1);
        }

        return indexes;
    }

    public static boolean install(boolean samples)
    {
        File file = new File("projects.csv");
        try {
            if(file.createNewFile())
            {
                logger.info("Utworzono plik projektów: projects.csv");
            }else{
                logger.info("Nie utworzono pliku projektów, plik już istnieje! Plik: projects.csv");
            }
        } catch (IOException e) {
            logger.severe("Nie udało się utworzyć pliku projektów! " + e.getMessage());
            return false;
        }

        return true;
    }
}
