package factory;

import operations.createClassification.ClassificationCreation;
import operations.delete.Deletion;
import operations.export.Export;
import operations.importOperation.Import;
import operations.operation.Operation;
import operations.read.Reading;
import operations.rollback.Rollback;
import users.UserTypes;

public class Factory implements IFactory{
    public  Operation createUserFunctionality(UserTypes type){
        Operation obj = new Operation();
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
