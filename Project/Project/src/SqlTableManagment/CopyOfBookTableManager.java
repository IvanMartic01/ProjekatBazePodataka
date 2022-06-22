package SqlTableManagment;

import Connection.ConnectionUtil_Basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class CopyOfBookTableManager extends TableManager {

    private boolean isSequenceCreated = false;
    private boolean isTableCreated = false;

    public void createTable() {
        if (!isTableCreated) {
            String createTableCommand = "CREATE TABLE CopyOfBook " +
                    "(" +
                    "    ID INTEGER NOT NULL," +
                    "    StateComment VARCHAR2(2000)," +
                    "    PurchasePrice FLOAT NOT NULL," +
                    "    LibraryID INTEGER NOT NULL," +
                    "    BookID INTEGER NOT NULL," +
                    "    CONSTRAINT copyOfBook_PK PRIMARY KEY (ID)," +
                    "    CONSTRAINT copyOfBook_library_FK FOREIGN KEY (LibraryID) REFERENCES Library(ID)," +
                    "    CONSTRAINT copyOfBook_Book_FK FOREIGN KEY (BookID) REFERENCES Book(ID)" +
                    ")";
            super.createTable(createTableCommand);
            isTableCreated = true;
        } else {
            System.out.println("Table is already created!");
        }
    }

    public void createSequence() {
        if (!isSequenceCreated) {
            isSequenceCreated = true;
            String createSequenceCommand = "CREATE SEQUENCE SEQ_copyOfBookID" +
                    "    INCREMENT BY 1" +
                    "    MINVALUE 0" +
                    "    START WITH 0" +
                    "    NOCYCLE" +
                    "    CACHE 10";
            super.createSequence(createSequenceCommand);
        } else {
            System.out.println("Sequence is already created!");
        }
    }

    @Override
    public void insertRow() {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("StateComment: ");
        String stateComment = sc.nextLine();
        System.out.println("PurchasePrice: ");
        String purchasePrice = sc.nextLine();
        System.out.println("LibraryID: ");
        String libraryId = sc.nextLine();
        System.out.println("BookID: ");
        String bookId = sc.nextLine();


        String insertRowCommand = "INSERT INTO CopyOfBook (ID, StateComment, PurchasePrice, LibraryID, BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRowCommand)) {

            preparedStatement.setString(1, stateComment);
            preparedStatement.setFloat(2, Float.parseFloat(purchasePrice));
            preparedStatement.setInt(3, Integer.parseInt(libraryId));
            preparedStatement.setInt(4, Integer.parseInt(bookId));
            preparedStatement.executeUpdate();

            System.out.println("Row successfully inserted");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteRow() {

        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("ID: ");
        String id = sc.nextLine();


        String deleteRowCommand = "DELETE FROM CopyOfBook WHERE ID=?";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteRowCommand)) {

            preparedStatement.setInt(1, Integer.parseInt(id));
            System.out.printf("%d row(s) affected by delete!", preparedStatement.executeUpdate());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateRow() {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("ID: ");
        String id = sc.nextLine();
        System.out.println("StateComment: ");
        String stateComment = sc.nextLine();
        System.out.println("PurchasePrice: ");
        String purchasePrice = sc.nextLine();
        System.out.println("LibraryID: ");
        String libraryId = sc.nextLine();
        System.out.println("BookID: ");
        String bookId = sc.nextLine();

        String updateCommand = "UPDATE CopyOfBook SET StateComment=?, PurchasePrice=?, LibraryID=?, BookID=? WHERE ID=?";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {

            preparedStatement.setString(1, stateComment);
            preparedStatement.setFloat(2, Float.parseFloat(purchasePrice));
            preparedStatement.setInt(3, Integer.parseInt(libraryId));
            preparedStatement.setInt(4, Integer.parseInt(bookId));
            preparedStatement.setInt(5, Integer.parseInt(id));
            preparedStatement.executeUpdate();

            System.out.printf("%d row(s) affected by update!", preparedStatement.executeUpdate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateData() {

        ArrayList<String> queries = new ArrayList<>();

        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 1500,0,1)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 1800,1,2)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 1500,2,3)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 1350,2,3)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 1500,2,4)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 2000,3,4)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 3000,3,5)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 1600,4,1)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 700,4,0)");
        queries.add("INSERT INTO CopyOfBook (ID,StateComment,PurchasePrice,LibraryID,BookID) " +
                "VALUES (SEQ_copyOfBookID.nextval, 'Komentar', 850,1,8)");

        try {
            executeGeneratedQueries(queries);
            System.out.println("You have successfully generated data for Visitor table");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
