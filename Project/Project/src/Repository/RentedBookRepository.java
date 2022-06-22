package Repository;

import Connection.ConnectionUtil_Basic;
import Model.RentedBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class RentedBookRepository {

    public ArrayList<RentedBook> getRentedBooks() {

        ArrayList<RentedBook> rentedBooks = new ArrayList<RentedBook>();

        String query = "SELECT * FROM RentedBook";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                int id = resultSet.getInt("ID");
                String rentedDateString = resultSet.getString("RentedDate");
                String lastReturnDateString = resultSet.getString("LastReturnDate");
                String returnDateString = resultSet.getString("ReturnDate");
                int visitorId = resultSet.getInt("VisitorID");
                int copyOfBookId = resultSet.getInt("CopyOfBookID");

                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                Date rentedDate = inputFormat.parse(rentedDateString);
                LocalDate rentedLocalDate = rentedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                Date lastReturnDate = inputFormat.parse(lastReturnDateString);
                LocalDate lastReturnLocalDate = lastReturnDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                LocalDate returnLocalDate = null;
                if (returnDateString != null) {
                    Date returnDate = inputFormat.parse(returnDateString);
                    returnLocalDate = returnDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                RentedBook rentedBook = new RentedBook(id, rentedLocalDate, lastReturnLocalDate, returnLocalDate, visitorId, copyOfBookId);
                rentedBooks.add(rentedBook);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rentedBooks;
    }
}
