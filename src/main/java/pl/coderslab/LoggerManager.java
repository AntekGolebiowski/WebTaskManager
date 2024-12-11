package pl.coderslab;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public class LoggerManager {
    private static final Logger logger = Logger.getLogger("pl.coderslab.WebTaskManager");

    static {
        try {
            for (Handler handler : logger.getHandlers()) {
                logger.removeHandler(handler);
            }
            logger.setUseParentHandlers(false);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new CustomFormatter());
            logger.addHandler(consoleHandler);


            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);


        } catch (IOException e) {
            logger.severe("Błąd przy konfiguracji loggera: " + e.getMessage());
        }
    }

    private LoggerManager() {

    }

    public static Logger getLogger() {
        return logger;
    }

    private static class CustomFormatter extends Formatter {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();
            if(record.getLevel() == Level.SEVERE) {
                sb.append(config.RED_BACKGROUND + "BŁĄD KRYTYCZNY:" + config.RED + " ");
            }else if(record.getLevel() == Level.WARNING) {
                sb.append(config.RED_BACKGROUND + "OSTRZEŻENIE:" + config.RED + " ");
            }else if(record.getLevel() == Level.INFO) {
                sb.append(config.BLUE_BACKGROUND + "INFO:" + config.BLUE + " ");
            }
            sb.append(record.getMessage()).append(config.RESET).append("\n");
            return sb.toString();
        }
    }
}
