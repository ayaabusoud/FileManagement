package classification;

import menu.ClassificationMenu;

import java.sql.Connection;
import java.util.Scanner;

public class NewClassification {


    public static String[] create(Connection connection) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        int choice ;
        String fileName = "Any";
        String fileType = "Any";
        String fileSize = "Any";
        String[] classificationAttributes = new String[4];
 //       String formattedContext = null;


        classificationAttributes[0] = CheckUniqueClassificationName.checkName(connection);

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


        return classificationAttributes;
    }
}