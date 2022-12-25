package users;

import exceptions.IncorrectFilePathException;
import exceptions.NotAllowedOperationException;
import operations.createClassification.IClassificationCreationBehavior;
import operations.delete.IDeletionBehavior;
import operations.export.IExportBehavior;
import operations.importOperation.IImportBehavior;
import operations.read.IReadingBehavior;
import operations.rollback.IRollbackBehavior;

import java.sql.Connection;

public class User implements IUser {
    private IDeletionBehavior delete;
    private IExportBehavior export;
    private IImportBehavior importFile;
    private IRollbackBehavior rollback;
    private IReadingBehavior read;
    private IClassificationCreationBehavior classification;

    public User(){
        this.delete = null;
        this.export = null;
        this.importFile = null;
        this.rollback = null;
        this.read = null;
        this.classification = null;
    }

    @Override
    public void importFiles(Connection connection) throws NotAllowedOperationException{
    if(importFile == null){
      throw new NotAllowedOperationException("This operation is not allowed");
     }
    try {
          importFile.importFile(connection);
        }
    catch (IncorrectFilePathException e){
        System.err.println(e.getMessage());
        }

    }
    @Override
    public void deleteFiles(Connection connection) throws NotAllowedOperationException{
        if(delete == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
            delete.delete(connection);
    }
    @Override
    public void readFiles(Connection connection) throws NotAllowedOperationException {
        if(read == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        read.read(connection);
    }
    @Override
    public void exportFile(Connection connection ) throws NotAllowedOperationException{
        if(export == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
            export.export(connection);
    }
    @Override
    public void rollBack(Connection connection) throws NotAllowedOperationException{
        if(rollback == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
            rollback.rollbackVersion(connection);
    }
    @Override
    public void createClassification(Connection connection) throws NotAllowedOperationException {
        if(classification == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
            classification.create(connection);
    }

    public IDeletionBehavior getDelete() {
        return delete;
    }

    public void setDelete(IDeletionBehavior delete) {
        this.delete = delete;

    }

    public IExportBehavior getExport() {
        return export;
    }

    public void setExport(IExportBehavior export) {
        this.export = export;
    }

    public IImportBehavior getImportFile() {
        return importFile;
    }

    public void setImportFile(IImportBehavior importFile) {
        this.importFile = importFile;
    }

    public IRollbackBehavior getRollback() {
        return rollback;
    }

    public void setRollback(IRollbackBehavior rollback) {
        this.rollback = rollback;
    }

    public IReadingBehavior getRead() {
        return read;
    }

    public void setRead(IReadingBehavior read) {
        this.read = read;
    }

    public IClassificationCreationBehavior getClassification() {
        return classification;
    }

    public void setClassification(IClassificationCreationBehavior classification) {
        this.classification = classification;
    }

}