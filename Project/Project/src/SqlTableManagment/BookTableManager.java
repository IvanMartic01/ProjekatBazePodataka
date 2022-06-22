package SqlTableManagment;

import Connection.ConnectionUtil_Basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class BookTableManager extends TableManager {

    private boolean isSequenceCreated = false;
    private boolean isTableCreated = false;

    public void createTable() {
        if (!isTableCreated) {
            String createTableCommand = "CREATE TABLE Book" +
                    "(" +
                    "    ID INTEGER NOT NULL," +
                    "    Name VARCHAR2(50) NOT NULL," +
                    "    PublicationDate DATE," +
                    "    Description VARCHAR2(3000)," +
                    "    EditionNumber VARCHAR2(80)," +
                    "    NumberOfPage INTEGER NOT NULL," +
                    "    CONSTRAINT book_PK PRIMARY KEY (ID), " +
                    "    CONSTRAINT book_NumberOfPage_CH CHECK (NumberOfPage>0) " +
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
            String createSequenceCommand = "CREATE SEQUENCE SEQ_BookID" +
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
        System.out.println("PublicationDate: ");
        String publicationDate = sc.nextLine();
        System.out.println("Description: ");
        String description = sc.nextLine();
        System.out.println("EditionNumber: ");
        String editionNumber = sc.nextLine();
        System.out.println("NumberOfPage: ");
        String numberOfPage = sc.nextLine();

        String insertRowCommand = "INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) VALUES (SEQ_BookID.nextval, ?, TO_DATE(?, 'yyyy/mm/dd'), ?, ?, ?)";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRowCommand)) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, publicationDate);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, editionNumber);
            preparedStatement.setInt(5, Integer.parseInt(numberOfPage));
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


        String deleteRowCommand = "DELETE FROM Book WHERE ID=?";
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
        System.out.println("ID");
        String id = sc.nextLine();
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("PublicationDate: ");
        String publicationDate = sc.nextLine();
        System.out.println("Description: ");
        String description = sc.nextLine();
        System.out.println("EditionNumber: ");
        String editionNumber = sc.nextLine();
        System.out.println("NumberOfPage: ");
        String numberOfPage = sc.nextLine();

        String updateCommand = "UPDATE Book SET Name=?, PublicationDate= TO_DATE(?, 'yyyy/mm/dd'), Description=?, EditionNumber=?, NumberOfPage=? WHERE ID=?";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateCommand)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, publicationDate);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, editionNumber);
            preparedStatement.setInt(5, Integer.parseInt(numberOfPage));
            preparedStatement.setInt(6, Integer.parseInt(id));


            System.out.printf("%d row(s) affected by update!", preparedStatement.executeUpdate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateData() {

        ArrayList<String> queries = new ArrayList<>();

        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Hari Poter',TO_DATE('2003/05/03', 'yyyy/mm/dd'),'Opis', 1,250)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Gospodat Prsenova',TO_DATE('2004/03/01', 'yyyy/mm/dd'),'Opis', 2,115)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Tom Sojer',TO_DATE('1964/11/13', 'yyyy/mm/dd'),'Opis','3',76)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Ranjeni orao',TO_DATE('1988/03/01', 'yyyy/mm/dd'),'Opis','4',46)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Druzina Pere Kvrzice',TO_DATE('1978/11/24', 'yyyy/mm/dd'),'Opis','3',133)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Juzni vetar',TO_DATE('2012/05/07', 'yyyy/mm/dd'),'Opis','2',536)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Noz',TO_DATE('1999/04/03', 'yyyy/mm/dd'),'Opis','4',155)");
        queries.add("INSERT INTO Book (ID,Name,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Hamlet','Opis','5',123)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Beograd na vodi',TO_DATE('1889/05/15', 'yyyy/mm/dd'),'Opis','1',412)");
        queries.add("INSERT INTO Book (ID,Name,PublicationDate,Description,EditionNumber,NumberOfPage) " +
                "VALUES (SEQ_BookID.nextval,'Tajne zdravlja',TO_DATE('2016/04/01', 'yyyy/mm/dd'),'Opis','12',332)");

        try {
            executeGeneratedQueries(queries);
            System.out.println("You have successfully generated data for Visitor table");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
