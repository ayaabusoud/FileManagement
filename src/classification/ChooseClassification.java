package classification;
import controller.ClassificationController;
import controller.OperationController;
import exceptions.NotAllowedOperationException;
import exceptions.SqlQueryException;
import file.FileInfo;
import file.FileNameAndType;
import readDB.DisplayClassifications;
import readDB.GetClassificationContent;
import readDB.GetFileInfo;
import variables.Variables;

import java.sql.Connection;
import java.util.Scanner;

public class ChooseClassification {

    public static void classificationChoice(String type, Connection connection) throws NotAllowedOperationException{
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
//            FileInfo file = null;
//            file = GetFileInfo.getInfo(connection, FileNameAndType.splitNameAndType(fileNameAndType));
            if(choice.equalsIgnoreCase(Variables.FILE_NAME) || choice.equalsIgnoreCase(Variables.FILE_TYPE) || choice.equalsIgnoreCase(Variables.FILE_SIZE)){
            fileAttribute[0] = choice.toLowerCase();
            System.out.print("Enter the value: ");
            fileAttribute[1] = sc.next();
            OperationController.control(connection,fileAttribute,type);
        }
        else{

                fileAttribute = GetClassificationContent.get(connection,choice);
                if (fileAttribute[0] == null){
                    throw new NotAllowedOperationException("There is no classification available with this name, try again");
                }

            ClassificationController.control(connection,fileAttribute, type);
        }
        }catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
    }

}