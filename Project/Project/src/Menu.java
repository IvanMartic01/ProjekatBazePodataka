import Reports.Report1;
import Reports.Report2;
import SqlTableManagment.*;

import java.util.Scanner;

public class Menu {

    public void choseItemFromMenu() {
        displayBigMenu();
        label:
        while (true) {
            String selectedItemForBigMenu = getSelectedItem();
            switch (selectedItemForBigMenu) {
                case "1":

                    displaySmallMenu("library");
                    LibraryTableManager libraryTableManager = new LibraryTableManager();
                    doSelectedItemFromSmallMenu(libraryTableManager);

                    break;
                case "2":

                    displaySmallMenu("Book");
                    BookTableManager bookTableManager = new BookTableManager();
                    doSelectedItemFromSmallMenu(bookTableManager);

                    break;
                case "3":

                    displaySmallMenu("Visitor");
                    VisitorTableManager visitorTableManager = new VisitorTableManager();
                    doSelectedItemFromSmallMenu(visitorTableManager);
                    break;
                case "4":

                    displaySmallMenu("rentedBook");
                    RentedBookTableManager rentedBookTableManager = new RentedBookTableManager();
                    doSelectedItemFromSmallMenu(rentedBookTableManager);

                    break;
                case "5":
                    displaySmallMenu("copyOfBook");
                    CopyOfBookTableManager copyOfBookTableManager = new CopyOfBookTableManager();
                    doSelectedItemFromSmallMenu(copyOfBookTableManager);

                    break;
                case "6":
                    Report1 report1 = new Report1();
                    report1.generateReport();

                    break;
                case "7":
                    Report2 report2 = new Report2();
                    report2.generateReport();

                    break;
                case "8":
                    displayQueryMenu();
                    doQuery();

                    break;
                case "x":
                    break label;
                default:
                    System.out.println("Bad input!");
                    break;
            }

        }
    }

    private void displayBigMenu() {
        System.out.println();
        System.out.println("To manipulate library data, enter 1");
        System.out.println("To manipulate book data, enter 2");
        System.out.println("To manipulate visitor data, enter 3");
        System.out.println("To manipulate rentedBook data, enter 4");
        System.out.println("To manipulate copyOfBook data, enter 5");
        System.out.println("To generate report1, enter 6");
        System.out.println("To generate report2, enter 7");
        System.out.println("To enter query menu, enter 8");
        System.out.println("For exit, enter x");
    }

    private void displaySmallMenu(String tableName) {
        System.out.println();
        System.out.println("To insert new row in " + tableName + ", enter 1");
        System.out.println("To update row in " + tableName + ", enter 2");
        System.out.println("To delete row in " + tableName + ", enter 3");
        System.out.println("To go back, enter x");
    }

    private void displayQueryMenu() {
        System.out.println();
        System.out.println("To execute Joker query, enter 1");
        System.out.println("To execute multipleInternalMergeTablesQuery, enter 2");
        System.out.println("To execute groupByQuery, enter 3");
        System.out.println("To execute transactionQuery, enter 4");
        System.out.println("To go back, enter x");
    }

    private String getSelectedItem() {

        String selectedItem;
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value: ");
        selectedItem = sc.nextLine();
        return selectedItem;
    }

    private void doSelectedItemFromSmallMenu(TableManager tableGenerator) {
        label:
        while (true) {
            String selectedItemForSmallMenu = getSelectedItem();
            switch (selectedItemForSmallMenu) {
                case "1":
                    tableGenerator.insertRow();
                    break;
                case "2":
                    tableGenerator.updateRow();
                    break;
                case "3":
                    tableGenerator.deleteRow();
                    break;
                case "x":
                    displayBigMenu();
                    break label;
                default:
                    System.out.println("Bad input!");
                    break;
            }
        }
    }

    private void doQuery() {
        label:
        while (true) {
            String selectedItemForSmallMenu = getSelectedItem();
            QueryExecutor queryExecutor = new QueryExecutor();
            switch (selectedItemForSmallMenu) {
                case "1":
                    queryExecutor.executeJokerQuery();
                    break;
                case "2":
                    queryExecutor.multipleInternalMergeTablesQuery();
                    break;
                case "3":
                    queryExecutor.groupByQuery();
                    break;
                case "4":
                    queryExecutor.executeTransactionQuery();
                    break;
                case "x":
                    displayBigMenu();
                    break label;
                default:
                    System.out.println("Bad input!");
                    break;
            }
        }
    }


}
