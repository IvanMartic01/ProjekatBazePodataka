package SqlTableManagment;

import Connection.ConnectionUtil_Basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {


    private void executeTransactional(String... commands) {
        try (Connection connection = ConnectionUtil_Basic.getConnection()) {
            connection.setAutoCommit(false); // disable auto-commit (enabled by default)

            try {
                for (String command : commands) {
                    PreparedStatement preparedStatement = connection.prepareStatement(command);
                    preparedStatement.execute(command);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback(); // if one of the commands fails, roll-back changes

            }

            connection.commit(); // if all commands execute successfully, commit changes
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void executeJokerQuery() {
        System.out.println("Select each visitor whose name contains the letter 'i' at second place");
        String jokerQuery = "SELECT * FROM Visitor WHERE FirstName LIKE '_i%'";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(jokerQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Visitors: ");
            System.out.println("ID      " + "FirstName      " + "SecondName      " + "PhoneNumber     " + "Email       " + "AccountBalance      ");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                float accountBalance = resultSet.getFloat("AccountBalance");

                System.out.println(id + "      " + firstName + "      " + lastName + "      " + phoneNumber + "     " + email + "       " + accountBalance + "      ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void multipleInternalMergeTablesQuery() {
        System.out.println("Show all book loans, the visitor who borrowed the book and the name of the book loan");
        String query = "SELECT DISTINCT v.ID, v.firstName ,v.lastName,b.name FROM visitor v JOIN rentedbook rb " +
                "ON rb.visitorid=v.ID  " +
                "JOIN copyofbook cb ON rb.copyofbookid=cb.id " +
                "JOIN book b ON cb.bookid=b.id";

        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Visitors: ");
            System.out.println("VisitorID      " + "FirstName      " + "SecondName      " + "RentedBookName     ");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String rentedBookName = resultSet.getString("Name");


                System.out.println(id + "      " + firstName + "      " + lastName + "      " + rentedBookName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void groupByQuery() {
        System.out.println("Show all books with more than 1 copy. ");
        String query = "SELECT  b1.id, b1.name, COUNT(b1.id)  FROM Book b1 " +
                "JOIN copyofbook cb on b1.Id = cb.bookID GROUP BY (b1.id,b1.name) HAVING COUNT(b1.id)>=2";

        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Visitors: ");
            System.out.println("BookID      " + "BookName      " + "NumberOfCopies      ");

            while (resultSet.next()) {
                int bookId = resultSet.getInt("ID");
                String bookName = resultSet.getString("Name");
                int numberOfCopies = resultSet.getInt("COUNT(B1.ID)");


                System.out.println(bookId + "      " + bookName + "      " + numberOfCopies);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeTransactionQuery() {
        executeTransactional("UPDATE Visitor SET accountBalance = accountBalance - 1000 WHERE id = 5",
                "UPDATE Visitor SET accountBalance = accountBalance + 1000 WHERE id = 4");
        System.out.println("You have successfully completed the transaction! ");
    }

}
