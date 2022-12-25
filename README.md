# FileManagement Report
This is a report containing all the achieved functionalities as written in the requirements pdf along with the used design patterns (Factory, Singleton). Each section contains the description of each feature, and what was done in order to achieve that.

## 1.Design Patterns

### The strategy Pattern
We use it in the User class as instead of inheriting behaviour we compose it that the class contains objects of each operation interface(IReadingBehaviour, IDeletionBehaviour, IExportBehaviour, IClassificationCreationBehaviour, IImportBehaviour, IRollbackBehaviour).

### Singleton Pattern
We use it in the SqlDatabase as the connection is done once at the starter of the application then all the operations will execute with the same connection and when the user logs out the connection will close.

### Factory Pattern
We used it to create objects in the User class of the allowed operation according to the user type.


## 2.Functionalities: 
### Signup
We start our program such that the reader will sign up if they don't have an account. Where the user enters a unique username and a password then if there is no conflict the user will be added to our system as a new record in the user table successfully then the reader home page will appear.

### Login
Each user needs to login before entering the system, and we display a menu according to the user type so that it contains his/her allowed operations. So that if the user is Admin or Staff they need to enter their key, as for the readers, they need to enter username and password.

### Import
The user enters the file path then we check if there is a previous version of it in our system if not the file with its size, content, name, type, version and versionType will be stored in the DB. If there is a previous version we will check if the user is admin or staff, if admin he/she choose the version control type so that the default version will add the new version as a new record and take the lastVersion value and the overwrite version control will save the previous file in backup table then overwrite the exist record with the new inputs. And if the user is a staff the file will be controlled as the default version.

### Rollback
The admin or the staff can rollback the files as the previous version becomes the last version instead of the newest one. So if the file is has a single version the file will be deleted, if the file version type is default the newest version will be deleted and the previous will become the lastVersion, and if the version type is overwrite the newest version will be deleted and the previous version will be added from the backup table to file table as the lastVersion.

### Delete
Admin can delete files and all its versions from the file table if it exists. According to name, size, type , and customer category.

### Export
The admin or staff can export file/files from the file table in our system to their device in their chosen folder name if it exists. According to name, size, type , and customer category.

### Create classification
Admin and staff can create classification that use in (read , delete , export ). They enter a unique classification name then choose according to what they want to make the category (Name , size , type or mix ).

### Read
All users can read files, by file name, file type, size or custom category, based on the user's choice the files’ name and content will display in the screen if it exists.

## 3.Exceptions

### ConnectionMySqlException
### FileIsAlreadyExist
### FileSizeException
### IncorrectFilePathException
### NotAllowedOperationException
### NotIntegerException
### SqlQueryException

