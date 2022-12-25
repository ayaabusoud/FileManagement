package menu;

import exceptions.NotIntegerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class NotIntegerInput {
    private static final Logger logger = LogManager.getLogger(ClassificationMenu.class);

    public static int scanInteger(int authChoice)throws NotIntegerException {
        logger.debug("Enter to scanInteger with args => " + authChoice);
        Scanner sc = new Scanner(System.in);
        try {
            authChoice = sc.nextInt();
            logger.debug("Integer number entered ");
            return authChoice;
        }catch (InputMismatchException e){
            logger.error("Error input ");
            throw new NotIntegerException("The input is not an integer");
        }
    }
}
