package Sevice;

import Connection.ConnectionUtil_Basic;
import Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BookService {

    public Book getBook(int bookId) throws Exception {

        Book book = null;
        String query = "SELECT * FROM Book WHERE ID=" + bookId;
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                int id = resultSet.getInt("ID");
                String bookName = resultSet.getString("Name");
                String publicationDateString = resultSet.getString("publicationDate");
                String description = resultSet.getString("description");
                String editionNumber = resultSet.getString("editionNumber");
                int numberOfPage = resultSet.getInt("numberOfPage");

                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                Date publicationDate = inputFormat.parse(publicationDateString);
                LocalDate publicationLocalDate = publicationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


                book = new Book(id, bookName, publicationLocalDate, description, editionNumber, numberOfPage);

            }
        }

        if (book == null) {
            throw new Exception("Book not found!");
        }
        return book;
    }
}
