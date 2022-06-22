package SqlTableManagment;


import Connection.ConnectionUtil_Basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public abstract class TableManager {

    public abstract void insertRow();

    public abstract void deleteRow();

    public abstract void updateRow();

    protected void createTable(String command) {

        try {
            executeSimpleCommand(command);
            System.out.println("Successfully created table!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    protected void createSequence(String command) {

        try {
            executeSimpleCommand(command);
            System.out.println("Successfully created sequence!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    protected void executeSimpleUpdate(String command) {
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void executeGeneratedQueries(ArrayList<String> queries) {
        for (String query : queries) {
            executeSimpleUpdate(query);
        }
    }

    private void executeSimpleCommand(String command) {
        try (Connection connection = ConnectionUtil_Basic.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(command)) {

            preparedStatement.execute(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

