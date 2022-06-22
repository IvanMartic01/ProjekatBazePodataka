package SqlTableManagment;

import Connection.ConnectionUtil_Basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class RentedBookTableManager extends TableManager {

    private boolean isSequenceCreated = false;
    private boolean isTableCreated = false;

    public void createTable() {
        if (!isTableCreated) {
            String createTableCommand = "CREATE TABLE RentedBook " +
                    "( " +
                    "    ID INTEGER NOT NULL, " +
                    "    RentedDate DATE NOT NULL, " +
                    "    LastReturnDate DATE NOT NULL, " +
                    "    ReturnDate DATE, " +
                    "    VisitorID INTEGER NOT NULL, " +
                    "    CopyOfBookID INTEGER NOT NULL, " +
                    "    CONSTRAINT rentedBook_PK PRIMARY KEY (ID), " +
                    "    CONSTRAINT rented_book_visitor_FK FOREIGN KEY (VisitorID) REFERENCES Visitor(ID), " +
                    "    CONSTRAINT rented_book_copyOfBook_FK FOREIGN KEY (CopyOfBookID) REFERENCES CopyOfBook(ID)," +
                    "    CONSTRAINT chk_DATE1 CHECK (LastReturnDate > RentedDate), " +
                    "    CONSTRAINT chk_DATE2 CHECK (ReturnDate > RentedDate) " +
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
            String createSequenceCommand = "CREATE SEQUENCE SEQ_rentedBookID " +
                    "    INCREMENT BY 1 " +
                    "    MINVALUE 0 " +
                    "    START WITH 0 " +
                    "    NOCYCLE " +
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
        System.out.println("RentedDate: ");
        String rentedDate = sc.nextLine();
        System.out.println("LastReturnDate: ");
        String lastReturnDate = sc.nextLine();
        System.out.println("ReturnDate: ");
        String returnDate = sc.nextLine();
        System.out.println("VisitorID: ");
        String visitorId = sc.nextLine();
        System.out.println("CopyOfBookID: ");
        String copyOfBookId = sc.nextLine();


        String insertRowCommand = "INSERT INTO RentedBook (ID, RentedDate, LastReturnDate, ReturnDate, VisitorID, CopyOfBookID) VALUES (SEQ_rentedBookID.nextval, TO_DATE(?, 'yyyy/mm/dd'), TO_DATE(?, 'yyyy/mm/dd'), TO_DATE(?, 'yyyy/mm/dd'), ?, ?)";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRowCommand)) {

            preparedStatement.setString(1, rentedDate);
            preparedStatement.setString(2, lastReturnDate);
            preparedStatement.setString(3, returnDate);
            preparedStatement.setInt(4, Integer.parseInt(visitorId));
            preparedStatement.setInt(5, Integer.parseInt(copyOfBookId));
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


        String deleteRowCommand = "DELETE FROM RentedBook WHERE ID=?";
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
        System.out.println("ReturnedDate: ");
        String returnedDate = sc.nextLine();
        System.out.println("LastReturnDate: ");
        String lastReturnDate = sc.nextLine();
        System.out.println("ReturnDate: ");
        String returnDate = sc.nextLine();
        System.out.println("VisitorID: ");
        String visitorId = sc.nextLine();
        System.out.println("CopyOfBookID: ");
        String copyOfBookId = sc.nextLine();

        String updateCommand = "UPDATE RentedBook SET RentedDate= TO_DATE(?, 'yyyy/mm/dd'), LastReturnDate= TO_DATE(?, 'yyyy/mm/dd'), ReturnDate= TO_DATE(?, 'yyyy/mm/dd'), VisitorID=?, CopyOfBookID=? WHERE ID=?";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {

            preparedStatement.setString(1, returnedDate);
            preparedStatement.setString(2, lastReturnDate);
            preparedStatement.setString(3, returnDate);
            preparedStatement.setInt(4, Integer.parseInt(visitorId));
            preparedStatement.setInt(5, Integer.parseInt(copyOfBookId));
            preparedStatement.setInt(6, Integer.parseInt(id));

            System.out.printf("%d row(s) affected by update!", preparedStatement.executeUpdate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateData() {

        ArrayList<String> queries = new ArrayList<String>();

        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,ReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2021/11/13', 'yyyy/mm/dd'), TO_DATE('2021/12/13', 'yyyy/mm/dd'), TO_DATE('2021/11/30', 'yyyy/mm/dd'), 1, 0)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2021/11/13', 'yyyy/mm/dd'),TO_DATE('2021/12/13', 'yyyy/mm/dd'),1,1) ");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,ReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2019/08/05', 'yyyy/mm/dd'),TO_DATE('2019/09/13', 'yyyy/mm/dd'),TO_DATE('2019/08/15', 'yyyy/mm/dd'),2,2)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2019/08/05', 'yyyy/mm/dd'),TO_DATE('2019/09/13', 'yyyy/mm/dd'),3,3)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,ReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('1964/11/13', 'yyyy/mm/dd'),TO_DATE('1964/12/13', 'yyyy/mm/dd'),TO_DATE('1964/12/19', 'yyyy/mm/dd'),3,4)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,ReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2000/02/13', 'yyyy/mm/dd'),TO_DATE('2000/03/13', 'yyyy/mm/dd'),TO_DATE('2000/05/13', 'yyyy/mm/dd'),5,5)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2000/02/13', 'yyyy/mm/dd'),TO_DATE('2000/03/13', 'yyyy/mm/dd'),6,6)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,ReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('1964/11/15', 'yyyy/mm/dd'),TO_DATE('1964/12/15', 'yyyy/mm/dd'),TO_DATE('1964/11/28', 'yyyy/mm/dd'),7,7)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,ReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2021/05/04', 'yyyy/mm/dd'),TO_DATE('2021/07/04', 'yyyy/mm/dd'),TO_DATE('2021/06/22', 'yyyy/mm/dd'),7,8)");
        queries.add("INSERT INTO RentedBook (ID,RentedDate,LastReturnDate,VisitorID,CopyOfBookID) " +
                "VALUES (SEQ_rentedBookID.nextval,TO_DATE('2021/05/04', 'yyyy/mm/dd'),TO_DATE('2021/07/04', 'yyyy/mm/dd'), 8, 9)");

        try {
            executeGeneratedQueries(queries);
            System.out.println("You have successfully generated data for Visitor table");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
