package menu;

public abstract class OperationMenu {
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


}
