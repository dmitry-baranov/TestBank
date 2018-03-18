package example.dima.limit;

import example.dima.Payments;
import example.dima.Service;
import example.dima.dao.limit.SearchHourLimit;
import lombok.Setter;

import java.sql.*;
import java.time.LocalDateTime;

@Setter
public class HourLimit extends AbstractLimit {
    private static int Hour = 1;
    private static int LimitMoney = 3000;
    @Override
    protected boolean confirmationOfPayment(Connection connection, Payments payments) {
        Service service = payments.getService();
        LocalDateTime today = LocalDateTime.now().minusHours(Hour);
        Integer money = payments.getMoney();
        SearchHourLimit searchHourLimit = new SearchHourLimit();
        searchHourLimit.searchLimit(asDate(today),service.getName());
        Integer sumMoney = searchHourLimit.getSum();
        return (!(sumMoney == null)) && (sumMoney + money) <= LimitMoney;

//        Service service = payments.getService();
//        LocalDateTime today = LocalDateTime.now().minusHours(1);
//        Integer money = payments.getMoney();
//        for (Payments e : list) {
//            if (purse(today,e.getDateTime()) && service.equals(e.getService())) {
//                money += e.getMoney();
//            }
//        }
//        return money <= 3000;
    }
}
