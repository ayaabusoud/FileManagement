package authnetication;

import operations.operation.IOperation;

import java.sql.Connection;

public interface IAuthentication {
    public IOperation authUser(Connection connection);
}
