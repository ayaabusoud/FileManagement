package menu;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class ClassificationMenu {
    private static final Logger logger = LogManager.getLogger(ClassificationMenu.class);

    public static void classificationMenu(){
        logger.info("Enter to classificationMenu");
        System.out.println("1.File name");
        System.out.println("2.File Type");
        System.out.println("3.Size (L/M/S)");
    }
    public static void doneMenu()
    {
        logger.info("Enter to doneMenu");
        System.out.println("4.Done");
    }
}