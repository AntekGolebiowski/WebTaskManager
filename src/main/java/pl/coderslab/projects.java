package pl.coderslab;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class projects {
    private static final Logger logger = LoggerManager.getLogger();
    public static void showProjects() {
        System.out.println(config.PURPLE_BACKGROUND + "LISTA PROJEKTÓW" + config.RESET);
        File file = new File("projects.csv");

        if(file.exists())
        {
            if(file.length() > 0) {
                Path path = Paths.get("projects.csv");
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        int[] separators = files.getSeparatorsIndex(line,"|");
                        sb.append(line, 0, separators[0]);
                        sb.append(": ");
                        if("true".equalsIgnoreCase(line.substring(separators[1]+1, separators[2])))
                        {
                            sb.append(config.RED + "PILNE " + config.RESET);
                        }
                        sb.append(line, separators[0]+1, separators[1]);

                        sb.append(", utwrzono: ");
                        sb.append(methods.returnFomattedDate(Long.parseLong(line.substring(separators[3]+1, separators[4])), "dd/mm/yyyy"));

                        sb.append(", termin realizacji: ");
                        sb.append(methods.returnFomattedDate(Long.parseLong(line.substring(separators[4]+1, separators[5])), "dd/mm/yyyy"));

                        sb.append(", postęp: ");
                        sb.append(showProgress(20));
                    }
                    System.out.println(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println(config.BRIGHT_BLACK + "Brak projektów." + config.RESET);
            }
        }else{
            System.out.println(config.BRIGHT_BLACK + "Brak projektów." + config.RESET);
            try {
                if(file.createNewFile())
                {
                    logger.info("Utworzono plik projektów");
                }
            } catch (IOException e) {
                logger.severe("Nie udało się utworzyć pliku projektów! " + e.getMessage());
            }
        }

        System.out.println("Dostępne komendy: " + config.BRIGHT_BLACK_BACKGROUND + "PODGLAD #id" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "UTWORZ" + config.RESET + " ");
    }

    public static String showProgress(int progress)
    {
        return "[" + config.GREEN_BACKGROUND + "________"+config.RESET+"____________] " + progress + "%";
    }
}
