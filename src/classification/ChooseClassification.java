package classification;
import controller.ClassificationController;
import controller.OperationController;
import exceptions.RunTimeException;
import readDB.DisplayClassifications;
import readDB.GetClassificationContent;
import variables.Variables;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ChooseClassification {

    public static void classificationChoice(String type, Connection connection) {
        System.out.println("According to: ");
        System.out.println("Name");
        System.out.println("Type");
        System.out.println("Size (L/M/S)");
        try {
            DisplayClassifications.display(connection);

        Scanner sc = new Scanner(System.in);
        System.out.print("enter your choice: ");
        String choice = sc.next() ;

        String fileAttribute[] = new String[3];
        if(choice.equalsIgnoreCase(Variables.FILE_NAME) || choice.equalsIgnoreCase(Variables.FILE_TYPE) || choice.equalsIgnoreCase(Variables.FILE_SIZE)){
            fileAttribute[0] = choice.toLowerCase();
            System.out.print("Enter the value: ");
            fileAttribute[1] = sc.next();
            OperationController.control(connection,fileAttribute,type);
        }
        else{

                fileAttribute = GetClassificationContent.get(connection,choice);
                ClassificationController.control(connection,fileAttribute,type);

        }
        }catch (RunTimeException e) {
            System.err.println(e.getMessage());
        }
    }

}