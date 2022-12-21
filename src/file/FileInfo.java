package file;
import java.sql.Blob;

public class FileInfo {
    private String name;
    private String type;
    private Blob context;
    private int version;
    private int lastVersion;
    private int versionType;
    private String size;

    public FileInfo(){
        this.name=null;
        this.type=null;
        this.context=null;
        this.lastVersion=1;
        this.version=1;
        this.versionType=0;
        this.size=null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Blob getContext() {
        return context;
    }

    public void setContext(Blob context) {
        this.context = context;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(int lastVersion) {
        this.lastVersion = lastVersion;
    }

    public int getVersionType() {
        return versionType;
    }

    public void setVersionType(int versionType) {
        this.versionType = versionType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
