package pl.coderslab;

import javax.sound.midi.Patch;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class projects {
    private static final Logger logger = LoggerManager.getLogger();

    static Enums projectsToShow = Enums.ALL;
    static class Projects {
        private int id;
        private String name;
        private String description;
        private boolean urgent;
        private boolean done;
        private long created;
        private long deadline;
        private int progressAnalysis;
        private int progressStructure;
        private int progressDesign;
        private int progressFrontend;
        private int progressBackend;
        private int progressTesting;
        private int progressOptimization;
        private int comments;

        public Projects(int id, String name, String description, boolean urgent, boolean done, long created, long deadline, int progressAnalysis, int progressStructure, int progressDesign, int progressFrontend, int progressBackend, int progressTesting, int progressOptimization, int comments) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.urgent = urgent;
            this.done = done;
            this.created = created;
            this.deadline = deadline;
            this.progressAnalysis = progressAnalysis;
            this.progressStructure = progressStructure;
            this.progressDesign = progressDesign;
            this.progressFrontend = progressFrontend;
            this.progressBackend = progressBackend;
            this.progressTesting = progressTesting;
            this.progressOptimization = progressOptimization;
            this.comments = comments;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public boolean isUrgent() {
            return urgent;
        }

        public boolean isDone() {
            return done;
        }

        public long getCreated() {
            return created;
        }

        public long getDeadline() {
            return deadline;
        }

        public int getProgressAnalysis() {
            return progressAnalysis;
        }

        public int getProgressStructure() {
            return progressStructure;
        }

        public int getProgressDesign() {
            return progressDesign;
        }

        public int getProgressFrontend() {
            return progressFrontend;
        }

        public int getProgressBackend() {
            return progressBackend;
        }

        public int getProgressTesting() {
            return progressTesting;
        }

        public int getProgressOptimization() {
            return progressOptimization;
        }

        public int getComments() {
            return comments;
        }


        public void setComments(int comments) { this.comments = comments; }
        public static void updateComments(int projectId, int newCommentsCount) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setComments(newCommentsCount);
                    break;
                }
            }
        }

        public void setName(String name) { this.name = name; }
        public static void updateName(int projectId, String name) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setName(name);
                    break;
                }
            }
        }

        public void setDescription(String description) { this.description = description; }
        public static void updateDescription(int projectId, String description) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setDescription(description);
                    break;
                }
            }
        }

        public void setDone(boolean done) { this.done = done; }
        public static void updateDone(int projectId, boolean done) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setDone(done);
                    break;
                }
            }
        }

        public void setUrgent(boolean urgent) { this.urgent = urgent; }
        public static void updateUrgent(int projectId, boolean urgent) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setUrgent(urgent);
                    break;
                }
            }
        }

        public void setAnalysis(int value) { this.progressAnalysis = value; }
        public static void updateAnalysis(int projectId, int value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setAnalysis(value);
                    break;
                }
            }
        }

        public void setStructure(int value) { this.progressStructure = value; }
        public static void updateStructure(int projectId, int value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setStructure(value);
                    break;
                }
            }
        }

        public void setDesign(int value) { this.progressDesign = value; }
        public static void updateDesign(int projectId, int value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setDesign(value);
                    break;
                }
            }
        }

        public void setFrontend(int value) { this.progressFrontend = value; }
        public static void updateFrontend(int projectId, int value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setFrontend(value);
                    break;
                }
            }
        }

        public void setBackend(int value) { this.progressBackend = value; }
        public static void updateBackend(int projectId, int value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setBackend(value);
                    break;
                }
            }
        }

        public void setTesting(int value) { this.progressTesting = value; }
        public static void updateTesting(int projectId, int value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setTesting(value);
                    break;
                }
            }
        }

        public void setOptimization(int value) { this.progressOptimization = value; }
        public static void updateOptimization(int projectId, int value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setOptimization(value);
                    break;
                }
            }
        }

        public void setDeadline(long value) { this.deadline = value; }
        public static void updateDeadline(int projectId, long value) {
            for (Projects project : projects) {
                if (project.getId() == projectId) {
                    project.setDeadline(value);
                    break;
                }
            }
        }
    }

    static List<Projects> projects = new ArrayList<>();

    public static Projects findProjectById(List<Projects> projects, int id) {
        for (Projects project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    public static int findMaxId(List<Projects> projects) {
        int max = -1;
        for (Projects project : projects) {
            if (project.getId() > max) {
                max = project.getId();
            }
        }
        return max;
    }

    public static void showProjects() {
        showProjects("id");
    }

    public static boolean loadProjects() {
        File file = new File("projects.csv");
        if (file.exists()) {
            if (file.length() > 0) {
                Path path = Paths.get("projects.csv");

                int tempId;
                String tempName;
                String tempDescription;
                boolean tempUrgent;
                boolean tempDone;
                long tempCreated;
                long tempDeadline;
                int tempProgressAnalysis;
                int tempProgressStructure;
                int tempProgressDesign;
                int tempProgressFrontend;
                int tempProgressBackend;
                int tempProgressTesting;
                int tempProgressOptimization;
                int tempComments = 0;


                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int[] separators = files.getSeparatorsIndex(line, "|");
                        try {
                            tempId = Integer.parseInt(line.substring(0, separators[0]));
                            tempName = line.substring(separators[0] + 1, separators[1]);
                            tempDescription = line.substring(separators[1] + 1, separators[2]);
                            tempUrgent = "true".equalsIgnoreCase(line.substring(separators[2] + 1, separators[3]));
                            tempDone = "true".equalsIgnoreCase(line.substring(separators[3] + 1, separators[4]));
                            tempCreated = Long.parseLong(line.substring(separators[4] + 1, separators[5]));
                            tempDeadline = Long.parseLong(line.substring(separators[5] + 1, separators[6]));
                            tempProgressAnalysis = Integer.parseInt(line.substring(separators[6] + 1, separators[7]));
                            tempProgressStructure = Integer.parseInt(line.substring(separators[7] + 1, separators[8]));
                            tempProgressDesign = Integer.parseInt(line.substring(separators[8] + 1, separators[9]));
                            tempProgressFrontend = Integer.parseInt(line.substring(separators[9] + 1, separators[10]));
                            tempProgressBackend = Integer.parseInt(line.substring(separators[10] + 1, separators[11]));
                            tempProgressTesting = Integer.parseInt(line.substring(separators[11] + 1, separators[12]));
                            tempProgressOptimization = Integer.parseInt(line.substring(separators[12] + 1, separators[13]));
                            tempComments = Integer.parseInt(line.substring(separators[13] + 1));

                            projects.add(new Projects(tempId, tempName, tempDescription, tempUrgent, tempDone, tempCreated, tempDeadline, tempProgressAnalysis, tempProgressStructure, tempProgressDesign, tempProgressFrontend, tempProgressBackend, tempProgressTesting, tempProgressOptimization, tempComments));
                        } catch (Exception e) {
                            logger.warning("Prawdopodobnie plik projektów jest uszkodzony: " + e.getMessage());
                        }
                    }
                } catch (IOException e) {
                    logger.severe("Nie udało się odczytać pliku projektów! " + e.getMessage());
                }
            }
        }
        return true;
    }

    public static void projectsEditName(int id, String name) {
        Projects.updateName(id, name.replaceAll("\\|", ""));
        files.editProjectsFile(id, name, Enums.PROJECT_CHANGE_NAME);
    }

    public static void projectsEditDescription(int id, String description) {
        Projects.updateDescription(id, description.replaceAll("\\|", ""));
        files.editProjectsFile(id, description, Enums.PROJECT_CHANGE_DESCRIPTION);
    }

    public static void projectsEditUrgent(int id, boolean urgent) {
        Projects.updateUrgent(id, urgent);
        if(urgent) {
            files.editProjectsFile(id, "true", Enums.PROJECT_CHANGE_URGENT);
        }else{
            files.editProjectsFile(id, "false", Enums.PROJECT_CHANGE_URGENT);
        }
    }

    public static void projectsEditDone(int id, boolean done) {
        Projects.updateDone(id, done);
        if(done) {
            files.editProjectsFile(id, "true", Enums.PROJECT_CHANGE_DONE);
        }else{
            files.editProjectsFile(id, "false", Enums.PROJECT_CHANGE_DONE);
        }
    }

    public static void projectsEditProgress(int id, int progress, Enums progressType) {
        switch (progressType) {
            case PROJECT_CHANGE_PROGRESS_ANALYSIS: Projects.updateAnalysis(id,progress); break;
            case PROJECT_CHANGE_PROGRESS_STRUCTURE: Projects.updateStructure(id,progress); break;
            case PROJECT_CHANGE_PROGRESS_DESIGN: Projects.updateDesign(id,progress); break;
            case PROJECT_CHANGE_PROGRESS_FRONTEND: Projects.updateFrontend(id,progress); break;
            case PROJECT_CHANGE_PROGRESS_BACKEND: Projects.updateBackend(id,progress); break;
            case PROJECT_CHANGE_PROGRESS_TESTING: Projects.updateTesting(id,progress); break;
            case PROJECT_CHANGE_PROGRESS_OPTIMIZATION: Projects.updateOptimization(id,progress); break;
            default: break;
        }

        files.editProjectsFile(id, Integer.toString(progress), progressType);
    }

    public static void projectsEditDeadline(int id, long timestamp) {
        Projects.updateDeadline(id, timestamp);
        files.editProjectsFile(id, Long.toString(timestamp), Enums.PROJECT_CHANGE_DEADLINE);
    }

    public static void showProjects(String sort) {
        users.whereIsUser = Enums.PROJECTS;
        System.out.println(config.PURPLE_BACKGROUND + "LISTA PROJEKTÓW" + config.RESET);

        if (projects.isEmpty()) {
            System.out.println(config.BRIGHT_BLACK + "Nie znaleziono projektów!" + config.RESET);
        } else {

            if (!sort.isEmpty()) {
                if ("date".equalsIgnoreCase(sort)) {
                    projects.sort(Comparator.comparingLong(project -> project.created));
                } else if ("deadline".equalsIgnoreCase(sort)) {
                    projects.sort(Comparator.comparingLong(project -> project.deadline));
                } else if ("name".equalsIgnoreCase(sort)) {
                    projects.sort(Comparator.comparing(project -> project.name));
                } else {
                    projects.sort(Comparator.comparingInt(project -> project.id));
                }
            }

            System.out.println(config.BRIGHT_BLACK + "id: nazwa projektu, data utworzenia, termin realizacji" + config.RESET);
            StringBuilder sb;
            for (Projects value : projects) {
                if ((projectsToShow == Enums.URGENT && value.urgent) || (projectsToShow == Enums.UNDONE && !value.done) || projectsToShow == Enums.ALL) {

                    sb = new StringBuilder();

                    if (value.done) {
                        sb.append(config.BRIGHT_BLACK);
                    }
                    sb.append(value.id).append(": ");
                    if (value.urgent && !value.done) {
                        sb.append(config.RED_BACKGROUND + "PILNY" + config.RESET + " ");
                    }
                    if (value.done) {
                        sb.append(config.RESET + config.BRIGHT_BLACK_BACKGROUND + "UKOŃCZONY" + config.BRIGHT_BLACK + " ");
                    }
                    sb.append(value.name);
                    sb.append(", utworzono: ").append(methods.returnFomattedDate(value.created, "dd/mm/yyyy"));
                    sb.append(", termin realizacji: ").append(methods.returnFomattedDate(value.deadline, "dd/mm/yyyy"));

                    if (value.done) {
                        sb.append(config.RESET);
                    }

                    System.out.println(sb);
                }
            }


            if (projectsToShow == Enums.UNDONE) {
                System.out.println("Wyświetlam tylko nieukończone projekty.");
            } else if (projectsToShow == Enums.URGENT) {
                System.out.println("Wyświetlam tylko pilne projekty.");
            }

        }
        System.out.println("Dostępne komendy: " + config.BRIGHT_BLACK_BACKGROUND + "PODGLAD #id" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "UTWORZ" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "SORTUJ" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "WYSWIETLAJ" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "ZAMKNIJ" + config.RESET);
    }

    public static String showProgress(int progress) {
        return showProgress(progress, false);
    }

    public static String showProgress(int progress, boolean done) {
        progress = Math.max(0, Math.min(progress, 100));

        int fullSegments = progress / 5;

        StringBuilder progressBar = new StringBuilder();
        if (done) {
            progressBar.append(config.BRIGHT_BLACK + "[");
            for (int i = 0; i < 20; i++) {
                if (i < fullSegments) {
                    progressBar.append(config.BRIGHT_BLACK_BACKGROUND + "_");
                } else if (i == fullSegments) {
                    progressBar.append(config.BRIGHT_BLACK + "_");
                } else {
                    progressBar.append("_");
                }
            }
            progressBar.append(config.BRIGHT_BLACK + "] " + progress + "%");
        } else {
            progressBar.append(config.RESET + "[");
            for (int i = 0; i < 20; i++) {
                if (i < fullSegments) {
                    progressBar.append(config.GREEN + config.GREEN_BACKGROUND + "_");
                } else if (i == fullSegments) {
                    progressBar.append(config.RESET + config.BLACK + "_");
                } else {
                    progressBar.append("_");
                }
            }
            progressBar.append(config.RESET + "] " + progress + "%");
        }

        return progressBar.toString();
    }

    public static void previewProject(int id) {
        Path path = Paths.get("projects.csv");
        if (!Files.exists(path)) {
            logger.warning("Plik projektów nie istnieje!");
            return;
        }

        Projects project = findProjectById(projects, id);
        if (project != null) {
            users.whereIsUser = Enums.PROJECT_PREVIEW;
            users.userProjectPreviewID = id;
            System.out.println(config.PURPLE_BACKGROUND + "PODGLĄD PROJEKTU NR #" + id + config.RESET);
            if (project.isUrgent()) {
                System.out.println(config.BRIGHT_BLACK + "Nazwa projektu: " + config.RESET + project.getName() + " " + config.RED_BACKGROUND + "PILNY" + config.RESET);
            }else {
                System.out.println(config.BRIGHT_BLACK + "Nazwa projektu: " + config.RESET + project.getName());
            }
            System.out.println(config.BRIGHT_BLACK + "Opis: " + config.RESET + methods.lineBreaker(project.getDescription(), config.LINE_BREAKER));
            System.out.println(config.BRIGHT_BLACK + "Postę prac: " + config.RESET);
            System.out.println("Analiza potrzeb klienta: " + showProgress(project.getProgressAnalysis()));
            System.out.println("Projektowanie struktury: " + showProgress(project.getProgressStructure()));
            System.out.println("Projektowanie UI/UX: " + showProgress(project.getProgressDesign()));
            System.out.println("Tworzenie frontend: " + showProgress(project.getProgressFrontend()));
            System.out.println("Tworzenie back-end: " + showProgress(project.getProgressBackend()));
            System.out.println("Testowanie: " + showProgress(project.getProgressTesting()));
            System.out.println("Optymalizacja: " + showProgress(project.getProgressOptimization()));
            System.out.println(config.BRIGHT_BLACK + "Data utworzenia: " + config.RESET + methods.returnFomattedDate(project.getCreated(), "dd/mm/yyyy") + config.BRIGHT_BLACK + " Termin realizacji: " + config.RESET + methods.returnFomattedDate(project.getDeadline(), "dd/mm/yyyy") + config.BRIGHT_BLACK + " Ilość komentarzy: " + config.RESET + project.getComments());

            System.out.println("Dostępne komendy: " + config.BRIGHT_BLACK_BACKGROUND + "KOMENTARZE" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "KASUJ" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "WYJDZ" + config.RESET);
        } else {
            logger.warning("Nie znaleziono projektu o id: " + id + ".");
        }
    }

    public static void commentsProject(int id) {
        System.out.println(config.PURPLE_BACKGROUND + "KOMENTARZE DO PROJEKTU NR #" + id + config.RESET);
        Path path = Paths.get("comments_" + id +".csv");
        int userId;
        String comment;
        long time;
        boolean commentsFound = false;
        users.whereIsUser = Enums.PROJECT_COMMENTS;
        if (Files.exists(path)) {
            try{
                for(String line : Files.readAllLines(path))
                {
                    if(!line.isBlank()) {
                        try {
                            int[] separators = files.getSeparatorsIndex(line, "|");
                            userId = Integer.parseInt(line.substring(separators[1] + 1));
                            time = Long.parseLong(line.substring(separators[0] + 1, separators[1]));
                            System.out.println(config.BRIGHT_BLACK + "Komentarz dodany przez: " + config.RESET + files.getUserName(userId) + config.BRIGHT_BLACK + ", data dodania: " + methods.returnFomattedDate(time, "dd/MM/yyyy HH:mm") + config.RESET);
                            System.out.println(methods.lineBreaker(line.substring(0, separators[0]), config.LINE_BREAKER));
                            commentsFound = true;
                        } catch (Exception e) {
                            logger.severe("Plik komentarzy prawdopodobnie jest uszkodzony! " + e.getMessage());
                        }
                    }
                }
            } catch (IOException e) {
                logger.severe("Odczyt pliku komentarzy zakończył się niepowodzeniem! " + e.getMessage());
            }
        }else{
            System.out.println(config.BRIGHT_BLACK + "Nie znaleziono komentarzy." + config.RESET);
        }
        if(!commentsFound) {
            System.out.println(config.BRIGHT_BLACK + "Nie znaleziono komentarzy." + config.RESET);
        }
        System.out.println("Dostępne komendy: " + config.BRIGHT_BLACK_BACKGROUND + "KOMENTUJ" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "WYJDZ" + config.RESET);
        return;
    }

    public static void createProject() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(config.PURPLE_BACKGROUND + "TWORZENIE PROJEKTU" + config.RESET);
        System.out.println("Wprowadź nazwę projektu:");
        System.out.println(config.BRIGHT_BLACK + "W każdym momencie możesz użyć komendy " + config.RESET + config.BRIGHT_BLACK_BACKGROUND + "WYJDZ" + config.RESET + config.BRIGHT_BLACK + " aby anulować dodawanie projektu." + config.RESET);

        String projectName = scanner.nextLine();
        if("wyjdz".equalsIgnoreCase(projectName)) {
            showProjects();
            return;
        }
        while(projectName.isEmpty() || projectName.equals("\\|")) {
            System.out.println(config.RED + "Wprowadzono nieprawidłową nazwę projektu!" + config.RESET);
            System.out.println("Wprowadź nazwę projektu:");
            projectName = scanner.nextLine();
            if("wyjdz".equalsIgnoreCase(projectName)) {
                showProjects();
                return;
            }
        }
        projectName = projectName.replaceAll("\\|", "");

        System.out.println("Wprowadź opis projektu:");
        String projectDescription = scanner.nextLine();
        while(projectDescription.isEmpty() || projectDescription.equals("\\|")) {
            System.out.println(config.RED + "Wprowadzono nieprawidłowy opis projektu!" + config.RESET);
            System.out.println("Wprowadź opis projektu:");
            projectDescription = scanner.nextLine();
            if("wyjdz".equalsIgnoreCase(projectName)) {
                showProjects();
                return;
            }
        }
        projectDescription = projectDescription.replaceAll("\\|", "");

        System.out.println("Wprowadź termin realizacji projektu w formacie DD/MM/YYYY:");
        String projectDeadline = scanner.nextLine();
        if("wyjdz".equalsIgnoreCase(projectName)) {
            showProjects();
            return;
        }
        while(methods.convertDateToTimestamp(projectDeadline) == -1) {
            System.out.println("Wprowadź termin realizacji projektu w formacie DD/MM/YYYY:");
            projectDeadline = scanner.nextLine();
            if("wyjdz".equalsIgnoreCase(projectName)) {
                showProjects();
                return;
            }
        }
        long projectDeadlineLong = methods.convertDateToTimestamp(projectDeadline);

        System.out.println("Czy projekt jest pilny?");
        System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
        String projectUrgent = scanner.nextLine();
        if("wyjdz".equalsIgnoreCase(projectName)) {
            showProjects();
            return;
        }
        while (!"tak".equalsIgnoreCase(projectUrgent) && !"nie".equalsIgnoreCase(projectUrgent)) {
            System.out.println("Czy projekt jest pilny?");
            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
            projectUrgent = scanner.nextLine();
            if("wyjdz".equalsIgnoreCase(projectName)) {
                showProjects();
                return;
            }
        }

        boolean projectUrgentBoolean = "tak".equalsIgnoreCase(projectUrgent);

        int newId = findMaxId(projects);
        newId++;

        StringBuilder sb = new StringBuilder();
        sb.append(newId).append("|");
        sb.append(projectName).append("|");
        sb.append(projectDescription).append("|");
        sb.append(projectUrgentBoolean).append("|");
        sb.append("false").append("|");
        sb.append(System.currentTimeMillis()).append("|");
        sb.append(projectDeadlineLong).append("|");
        sb.append("0").append("|");
        sb.append("0").append("|");
        sb.append("0").append("|");
        sb.append("0").append("|");
        sb.append("0").append("|");
        sb.append("0").append("|");
        sb.append("0").append("|");
        sb.append("0").append("\n");

        Path projectsPath = Paths.get("projects.csv");
        try{
            Files.writeString(projectsPath, sb.toString(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            projects.add(new Projects(newId, projectName, projectDescription, projectUrgentBoolean, false, System.currentTimeMillis(), projectDeadlineLong, 0, 0, 0, 0, 0, 0, 0, 0));
            logger.info("Projekt został pomyślnie dodany!");
            previewProject(newId);
            return;
        }catch (IOException e){
            logger.severe("Nie udało się zapisać nowego projektu. " + e.getMessage());
        }

        showProjects();
        return;
    }

    public static void editProject(int id) {

        System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ NAZWA #nowa_nazwa" + config.RESET + " - zmienia nazwę projektu");
        System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ OPIS #nowy_opis" + config.RESET + " - zmienia opis projektu");
        System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ PILNY #tak/nie" + config.RESET + " - zmienia pilność projektu");
        System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ UKONCZONY #tak/nie" + config.RESET + " - oznacza projekt jako ukończony lub nieukończony");
        System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP" + config.RESET + " - zmienia postęp projektu");
        System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ TERMIN DD/MM/YYYY" + config.RESET + " - zmienia termin realizacji projektu");
        return;
    }

    public static void deleteProject(int id) {
        Projects project = findProjectById(projects, id);
        if (project != null) {
            System.out.println("Czy na pewno chcesz skasować projekt id #" + id + "?");
            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            while (!"tak".equalsIgnoreCase(line) && !"nie".equalsIgnoreCase(line)) {
                System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
                line = scanner.nextLine();
            }
            if("tak".equalsIgnoreCase(line)) {
                Path path = Paths.get("projects.csv");
                StringBuilder sb = new StringBuilder();
                try {
                    for (String fileLine : Files.readAllLines(path)) {
                        if(!fileLine.isBlank()) {
                            if(!fileLine.startsWith(id + "|")) {
                                sb.append(fileLine).append("\n");
                            }
                        }
                    }
                    try {
                        Files.writeString(path, sb);
                        projects.remove(id);
                        Path path2 = Paths.get("comments_" + id + ".csv");
                        Files.deleteIfExists(path2);
                        System.out.println("Projekt został usunięty");
                        showProjects();
                        return;
                    } catch (IOException ex) {
                        logger.severe("Nie udało się zaktualizować pliku projektów! " + ex.getMessage());
                    }
                } catch (IOException e) {
                    logger.severe("Nie udało się otworzyć pliku projektów! " + e.getMessage());
                }
            }
            previewProject(id);
        }else{
            System.out.println(config.BRIGHT_BLACK + "Projekt nie został znaleziony." + config.RESET);
        }
        return;
    }

    public static void commentProject(int id) {
        System.out.println(config.PURPLE_BACKGROUND + "Dodaj komentarz do projektu NR #" + id + config.RESET);
        System.out.println("Wprowadź jedną linię tekstu, jeśli chcesz zrezygnować wpisz " + config.BRIGHT_BLACK_BACKGROUND + "wyjdz" + config.RESET + ".");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if("wyjdz".equalsIgnoreCase(input)) {
            commentsProject(id);
            return;
        }
        Projects project = findProjectById(projects, id);
        if(project == null) {
            logger.warning("Nie udało się dodać komentarz");
            commentsProject(id);
            return;
        }
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(input.replaceAll("\\|", ""));
        sb.append("|");
        sb.append(timeStampMillis);
        sb.append("|");
        sb.append(users.userId);

        Path path = Paths.get("comments_" + id + ".csv");
        int comments = project.getComments();
        try{
            Files.writeString(path, sb, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            logger.info("Pomyślnie dodano komentarz!");

            Path pathToProjects = Paths.get("projects.csv");
            StringBuilder sbProjects = new StringBuilder();
            try {
                for (String fileLine : Files.readAllLines(pathToProjects)) {
                    if(!fileLine.isBlank()) {
                        if(!fileLine.startsWith(id + "|")) {
                            sbProjects.append(fileLine).append("\n");
                        }else{
                            int[] separators = files.getSeparatorsIndex(fileLine, "|");
                            sbProjects.append(fileLine.substring(0, separators[13] + 1));
                            sbProjects.append(comments+1);
                            sbProjects.append("\n");
                        }
                        try {
                            Files.writeString(pathToProjects, sbProjects);
                            Projects.updateComments(id, comments+1);
                        } catch (IOException ex) {
                            logger.severe("Nie udało się zaktualizować liczby komentarzy! " + ex.getMessage());
                        }
                    }
                }
            } catch (IOException e) {
                logger.severe("Nie udało się otworzyć pliku projektów! " + e.getMessage());
            }
        } catch (IOException ex) {
            logger.warning("Dodanie komentarza zakończone niepowodzeniem! " + ex.getMessage());
            return;
        }
        commentsProject(id);
        return;
    }

}
