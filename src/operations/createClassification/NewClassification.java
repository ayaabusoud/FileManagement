package operations.createClassification;

import exceptions.SqlQueryException;
import menu.ClassificationMenu;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.ExistenceChecking;
import variables.Variables;

import java.sql.Connection;
import java.util.Scanner;

public abstract class NewClassification {
    private static final Logger logger = LogManager.getLogger(NewClassification.class);
    public static String[] createClassification(Connection connection) {
        logger.debug("enter createClassification function");
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        int choice ;
        String fileName = "Any";
        String fileType = "Any";
        String fileSize = "Any";
        String[] classificationAttributes = new String[4];

        boolean loop = true;
        String name = null;
        while (loop) {
            System.out.print("Enter the classification name: ");
            classificationAttributes[0] = sc.next();
            logger.debug("new classification name: "+classificationAttributes[0]);
            try {
                loop = ExistenceChecking.isExists(connection, Variables.CLASSIFICATION_TABLE, classificationAttributes[0]);
            }  catch (SqlQueryException e) {
                System.err.println(e.getMessage());
            }
            if (loop) {
                System.out.println("The classification name is already exit, choose another one..");
            }
        }

        System.out.println("create classification according to: (at least one choice)");

        ClassificationMenu.classificationMenu();
        ClassificationMenu.doneMenu();
        do{
            System.out.print("Enter a choice number: ");
            choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.print("Enter file name: ");
                    fileName = sc.next();
                    break;
                case 2:
                    System.out.print("Enter file type: ");
                    fileType = sc.next();
                    break;
                case 3:
                    System.out.print("Enter file size: ");
                    fileSize = sc.next();
                    break;
                case 4:
                    exit = false;
                    break;
            }
        }while (exit);

        classificationAttributes[1] =  fileName +","+fileType+","+fileSize;
        classificationAttributes[2] = "Files name: "+fileName+ ", Files Type: "+fileType+ ", Files Size: "+fileSize;

        logger.debug("classification according to:"+classificationAttributes[1]);
        logger.debug("exist create function");
        return classificationAttributes;
    }
}
