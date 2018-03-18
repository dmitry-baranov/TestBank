package example.dima.dao.limit;

import example.dima.dao.ConnectionBD;
import lombok.Getter;

import java.sql.*;

@Getter
public class SearchSameLimit {
    private Integer count;

    public void searchLimit(java.sql.Timestamp today, String client, String service) {
        try (Connection connection = ConnectionBD.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(Payments.DateTimePay) AS CountPay " +
                            "FROM Payments " +
                            "WHERE Payments.Confirmation = TRUE AND Payments.DateTimePay >= ? AND Payments.Clientname = ? AND Payments.Service = ?");
            preparedStatement.setTimestamp(1, today);
            preparedStatement.setString(2, client);
            preparedStatement.setString(3, service);
            ResultSet resultSet = preparedStatement.executeQuery();
            int countPay = 0;
            if (resultSet.next()) {
                countPay = resultSet.getInt("CountPay");
            }
            this.count = countPay;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
