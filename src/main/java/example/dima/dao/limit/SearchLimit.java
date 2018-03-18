package example.dima.dao.limit;

import example.dima.dao.ConnectionBD;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
public class SearchLimit {
    private Integer sum;
    private Integer count;

    public void searchLimit(List list, String request, String quantity) {
        try (Connection connection = ConnectionBD.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            int i = 1;
            for (Object e : list) {
                if ("java.sql.Timestamp".equals(e.getClass().getName())) {
                    preparedStatement.setTimestamp(i,(java.sql.Timestamp) e);
                }
                if ("java.lang.String".equals(e.getClass().getName())) {
                    preparedStatement.setString(i,(String) e);
                }
                if ("java.lang.Integer".equals(e.getClass().getName())) {
                    preparedStatement.setInt(i,(Integer) e);
                }
                i++;
            }
            if (quantity.equals("sum")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                int sumMoney = 0;
                if (resultSet.next()) {
                    sumMoney = resultSet.getInt(1);
                }
                this.sum = sumMoney;
            }
            if (quantity.equals("count")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                int sumMoney = 0;
                if (resultSet.next()) {
                    sumMoney = resultSet.getInt(1);
                }
                this.sum = sumMoney;
            }
            if (quantity.equals("sum+count")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                int countPay = 0;
                int sumPay = 0;
                if (resultSet.next()) {
                    countPay = resultSet.getInt("CountPay");
                    sumPay = resultSet.getInt("SumPay");
                }
                this.sum = sumPay;
                this.count = countPay;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
