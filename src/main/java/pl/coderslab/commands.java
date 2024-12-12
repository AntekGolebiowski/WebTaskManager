package pl.coderslab;

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

            }else if ("wyjdz".equalsIgnoreCase(command)) {
                projects.previewProject(users.userProjectPreviewID);
                return true;

            }
        } else if (users.whereIsUser == Enums.PROJECT_PREVIEW) {
            if ("komentarze".equalsIgnoreCase(command)) {
                projects.commentsProject(users.userProjectPreviewID);
                return true;

            }else if ("edytuj".equalsIgnoreCase(command)) {
                projects.editProject(users.userProjectPreviewID);
                return true;

            }else if ("skasuj".equalsIgnoreCase(command)) {
                projects.deleteProject(users.userProjectPreviewID);
                return true;

            }else if ("wyjdz".equalsIgnoreCase(command)) {
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

        if ("pomoc".equalsIgnoreCase(command)) {
            methods.help();
            return true;
        }

        if ("wyloguj".equalsIgnoreCase(command) || "zamknij".equalsIgnoreCase(command)) {
            System.out.println("Do widzenia!");
            Main.ShoutDown();
            return true;
        }
        return false;
    }
}
