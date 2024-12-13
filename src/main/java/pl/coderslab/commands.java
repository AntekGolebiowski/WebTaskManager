package pl.coderslab;

import java.util.Scanner;
import java.util.logging.Logger;

public class commands {
    private static final Logger logger = LoggerManager.getLogger();

    public static boolean onUserCommand(String command) {
        if (command == null || command.isEmpty()) {
            return false;
        }
        if (users.whereIsUser == Enums.PROJECT_COMMENTS) {
            if ("komentuj".equalsIgnoreCase(command)) {
                projects.commentProject(users.userProjectPreviewID);
                return true;

            } else if ("wyjdz".equalsIgnoreCase(command)) {
                projects.previewProject(users.userProjectPreviewID);
                return true;

            }
        } else if (users.whereIsUser == Enums.PROJECT_PREVIEW) {
            if (command.startsWith("edytuj nazwa")) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                projects.Projects project = projects.findProjectById(projects.projects, users.userProjectPreviewID);
                if (project == null) {
                    System.out.println(config.BRIGHT_BLACK + "Nie znaleziono projektu.");
                    return true;
                }
                if("edytuj nazwa  ".length() > command.length()) {
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ NAZWA #nowanazwa" + config.RESET);
                    return true;
                }
                String newName = command.substring("edytuj nazwa ".length());
                System.out.println(config.BRIGHT_BLACK + "Aktualna nazwa: " + config.RESET + project.getName());
                System.out.println(config.BRIGHT_BLACK + "Nowa nazwa: " + config.RESET + newName);
                System.out.println("Czy na pewno chcesz zmienić nazwę projektu id #" + users.userProjectPreviewID + "?");
                System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                while (!"tak".equalsIgnoreCase(line) && !"nie".equalsIgnoreCase(line)) {
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
                    line = scanner.nextLine();
                }
                if ("tak".equalsIgnoreCase(line)) {
                    logger.info("Nazwa projektu o id #" + users.userProjectPreviewID + " została zmieniona.");
                    projects.projectsEditName(users.userProjectPreviewID, newName);
                }
                projects.previewProject(users.userProjectPreviewID);
                return true;

            } else if (command.startsWith("edytuj opis")) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                projects.Projects project = projects.findProjectById(projects.projects, users.userProjectPreviewID);
                if (project == null) {
                    System.out.println(config.BRIGHT_BLACK + "Nie znaleziono projektu.");
                    return true;
                }
                if("edytuj opis  ".length() > command.length()) {
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ OPIS #nowyopis" + config.RESET);
                    return true;
                }
                String newDescription = command.substring("edytuj opis ".length());
                System.out.println(config.BRIGHT_BLACK + "Aktualny opis: " + config.RESET + project.getDescription());
                System.out.println(config.BRIGHT_BLACK + "Nowy opis: " + config.RESET + newDescription);
                System.out.println("Czy na pewno chcesz zmienić opis projektu id #" + users.userProjectPreviewID + "?");
                System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                while (!"tak".equalsIgnoreCase(line) && !"nie".equalsIgnoreCase(line)) {
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "NIE" + config.RESET);
                    line = scanner.nextLine();
                }
                if ("tak".equalsIgnoreCase(line)) {
                    logger.info("Opis projektu o id #" + users.userProjectPreviewID + " została zmieniona.");
                    projects.projectsEditDescription(users.userProjectPreviewID, newDescription);
                }
                projects.previewProject(users.userProjectPreviewID);
                return true;

            } else if (command.startsWith("edytuj pilny")) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                if (command.equals("edytuj pilny tak")) {
                    projects.projectsEditUrgent(users.userProjectPreviewID, true);
                    projects.previewProject(users.userProjectPreviewID);
                } else if (command.equals("edytuj pilny nie")) {
                    projects.projectsEditUrgent(users.userProjectPreviewID, false);
                    projects.previewProject(users.userProjectPreviewID);
                } else {
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ PILNY TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "EDYTUJ PILNY NIE" + config.RESET);
                }
                return true;

            } else if (command.startsWith("edytuj ukonczony")) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                if (command.equals("edytuj ukonczony tak")) {
                    projects.projectsEditDone(users.userProjectPreviewID, true);
                    projects.previewProject(users.userProjectPreviewID);
                } else if (command.equals("edytuj ukonczony nie")) {
                    projects.projectsEditDone(users.userProjectPreviewID, false);
                    projects.previewProject(users.userProjectPreviewID);
                } else {
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ UKONCZONY TAK" + config.RESET + " lub " + config.BLUE_BACKGROUND + "EDYTUJ UKONCZONY NIE" + config.RESET);
                }
                return true;

            } else if ("edytuj postep".equalsIgnoreCase(command)) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP ANALIZA 0-100" + config.RESET + " - zmienia postęp prac nad analizą potrzeb klienta");
                System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP STRUKTURA 0-100" + config.RESET + " - zmienia postęp prac nad projektowaniem struktury");
                System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP UIUX 0-100" + config.RESET + " - zmienia postęp prac nad projektowaniem UI/UX");
                System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP FRONTEND 0-100" + config.RESET + " - zmienia postęp prac nad tworzeniem frontendu");
                System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP BACKEND 0-100" + config.RESET + " - zmienia postęp prac nad tworzeniem backendu");
                System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP TESTOWANIE 0-100" + config.RESET + " - zmienia postęp prac nad testowaniem projektu");
                System.out.println(config.BRIGHT_BLACK_BACKGROUND + "EDYTUJ POSTEP OPTYMALIZACJA 0-100" + config.RESET + " - zmienia postęp prac nad optymalizacją");
                return true;

            } else if (command.startsWith("edytuj postep ")) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                if(command.startsWith("edytuj postep analiza ")) {
                    try{
                        int value = Integer.parseInt(command.substring("edytuj postep analiza ".length()));
                        if(value < 0 || value > 100) {
                            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ POSTĘP ANALIZA 0-100" + config.RESET);
                            return true;
                        }
                        projects.projectsEditProgress(users.userProjectPreviewID, value, Enums.PROJECT_CHANGE_PROGRESS_ANALYSIS);
                        projects.previewProject(users.userProjectPreviewID);
                        logger.info("Zaktualizowano postęp prac.");
                    } catch (NumberFormatException e) {
                        logger.warning("Podano nieprawidłową wartość postępu! " + e.getMessage());
                    }
                }else if(command.startsWith("edytuj postep struktura ")) {
                    try{
                        int value = Integer.parseInt(command.substring("edytuj postep struktura ".length()));
                        if(value < 0 || value > 100) {
                            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ POSTĘP STRUKTURA 0-100" + config.RESET);
                            return true;
                        }
                        projects.projectsEditProgress(users.userProjectPreviewID, value, Enums.PROJECT_CHANGE_PROGRESS_STRUCTURE);
                        projects.previewProject(users.userProjectPreviewID);
                        logger.info("Zaktualizowano postęp prac.");
                    } catch (NumberFormatException e) {
                        logger.warning("Podano nieprawidłową wartość postępu! " + e.getMessage());
                    }

                }else if(command.startsWith("edytuj postep uiux ")) {
                    try{
                        int value = Integer.parseInt(command.substring("edytuj postep uiux ".length()));
                        if(value < 0 || value > 100) {
                            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ POSTĘP UIUX 0-100" + config.RESET);
                            return true;
                        }
                        projects.projectsEditProgress(users.userProjectPreviewID, value, Enums.PROJECT_CHANGE_PROGRESS_DESIGN);
                        projects.previewProject(users.userProjectPreviewID);
                        logger.info("Zaktualizowano postęp prac.");
                    } catch (NumberFormatException e) {
                        logger.warning("Podano nieprawidłową wartość postępu! " + e.getMessage());
                    }

                }else if(command.startsWith("edytuj postep frontend ")) {
                    try{
                        int value = Integer.parseInt(command.substring("edytuj postep frontend ".length()));
                        if(value < 0 || value > 100) {
                            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ POSTĘP FRONTEND 0-100" + config.RESET);
                            return true;
                        }
                        projects.projectsEditProgress(users.userProjectPreviewID, value, Enums.PROJECT_CHANGE_PROGRESS_FRONTEND);
                        projects.previewProject(users.userProjectPreviewID);
                        logger.info("Zaktualizowano postęp prac.");
                    } catch (NumberFormatException e) {
                        logger.warning("Podano nieprawidłową wartość postępu! " + e.getMessage());
                    }

                }else if(command.startsWith("edytuj postep backend ")) {
                    try{
                        int value = Integer.parseInt(command.substring("edytuj postep backend ".length()));
                        if(value < 0 || value > 100) {
                            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ POSTĘP BACKEND 0-100" + config.RESET);
                            return true;
                        }
                        projects.projectsEditProgress(users.userProjectPreviewID, value, Enums.PROJECT_CHANGE_PROGRESS_BACKEND);
                        projects.previewProject(users.userProjectPreviewID);
                        logger.info("Zaktualizowano postęp prac.");
                    } catch (NumberFormatException e) {
                        logger.warning("Podano nieprawidłową wartość postępu! " + e.getMessage());
                    }

                }else if(command.startsWith("edytuj postep testowanie ")) {
                    try{
                        int value = Integer.parseInt(command.substring("edytuj postep testowanie ".length()));
                        if(value < 0 || value > 100) {
                            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ POSTĘP TESTOWANIE 0-100" + config.RESET);
                            return true;
                        }
                        projects.projectsEditProgress(users.userProjectPreviewID, value, Enums.PROJECT_CHANGE_PROGRESS_TESTING);
                        projects.previewProject(users.userProjectPreviewID);
                        logger.info("Zaktualizowano postęp prac.");
                    } catch (NumberFormatException e) {
                        logger.warning("Podano nieprawidłową wartość postępu! " + e.getMessage());
                    }

                }else if(command.startsWith("edytuj postep optymalizacja ")) {
                    try{
                        int value = Integer.parseInt(command.substring("edytuj postep optymalizacja ".length()));
                        if(value < 0 || value > 100) {
                            System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ POSTĘP OPTYMALIZACJA 0-100" + config.RESET);
                            return true;
                        }
                        projects.projectsEditProgress(users.userProjectPreviewID, value, Enums.PROJECT_CHANGE_PROGRESS_OPTIMIZATION);
                        projects.previewProject(users.userProjectPreviewID);
                        logger.info("Zaktualizowano postęp prac.");
                    } catch (NumberFormatException e) {
                        logger.warning("Podano nieprawidłową wartość postępu! " + e.getMessage());
                    }

                }else{
                    return onUserCommand("edytuj postep");
                }
                return true;

            } else if (command.startsWith("edytuj termin")) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                if(command.length() <= "edytuj termin ".length()) {
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ TERMIN DD/MM/YYYY" + config.RESET);
                    return true;
                }
                long timestamp = methods.convertDateToTimestamp(command.substring("edytuj termin ".length()));
                if(timestamp == -1){
                    System.out.println(config.BRIGHT_BLACK + "Wprowadzono nieprawidłową datę" + config.RESET);
                    System.out.println("Wprowadź " + config.BLUE_BACKGROUND + "EDYTUJ TERMIN DD/MM/YYYY" + config.RESET);
                    return true;
                }
                projects.projectsEditDeadline(users.userProjectPreviewID, timestamp);
                logger.info("Zaktualizowano termin realizacji projektu.");
                projects.previewProject(users.userProjectPreviewID);
                return true;

            } else if ("komentarze".equalsIgnoreCase(command)) {
                projects.commentsProject(users.userProjectPreviewID);
                return true;

            } else if ("edytuj".equalsIgnoreCase(command)) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                projects.editProject(users.userProjectPreviewID);
                return true;

            } else if ("kasuj".equalsIgnoreCase(command)) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                projects.deleteProject(users.userProjectPreviewID);
                return true;

            } else if ("wyjdz".equalsIgnoreCase(command)) {
                projects.showProjects();
                return true;

            }
        } else if (users.whereIsUser == Enums.PROJECTS) {
            if (command.startsWith("podglad")) {
                if ("podglad ".length() < command.length()) {
                    int id = -1;
                    try {
                        id = Integer.parseInt(command.substring("podglad ".length()));
                    } catch (NumberFormatException e) {
                        System.out.println(config.RED + "Podano nieprawidłowe id." + config.RESET);
                        return true;
                    }

                    if (id < 0) {
                        System.out.println(config.BRIGHT_BLACK + "Wprowadzono nieprawidłowe id." + config.RESET);
                        System.out.println("Użyj: " + config.BRIGHT_BLACK_BACKGROUND + "PODGLAD #ID" + config.RESET);
                        return true;
                    }

                    projects.previewProject(id);
                    return true;
                }
                System.out.println("Użyj: " + config.BRIGHT_BLACK_BACKGROUND + "PODGLAD #ID" + config.RESET);
                return true;

            } else if ("utworz".equalsIgnoreCase(command)) {
                if(!users.userAdmin)
                {
                    System.out.println(config.BRIGHT_BLACK + "Nie posiadasz uprawnień administratora!" + config.RESET);
                    return true;
                }
                projects.createProject();
                return true;

            } else if (command.startsWith("sortuj")) {
                if ("sortuj id".equalsIgnoreCase(command)) {
                    projects.showProjects();
                } else if ("sortuj utworzono".equalsIgnoreCase(command)) {
                    projects.showProjects("date");
                } else if ("sortuj deadline".equalsIgnoreCase(command)) {
                    projects.showProjects("deadline");
                } else if ("sortuj nazwa".equalsIgnoreCase(command)) {
                    projects.showProjects("name");
                } else {
                    System.out.println("Dostępne: " + config.BRIGHT_BLACK_BACKGROUND + "SORTUJ ID" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "SORTUJ UTWORZONO" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "SORTUJ DEADLINE" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "SORTUJ NAZWA" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "SORTUJ POSTEP" + config.RESET);
                }
                return true;

            } else if (command.startsWith("wyswietlaj")) {
                if ("wyswietlaj wszystko".equalsIgnoreCase(command)) {
                    projects.projectsToShow = Enums.ALL;
                    projects.showProjects();
                } else if ("wyswietlaj nieukonczone".equalsIgnoreCase(command)) {
                    projects.projectsToShow = Enums.UNDONE;
                    projects.showProjects();
                } else if ("wyswietlaj pilne".equalsIgnoreCase(command)) {
                    projects.projectsToShow = Enums.URGENT;
                    projects.showProjects();
                } else {
                    System.out.println("Dostępne: " + config.BRIGHT_BLACK_BACKGROUND + "WYSWIETLAJ WSZYSTKO" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "WYSWIETLAJ NIEUKONCZONE" + config.RESET + " " + config.BRIGHT_BLACK_BACKGROUND + "WYSWIETLAJ PILNE" + config.RESET);
                }
                return true;
            }
        }

        if ("wyloguj".equalsIgnoreCase(command) || "zamknij".equalsIgnoreCase(command)) {
            System.out.println("Do widzenia!");
            Main.ShoutDown();
            return true;
        }
        return false;
    }
}
