package Repository;

import Connection.ConnectionUtil_Basic;
import Model.Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LibraryRepository {

    public ArrayList<Library> getLibraries() {

        ArrayList<Library> libraries = new ArrayList<>();

        String query = "SELECT * FROM Library";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {

                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                String address = resultSet.getString("Address");

                Library library = new Library(id, name, phoneNumber, email, address);
                libraries.add(library);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return libraries;
    }
}
