package classification;
import encryption.EncryptionFile;
import encryption.IEncrAndDecrption;
import exceptions.NotAllowedOperationException;
import exceptions.SqlQueryException;
import readDB.ClassificationContent;
import readDB.ClassificationsList;
import variables.Variables;

import java.sql.Connection;
import java.util.Scanner;

public abstract class ClassificationChoice{

    public static void chooseClassification(String type, Connection connection) throws NotAllowedOperationException{
        System.out.println("According to: ");
        System.out.println("Name");
        System.out.println("Type");
        System.out.println("Size (L/M/S)");
        try {
            ClassificationsList.displayClassification(connection);

        Scanner sc = new Scanner(System.in);
        System.out.print("enter your choice: ");
        String choice = sc.next() ;

        String fileAttribute[] = new String[3];
            if(choice.equalsIgnoreCase(Variables.FILE_NAME) || choice.equalsIgnoreCase(Variables.FILE_TYPE)
                    || choice.equalsIgnoreCase(Variables.FILE_SIZE)){
            fileAttribute[0] = choice.toLowerCase();
            System.out.print("Enter the value: ");
            fileAttribute[1] = sc.next();
                if(fileAttribute[0].equals("name")){
                    IEncrAndDecrption EncryptionFile = new EncryptionFile();
                    fileAttribute[1] = EncryptionFile.IncAndDec(fileAttribute[1]);
                }
                ClassificationController.controlClassification(connection,fileAttribute,type,Variables.BY_ATTRIBUTES);
        }
        else{
                fileAttribute = ClassificationContent.getContent(connection,choice);
                if (fileAttribute[0] == null){
                    throw new NotAllowedOperationException("There is no classification available with this name, try again");
                }
                ClassificationController.controlClassification(connection,fileAttribute, type,Variables.BY_CLASSIFICATION);
        }
        }catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
    }

}