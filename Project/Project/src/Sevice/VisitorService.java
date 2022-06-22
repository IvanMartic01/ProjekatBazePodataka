package Sevice;

import Connection.ConnectionUtil_Basic;
import Model.Visitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VisitorService {

    public Visitor getVisitor(int visitorId) {

        Visitor visitor = null;
        try {
            String query = "SELECT * FROM Visitor WHERE ID=" + visitorId;
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

                    visitor = new Visitor(id, firstName, lastName, phoneNumber, email, accountBalance);
                }
            }
            if (visitor == null) {
                throw new Exception("Visitor not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return visitor;
    }
}
