package menue;

public class OperationMenu {
    public static void readerMenu(){
        System.out.println("-----menu-----");
        System.out.println("0.Log out");
        System.out.println("1.Read a file");
    }
    public static void staffMenu(){
        readerMenu();
        System.out.println("2.Import a file");
        System.out.println("3.Rollback");
        System.out.println("4.Export a file");
        System.out.println("5.create Classification");
    }
    public static void adminMenu(){
        staffMenu();
        System.out.println("6.Delete a file");
    }
    public static void loginMenu()
    {
        System.out.println("choose your account type:");
        System.out.println("1.Admin");
        System.out.println("2.Staff");
        System.out.println("3.Reader");
        System.out.println("4.Create a new account");
    }

    public static void MainMenu() {
        System.out.println("Welcome to our File Management Application");
        System.out.println("choose Operation number: ");
        System.out.println("1.Sig1nup");
        System.out.println("2.Login");
    }
}
