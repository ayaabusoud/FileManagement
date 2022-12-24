package operations.operation;

import exceptions.IncorrectFilePathException;
import exceptions.NotAllowedOperationException;
import file.FileInfo;
import operations.createClassification.ICreateClassification;
import operations.delete.IDelete;
import operations.export.IExport;
import operations.importOperation.IImport;
import operations.read.IRead;
import operations.rollback.IRollback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Operation implements IOperation {
    private IDelete delete;
    private IExport export;
    private IImport importFile;
    private IRollback rollback;
    private IRead read;



    private ICreateClassification classification;

    public Operation(){
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
     }try {
            importFile.importFile(connection);
        }catch (IncorrectFilePathException e){
        System.err.println(e.getMessage());
        }

    }
    @Override
    public void deleteFiles(Connection connection) throws SQLException, NotAllowedOperationException {
        if(delete == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        delete.delete(connection);
    }
    @Override
    public void readFiles(Connection connection) throws SQLException, NotAllowedOperationException {
        if(read == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        read.read(connection);
    }
    @Override
    public void exportFile(Connection connection , FileInfo nameOfFile) throws NotAllowedOperationException{
        if(export == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        try {
            export.export(connection , nameOfFile);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void rollBack(Connection connection,String path) throws SQLException, IOException, NotAllowedOperationException{
        if(rollback == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        rollback.rollbackVersion(connection,path);
    }
    @Override
    public void createClassification(Connection connection) throws SQLException, NotAllowedOperationException {
        if(classification == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        classification.create(connection);
    }

    public IDelete getDelete() {
        return delete;
    }

    public void setDelete(IDelete delete) {
        this.delete = delete;

    }

    public IExport getExport() {
        return export;
    }

    public void setExport(IExport export) {
        this.export = export;
    }

    public IImport getImportFile() {
        return importFile;
    }

    public void setImportFile(IImport importFile) {
        this.importFile = importFile;
    }

    public IRollback getRollback() {
        return rollback;
    }

    public void setRollback(IRollback rollback) {
        this.rollback = rollback;
    }

    public IRead getRead() {
        return read;
    }

    public void setRead(IRead read) {
        this.read = read;
    }

    public ICreateClassification getClassification() {
        return classification;
    }

    public void setClassification(ICreateClassification classification) {
        this.classification = classification;
    }

}