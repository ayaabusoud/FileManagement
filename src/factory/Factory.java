package factory;

import operations.createClassification.CreateClassification;
import operations.delete.Delete;
import operations.export.Export;
import operations.importOperation.Import;
import operations.operation.Operation;
import operations.read.Read;
import operations.rollback.Rollback;
import users.UserTypes;

public class Factory {
    public static Operation createUserFunctionality(UserTypes type){
        Operation obj = new Operation();
        if (type.equals(UserTypes.Admin)){
            obj.setImportFile(new Import());
            obj.setDelete(new Delete());
            obj.setExport(new Export());
            obj.setRollback(new Rollback());
            obj.setRead(new Read());
            obj.setClassification(new CreateClassification());
        }
        if (type.equals(UserTypes.Staff)){
        obj.setImportFile(new Import());
        obj.setExport(new Export());
        obj.setRollback(new Rollback());
        obj.setRead(new Read());
        obj.setClassification(new CreateClassification());
        }
        else{
            obj.setRead(new Read());
        }
        return obj;
    }
}
