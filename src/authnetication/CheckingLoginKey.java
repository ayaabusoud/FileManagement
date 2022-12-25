package authnetication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public abstract class CheckingLoginKey {
    private static final Logger logger = LogManager.getLogger(CheckingLoginKey.class);

    public static void checkUserKey(int UserKey){
        logger.debug(" Call the checkUserKey function ");

        Scanner sc = new Scanner(System.in);
        int key ;
        System.out.print("enter your key, please: ");
        key = sc.nextInt();
        while (key != UserKey)
        {
            System.out.print("The key you entered is incorrect,please re-enter: ");
            key = sc.nextInt();
            logger.debug("Failed, key mismatch");
        }
        logger.debug("Succeeded, keys matched");
        logger.debug("Close the checkUserKey function ");
    }
}