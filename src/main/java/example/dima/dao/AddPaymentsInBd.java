package example.dima.dao;

import example.dima.limit.AbstractLimit;
import example.dima.Payments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AddPaymentsInBd {
    public static void addPayments(Connection connection, Payments payments) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Payments VALUE (?,?,?,?,?)"
        );
        preparedStatement.setString(1, payments.getService().getName());
        preparedStatement.setString(2, payments.getClient().getName());
        preparedStatement.setInt(3, payments.getMoney());
        preparedStatement.setTimestamp(4, AbstractLimit.asDate(LocalDateTime.now()));
        preparedStatement.setBoolean(5, payments.getConfirmation());
        preparedStatement.executeUpdate();
    }
}
