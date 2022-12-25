package classification;
import application.Main;
import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.NotAllowedOperationException;
import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.ClassificationContent;
import readDB.ClassificationsList;
import variables.Variables;

import java.sql.Connection;
import java.util.Scanner;

public abstract class ClassificationChoice{
    private static final Logger logger = LogManager.getLogger(ClassificationChoice.class);

    public static void chooseClassification(String type, Connection connection) throws NotAllowedOperationException{

        logger.debug("Enter to ClassificationChoice with following args => type "+type +"Connection: "+ connection );

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
                    IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
                    fileAttribute[1] = EncryptionFile.encryptAndDecrypt(fileAttribute[1]);
                }
                ClassificationController.controlClassification(connection,fileAttribute,type,Variables.BY_ATTRIBUTES);
        }
        else{
                fileAttribute = ClassificationContent.getContent(connection,choice);
                if (fileAttribute[0] == null){

                    logger.error("No class with this name ..");
                    throw new NotAllowedOperationException("There is no classification available with this name, try again");
                }
                ClassificationController.controlClassification(connection,fileAttribute, type,Variables.BY_CLASSIFICATION);
        }
        }catch (SqlQueryException e) {
            logger.error("Failed create classification");
            System.err.println(e.getMessage());
        }
        logger.debug("Exist from ClassificationChoice");
    }

}