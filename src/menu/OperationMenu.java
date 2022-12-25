package menu;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class OperationMenu {
    private static final Logger logger = LogManager.getLogger(OperationMenu.class);

    public static void readerMenu(){
        logger.info("Enter to readerMenu");
        System.out.println("-----menu-----");
        System.out.println("0.Log out");
        System.out.println("1.Read a file");
    }
    public static void staffMenu(){
        logger.info("Enter to staffMenu");
        readerMenu();
        System.out.println("2.Import a file");
        System.out.println("3.Rollback");
        System.out.println("4.Export a file");
        System.out.println("5.create Classification");
    }
    public static void adminMenu(){
        logger.info("Enter to adminMenu");
        staffMenu();
        System.out.println("6.Delete a file");
    }


}
