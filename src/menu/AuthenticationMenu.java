package menu;

import factory.OperationFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class AuthenticationMenu {

    private static final Logger logger = LogManager.getLogger(AuthenticationMenu.class);

    public static void AuthMenu() {
        logger.info("Enter to AuthMenu");
        System.out.println("1.Signup");
        System.out.println("2.Login");
        System.out.print("choose Operation number: ");
    }
    public static void loginMenu()
    {
        logger.info("Enter to loginMenu");
        System.out.println("1.Admin");
        System.out.println("2.Staff");
        System.out.println("3.Reader");
        System.out.print("choose your account type:");
    }
}
