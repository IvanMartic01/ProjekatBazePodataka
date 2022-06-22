package Sevice;

import Connection.ConnectionUtil_Basic;
import Model.CopyOfBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CopyOfBookService {

    public CopyOfBook getCopyOfBook(int copyOfBookId) throws Exception {

        CopyOfBook copyOfBook = null;
        String query = "SELECT * FROM CopyOfBook WHERE ID=" + copyOfBookId;
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String stateComment = resultSet.getString("StateComment");
                float purchasePrice = resultSet.getFloat("PurchasePrice");
                int libraryId = resultSet.getInt("LibraryId");
                int BookId = resultSet.getInt("BookId");

                copyOfBook = new CopyOfBook(id, stateComment, purchasePrice, libraryId, BookId);
            }
        }

        if (copyOfBook == null) {
            throw new Exception("CopyOfBook not found!");
        }
        return copyOfBook;
    }
}
