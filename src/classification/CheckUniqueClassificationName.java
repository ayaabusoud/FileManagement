package classification;

import exceptions.RunTimeException;
import readDB.CheckClassificationExistences;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckUniqueClassificationName {
    public static String checkName(Connection connection){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        String name = null;
        while (loop){
            System.out.print("Enter the classification name: ");
            name = sc.next();
            try {
                loop =CheckClassificationExistences.isExist(connection ,name) ;
                if(loop){
                    System.out.println("The classification name is already exit, choose another one..");
                }
            }  catch (RunTimeException e) {
                System.err.println(e.getMessage());
            }
        }
        return name;
    }
}
