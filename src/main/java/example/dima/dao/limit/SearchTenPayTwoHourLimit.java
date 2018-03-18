package example.dima.dao.limit;

import example.dima.dao.ConnectionBD;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class SearchTenPayTwoHourLimit {
    private Integer sum;
    private Integer count;

    public void searchLimit(java.sql.Timestamp today, String client) {
        try (Connection connection = ConnectionBD.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(Payments.DateTimePay) AS CountPay, SUM(Payments.Money) AS SumPay " +
                            "FROM Payments " +
                            "WHERE Payments.Confirmation = TRUE AND Payments.DateTimePay >= ? AND Payments.Clientname = ?"
            );
            preparedStatement.setTimestamp(1, today);
            preparedStatement.setString(2, client);
            ResultSet resultSet = preparedStatement.executeQuery();
            int countPay = 0;
            int sumPay = 0;
            if (resultSet.next()) {
                countPay = resultSet.getInt("CountPay");
                sumPay = resultSet.getInt("SumPay");
            }
            this.sum = sumPay;
            this.count = countPay;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
