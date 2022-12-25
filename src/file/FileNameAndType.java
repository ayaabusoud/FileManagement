package file;

import encryption.HashedPassword;
import exceptions.IncorrectFileException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class FileNameAndType {
    private static final Logger logger = LogManager.getLogger(FileNameAndType.class);

    public static FileInformation splitNameAndType(String filePath) throws ArrayIndexOutOfBoundsException{
        logger.debug("enter splitNameAndType function with file path: "+filePath);
        String[] nameAndType = null;
        FileInformation file = new FileInformation();

        if (!filePath.contains("/")){
            nameAndType = filePath.split("\\.");
        }else {
            nameAndType[0] =  filePath.substring(filePath.lastIndexOf("/")+1,filePath.indexOf("."));
            nameAndType[1] = filePath.substring(filePath.lastIndexOf(".")+1);
        }
        try {
            file.setName(nameAndType[0]);
            file.setType(nameAndType[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new IncorrectFileException("incorrect name.type input");
        }


        logger.debug("file name: "+file.getName() + "file type: "+file.getType());
        logger.debug("exit splitNameAndType function");
        return file;
    }
}
