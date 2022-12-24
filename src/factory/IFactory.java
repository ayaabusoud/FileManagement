package factory;

import operations.operation.Operation;
import users.UserTypes;

public interface IFactory {
      Operation createUserFunctionality(UserTypes type);
}
