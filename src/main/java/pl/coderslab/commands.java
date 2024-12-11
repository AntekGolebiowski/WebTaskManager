package pl.coderslab;

import java.util.logging.Logger;

public class commands {
    private static final Logger logger = LoggerManager.getLogger();
    public static boolean onUserCommand(String command)
    {
        if(command == null || command.isEmpty())
        {
            return false;
        }
        if(users.whereIsUser == Enums.PROJECTS)
        {
            if("podlgad".equalsIgnoreCase(command))
            {
                System.out.println("");
                return true;
            }
        }
        return true;
    }
}
