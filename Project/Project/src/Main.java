import SqlTableManagment.*;

public class Main {

    public static void initializeTables() {
        VisitorTableManager visitorTableManager = new VisitorTableManager();
        visitorTableManager.createTable();
        visitorTableManager.createSequence();
        visitorTableManager.generateData();


        LibraryTableManager libraryTableManager = new LibraryTableManager();
        libraryTableManager.createTable();
        libraryTableManager.createSequence();
        libraryTableManager.generateData();


        BookTableManager bookTableManager = new BookTableManager();
        bookTableManager.createTable();
        bookTableManager.createSequence();
        bookTableManager.generateData();


        CopyOfBookTableManager copyOfBookTableManager = new CopyOfBookTableManager();
        copyOfBookTableManager.createTable();
        copyOfBookTableManager.createSequence();
        copyOfBookTableManager.generateData();

        RentedBookTableManager rentedBookTableManager = new RentedBookTableManager();
        rentedBookTableManager.createTable();
        rentedBookTableManager.createSequence();
        rentedBookTableManager.generateData();
    }


    public static void main(String[] args) {

        initializeTables();
        Menu menu = new Menu();
        menu.choseItemFromMenu();
    }
}
