package authnetication;

import users.IUser;

import java.sql.Connection;

public interface IAuthentication {
    public IUser authUser(Connection connection);
}
