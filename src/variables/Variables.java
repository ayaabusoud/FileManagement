package variables;

public class Variables {

    public static final String FILE_TABLE = "file";
    public final static int OVERWRITE_VERSION_CONTROL_TYPE = 2;
    public final static int DEFAULT_VERSION_CONTROL_TYPE = 1;
    public final static int ONE_VERSION_TYPE = 0;
    public final static int LAST_VERSION = 1;
    public static final String BACKUP_TABLE = "backup";
    public static final String  USER_TABLE = "user" ;
    public static final String  CLASSIFICATION_TABLE = "classification" ;
    public static final String NO_Condition = "Any";
    public static final String READ_FILES = "read";
    public static final String DELETE_FILES = "delete" ;
    public static final String EXPORT_FILES = "export" ;
    public static final String FILE_NAME = "name" ;
    public static final String  FILE_TYPE = "type" ;
    public static final String  FILE_SIZE = "size" ;
    public static final String  EQUALS = " = " ;
    public static final String  NOT_EQUALS = " != " ;
    public static final String  BY_CLASSIFICATION = "classification" ;
    public static final String  BY_ATTRIBUTES = "attributes" ;
    public static final String  NOT_DEFAULT = "yes" ;
    public static final String  DEFAULT = "no" ;

    public static boolean adminUser = false;
    public static boolean staffUser = false;
    public static boolean readerUser = false;
}