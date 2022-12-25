package factory;

import users.User;
import users.UserTypes;

public interface IFactory {
      public User create(UserTypes type);
}
