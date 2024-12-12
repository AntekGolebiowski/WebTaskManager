package pl.coderslab;

import java.util.logging.Logger;

public class config {
    private static final Logger logger = LoggerManager.getLogger();

    public static final int NAME_MIN_LENGTH = 3;
    public static final int SURNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int LINE_BREAKER = 150;

    static int port = 8080;

    //Kolory
    public static final String RESET = "\033[0m";          // Reset kolorów

    public static final String BLACK = "\033[0;30m";       // BLACK
    public static final String RED = "\033[0;31m";         // RED
    public static final String GREEN = "\033[0;32m";       // GREEN
    public static final String YELLOW = "\033[0;33m";      // YELLOW
    public static final String BLUE = "\033[0;34m";        // BLUE
    public static final String PURPLE = "\033[0;35m";      // PURPLE
    public static final String CYAN = "\033[0;36m";        // CYAN
    public static final String WHITE = "\033[0;37m";       // WHITE

    public static final String BRIGHT_BLACK = "\033[0;90m";    // BRIGHT BLACK
    public static final String BRIGHT_RED = "\033[0;91m";      // BRIGHT RED
    public static final String BRIGHT_GREEN = "\033[0;92m";    // BRIGHT GREEN
    public static final String BRIGHT_YELLOW = "\033[0;93m";   // BRIGHT YELLOW
    public static final String BRIGHT_BLUE = "\033[0;94m";     // BRIGHT BLUE
    public static final String BRIGHT_PURPLE = "\033[0;95m";   // BRIGHT PURPLE
    public static final String BRIGHT_CYAN = "\033[0;96m";     // BRIGHT CYAN
    public static final String BRIGHT_WHITE = "\033[0;97m";    // BRIGHT WHITE

    public static final String BLACK_UNDERLINED = "\033[4;30m";    // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";      // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";    // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m";   // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";     // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m";   // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";     // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";    // WHITE

    public static final String BRIGHT_BLACK_UNDERLINED = "\033[4;90m";  // BRIGHT BLACK
    public static final String BRIGHT_RED_UNDERLINED = "\033[4;91m";    // BRIGHT RED
    public static final String BRIGHT_GREEN_UNDERLINED = "\033[4;92m";  // BRIGHT GREEN
    public static final String BRIGHT_YELLOW_UNDERLINED = "\033[4;93m"; // BRIGHT YELLOW
    public static final String BRIGHT_BLUE_UNDERLINED = "\033[4;94m";   // BRIGHT BLUE
    public static final String BRIGHT_PURPLE_UNDERLINED = "\033[4;95m"; // BRIGHT PURPLE
    public static final String BRIGHT_CYAN_UNDERLINED = "\033[4;96m";   // BRIGHT CYAN
    public static final String BRIGHT_WHITE_UNDERLINED = "\033[4;97m";  // BRIGHT WHITE

    public static final String BLACK_BACKGROUND = "\033[40m";       // BLACK BACKGROUND
    public static final String RED_BACKGROUND = "\033[41m";         // RED BACKGROUND
    public static final String GREEN_BACKGROUND = "\033[42m";       // GREEN BACKGROUND
    public static final String YELLOW_BACKGROUND = "\033[43m";      // YELLOW BACKGROUND
    public static final String BLUE_BACKGROUND = "\033[44m";        // BLUE BACKGROUND
    public static final String PURPLE_BACKGROUND = "\033[45m";      // PURPLE BACKGROUND
    public static final String CYAN_BACKGROUND = "\033[46m";        // CYAN BACKGROUND
    public static final String WHITE_BACKGROUND = "\033[47m";       // WHITE BACKGROUND

    public static final String BRIGHT_BLACK_BACKGROUND = "\033[100m";  // BRIGHT BLACK BACKGROUND
    public static final String BRIGHT_RED_BACKGROUND = "\033[101m";    // BRIGHT RED BACKGROUND
    public static final String BRIGHT_GREEN_BACKGROUND = "\033[102m";  // BRIGHT GREEN BACKGROUND
    public static final String BRIGHT_YELLOW_BACKGROUND = "\033[103m"; // BRIGHT YELLOW BACKGROUND
    public static final String BRIGHT_BLUE_BACKGROUND = "\033[104m";   // BRIGHT BLUE BACKGROUND
    public static final String BRIGHT_PURPLE_BACKGROUND = "\033[105m"; // BRIGHT PURPLE BACKGROUND
    public static final String BRIGHT_CYAN_BACKGROUND = "\033[106m";   // BRIGHT CYAN BACKGROUND
    public static final String BRIGHT_WHITE_BACKGROUND = "\033[107m";  // BRIGHT WHITE BACKGROUND
}
