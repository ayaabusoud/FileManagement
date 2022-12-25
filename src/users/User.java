package users;

import authnetication.ReaderLogin;
import exceptions.IncorrectFilePathException;
import exceptions.NotAllowedOperationException;
import operations.createClassification.IClassificationCreationBehavior;
import operations.delete.IDeletionBehavior;
import operations.export.IExportBehavior;
import operations.importOperation.IImportBehavior;
import operations.read.IReadingBehavior;
import operations.rollback.IRollbackBehavior;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class User implements IUser {
    private IDeletionBehavior delete;
    private IExportBehavior export;
    private IImportBehavior importFile;
    private IRollbackBehavior rollback;
    private IReadingBehavior read;
    private IClassificationCreationBehavior classification;
    private static final Logger logger = LogManager.getLogger(User.class);

    public User(){
        logger.debug("Call Constructor Function from user ");
        this.delete = null;
        this.export = null;
        this.importFile = null;
        this.rollback = null;
        this.read = null;
        this.classification = null;
    }

    @Override
    public void importFiles(Connection connection) throws NotAllowedOperationException{
        logger.debug("Call importFiles Function from user ");
        if(importFile == null){
      throw new NotAllowedOperationException("This operation is not allowed");
     }
    try {
          importFile.importFile(connection);
          logger.debug("Succeed,,importFile to Data base ");
        }
    catch (IncorrectFilePathException e){
        System.err.println(e.getMessage());
        }
        logger.debug("Close the importFiles function in user");

    }
    @Override
    public void deleteFiles(Connection connection) throws NotAllowedOperationException{
        logger.debug("Call deleteFiles Function from user ");

        if(delete == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        {
            delete.delete(connection);
            logger.debug("Succeed,deleteFiles from Data base ");
        }
        logger.debug("Close the deleteFiles function in user");

    }
    @Override
    public void readFiles(Connection connection) throws NotAllowedOperationException {
        logger.debug("Call readFiles Function from user ");

        if(read == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        {
            read.read(connection);
            logger.debug("Succeed,readFiles from Data base ");
        }
        logger.debug("Close the readFiles function in user");

    }
    @Override
    public void exportFile(Connection connection ) throws NotAllowedOperationException{
        logger.debug("Call exportFile Function from user ");

        if(export == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        {
            export.export(connection);
            logger.debug("Succeed,exportFile from Data base ");
        }
        logger.debug("Close the exportFile function in user");

    }
    @Override
    public void rollBack(Connection connection) throws NotAllowedOperationException{
        logger.debug("Call rollBack Function from user ");

        if(rollback == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        {
            rollback.rollbackVersion(connection);
            logger.debug("Succeed,rollbackVersion from Data base ");
        }
        logger.debug("Close the rollBack function in user");
    }
    @Override
    public void createClassification(Connection connection) throws NotAllowedOperationException {
        logger.debug("Call createClassification Function from user ");

        if(classification == null){
            throw new NotAllowedOperationException("This operation is not allowed");
        }
        {
            classification.create(connection);
            logger.debug("Succeed,createClassification ");
        }
        logger.debug("Close the createClassification function in user");

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