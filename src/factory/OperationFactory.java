package factory;

import operations.createClassification.ClassificationCreation;
import operations.delete.Deletion;
import operations.export.Export;
import operations.importOperation.Import;
import users.User;
import operations.read.Reading;
import operations.rollback.Rollback;
import users.UserTypes;

public class OperationFactory implements IFactory{
    public  User create(UserTypes type){
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
        return obj;
    }
}
