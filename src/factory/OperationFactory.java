package factory;

import application.Main;
import operations.createClassification.ClassificationCreation;
import operations.delete.Deletion;
import operations.export.Export;
import operations.importOperation.Import;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import users.User;
import operations.read.Reading;
import operations.rollback.Rollback;
import users.UserTypes;

public class OperationFactory implements IFactory{
    private static final Logger logger = LogManager.getLogger(OperationFactory.class);
    public  User create(UserTypes type){
        logger.debug("Enter to OperationFactory with following args => UserTypes: "+type);
        User obj = new User();
        if (type.equals(UserTypes.Admin)){
            obj.setImportFile(new Import());
            obj.setDelete(new Deletion());
            obj.setExport(new Export());
            obj.setRollback(new Rollback());
            obj.setRead(new Reading());
            obj.setClassification(new ClassificationCreation());
        }
        if (type.equals(UserTypes.Staff)){
        obj.setImportFile(new Import());
        obj.setExport(new Export());
        obj.setRollback(new Rollback());
        obj.setRead(new Reading());
        obj.setClassification(new ClassificationCreation());
        }
        else if(type.equals(UserTypes.Reader)){
            obj.setRead(new Reading());
        }
        else {
            logger.error("Failed on choice option (admin, staff, reader)");
        }
        return obj;
    }
}
