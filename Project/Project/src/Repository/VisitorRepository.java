package Repository;

import Connection.ConnectionUtil_Basic;
import Model.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VisitorRepository {

    
    public ArrayList<Visitor> getVisitors() {

        ArrayList<Visitor> visitors = new ArrayList<>();

        String query = "SELECT * FROM Visitor";
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                float accountBalance = resultSet.getFloat("AccountBalance");

                Visitor visitor = new Visitor(id, firstName, lastName, phoneNumber, email, accountBalance);
                visitors.add(visitor);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return visitors;
    }
}
