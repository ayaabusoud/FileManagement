package file;

public class FileNameAndType {
    public static FileInfo splitNameAndType(String filePath){
        String[] nameAndType = null;
        FileInfo file = new FileInfo();
        if (!filePath.contains("/")){
            nameAndType = filePath.split("\\.");

        }
        nameAndType[0] =  filePath.substring(filePath.lastIndexOf("/")+1,filePath.indexOf("."));
        nameAndType[1] = filePath.substring(filePath.lastIndexOf(".")+1);

        file.setName(nameAndType[0]);
        file.setType(nameAndType[1]);
        return file;
    }
}
