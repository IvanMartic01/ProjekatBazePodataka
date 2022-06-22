package SqlTableManagment;

import Connection.ConnectionUtil_Basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryTableManager extends TableManager {

    private boolean isSequenceCreated = false;
    private boolean isTableCreated = false;

    public void createTable() {
        if (!isTableCreated) {
            String createTableCommand = "CREATE TABLE Library" +
                    "(" +
                    "ID INTEGER NOT NULL," +
                    "Name VARCHAR2(50) NOT NULL," +
                    "PhoneNumber VARCHAR2(80) NOT NULL," +
                    "Email VARCHAR2(80) NOT NULL," +
                    "Address VARCHAR2(80) NOT NULL," +
                    "CONSTRAINT library_PK PRIMARY KEY (ID)" +
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
            String createSequenceCommand = "CREATE SEQUENCE SEQ_LibraryID" +
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
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("PhoneNumber: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Address: ");
        String address = sc.nextLine();

        String insertRowCommand = "INSERT INTO Library (ID,Name,PhoneNumber,Email,Address) VALUES (SEQ_LibraryID.nextval, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRowCommand)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, address);
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


        String deleteRowCommand = "DELETE FROM Library WHERE ID=?";
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
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("PhoneNumber: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Address: ");
        String address = sc.nextLine();

        String updateCommand = "UPDATE Library SET NAME=?, PhoneNumber=?, Email=?, Address=? WHERE ID=?";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {
            preparedStatement.setString(1, (name));
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, Integer.parseInt(id));
            preparedStatement.executeUpdate();

            System.out.printf("%d row(s) affected by update!", preparedStatement.executeUpdate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void generateData() {

        ArrayList<String> queries = new ArrayList<>();

        queries.add("INSERT INTO Library (ID,Name,PhoneNumber,Email,Address) " +
                "VALUES (SEQ_LibraryID.nextval,'Svetozar Markovic','0601410244','SvetozarMarkovicBlb@gmail.com','adresa1')");
        queries.add("INSERT INTO Library (ID,Name,PhoneNumber,Email,Address) " +
                "VALUES (SEQ_LibraryID.nextval,'Narodna biblioteka','0601410244','NarodnaBlb@gmail.com','adresa2')");
        queries.add("INSERT INTO Library (ID,Name,PhoneNumber,Email,Address) " +
                "VALUES (SEQ_LibraryID.nextval,'Djura Jaksic','0601410244','DjuraJaksicBlb@gmail.com','adresa3')");
        queries.add("INSERT INTO Library (ID,Name,PhoneNumber,Email,Address) " +
                "VALUES (SEQ_LibraryID.nextval,'Laguna','0601410244','lagunaBlb@gmail.com','adresa4')");
        queries.add("INSERT INTO Library (ID,Name,PhoneNumber,Email,Address) " +
                "VALUES (SEQ_LibraryID.nextval,'Galeb','0601410244','galebBlb@gmail.com','adresa5')");

        try {
            executeGeneratedQueries(queries);
            System.out.println("You have successfully generated data for Visitor table");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
