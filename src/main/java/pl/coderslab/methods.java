package pl.coderslab;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class methods {
    public static String returnFomattedDate(long timestamp, String format){
        DateTimeFormatter formatter;
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        if("dd/mm/yyyy".equals(format)){
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }else if("dd/MM/yyyy HH:mm".equals(format)){
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        }else{
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        }
        return dateTime.format(formatter);
    }

    public static String removePolishCharacters(String input) {
        String[][] polishToLatinMap = {
                {"ą", "a"}, {"ć", "c"}, {"ę", "e"}, {"ł", "l"}, {"ń", "n"},
                {"ó", "o"}, {"ś", "s"}, {"ź", "z"}, {"ż", "z"},
                {"Ą", "A"}, {"Ć", "C"}, {"Ę", "E"}, {"Ł", "L"}, {"Ń", "N"},
                {"Ó", "O"}, {"Ś", "S"}, {"Ź", "Z"}, {"Ż", "Z"}
        };

        for (String[] mapping : polishToLatinMap) {
            input = input.replace(mapping[0], mapping[1]);
        }
        return input;
    }

    public static String lineBreaker(String input, int maxChars) {
        StringBuilder sb = new StringBuilder();
        int charsInLine = 0;

        for (String word : input.split(" ")) {
            if (word.contains("\n")) {
                String[] parts = word.split("\n", -1);
                for (int i = 0; i < parts.length; i++) {
                    if (charsInLine + parts[i].length() > maxChars && charsInLine > 0) {
                        sb.append("\n");
                        charsInLine = 0;
                    }
                    if (!parts[i].isEmpty()) {
                        if (charsInLine > 0) {
                            sb.append(" ");
                            charsInLine++;
                        }
                        sb.append(parts[i]);
                        charsInLine += parts[i].length();
                    }
                    if (i < parts.length - 1) {
                        sb.append("\n");
                        charsInLine = 0;
                    }
                }
            } else {
                if (charsInLine + word.length() + 1 > maxChars) {
                    sb.append("\n");
                    charsInLine = 0;
                } else if (charsInLine > 0) {
                    sb.append(" ");
                    charsInLine++;
                }
                sb.append(word);
                charsInLine += word.length();
            }
        }
        return sb.toString();
    }

    public static void help() {
        //todo:panel pomocy
        return;
    }
}
