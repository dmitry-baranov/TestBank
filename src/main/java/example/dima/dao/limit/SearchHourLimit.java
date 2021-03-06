package example.dima.dao.limit;

import example.dima.dao.ConnectionBD;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class SearchHourLimit {
    private Integer sum;

    public void searchLimit(java.sql.Timestamp today, String service) {
        try (Connection connection = ConnectionBD.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT SUM(Payments.Money) AS SumMoney " +
                            "FROM Payments " +
                            "WHERE Payments.Confirmation = TRUE AND Payments.DateTimePay >= ? AND Payments.Service = ?" );
            preparedStatement.setTimestamp(1, today);
            preparedStatement.setString(2, service);
            ResultSet resultSet = preparedStatement.executeQuery();
            int sumMoney = 0;
            if (resultSet.next()){
                sumMoney = resultSet.getInt("SumMoney");
            }
            this.sum = sumMoney;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
