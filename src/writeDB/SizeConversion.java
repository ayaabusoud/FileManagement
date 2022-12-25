package writeDB;

import exceptions.FileSizeException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public abstract class SizeConversion {
    private static final Logger logger = LogManager.getLogger(SizeConversion.class);
    public static String convertSize(InputStream inputStream) throws FileSizeException {
        logger.info("enter convertSize function ");
        String Size = null ;
        try{
            if(inputStream.available() < 500){
                Size = "S" ;
            } else  if (inputStream.available() >= 500 && inputStream.available() <= 700 ){
                Size = "M" ;
            } else {
                Size = "L" ;
            }
        } catch (IOException e){
            throw  new FileSizeException("Failed convert size ..") ;
        }
        logger.info("exit convertSize function ");
        return Size ;
    }
}
