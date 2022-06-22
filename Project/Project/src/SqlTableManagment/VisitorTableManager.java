package SqlTableManagment;

import Connection.ConnectionUtil_Basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class VisitorTableManager extends TableManager {
    private boolean isSequenceCreated = false;
    private boolean isTableCreated = false;

    public void createTable() {
        if (!isTableCreated) {
            String createTableCommand = "CREATE TABLE Visitor " +
                    "(" +
                    "ID INTEGER NOT NULL," +
                    "FirstName VARCHAR2(50) NOT NULL," +
                    "LastName VARCHAR2(80) NOT NULL," +
                    "PhoneNumber VARCHAR2(50) NOT NULL," +
                    "Email VARCHAR2(80) NOT NULL," +
                    "AccountBalance FLOAT DEFAULT 0," +
                    "CONSTRAINT visitor_PK PRIMARY KEY (ID)," +
                    "CONSTRAINT visitor_Email_UK UNIQUE (Email)" +
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
            String createSequenceCommand = "CREATE SEQUENCE SEQ_VisitorID " +
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
        System.out.println("FirstName: ");
        String firstName = sc.nextLine();
        System.out.println("LastName: ");
        String lastName = sc.nextLine();
        System.out.println("PhoneNumber: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("AccountBalance: ");
        String accountBalance = sc.nextLine();

        String insertRowCommand = "INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRowCommand)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, email);
            preparedStatement.setFloat(5, Float.parseFloat(accountBalance));
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


        String deleteRowCommand = "DELETE FROM Visitor WHERE ID=?";
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
        System.out.println("FirstName: ");
        String firstName = sc.nextLine();
        System.out.println("LastName: ");
        String lastName = sc.nextLine();
        System.out.println("PhoneNumber: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("AccountBalance: ");
        String accountBalance = sc.nextLine();

        String updateCommand = "UPDATE Visitor SET FirstName=?, LastName=?, PhoneNumber=?, Email=?, AccountBalance=? WHERE ID=?";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, email);
            preparedStatement.setFloat(5, Float.parseFloat(accountBalance));
            preparedStatement.setInt(6, Integer.parseInt(id));

            System.out.printf("%d row(s) affected by update!", preparedStatement.executeUpdate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateData() {

        ArrayList<String> queries = new ArrayList<String>();
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Ivan','Martic','0604672999','ivanmartic@gmail.com', 0.0)");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Pera','Peric','0614992111','peraperic@gmail.com', 2000.0)");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Ana','Anic','0636654789','anaanic@gmail.com', 0.0)");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Nikola','Nikolic','0654553211','nikolanikolic@gmail.com',3000.0)");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Filip','Filipic','0621532879','filipfilipic@gmail.com',0.0)");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Zana','Zanic','06245567912','zanazanic@gmail.com',5000.0) ");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Tamara','Tamaric','0658946487','tamaratamaric@gmail.com',500.0)");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Vuk','Vatovic','0647781234','vukvatovic@gmail.com',3550.0) ");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Milica','Micic','0606553214','milicamicic@gmail.com',4125.0) ");
        queries.add("INSERT INTO Visitor (ID,FirstName,LastName,PhoneNumber,Email,AccountBalance) " +
                "VALUES (SEQ_VISITORID.nextval,'Ilija','Todorovic','0691324456','ilijatodorovic@gmail.com',1000.0)");

        try {
            executeGeneratedQueries(queries);
            System.out.println("You have successfully generated data for Visitor table");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
