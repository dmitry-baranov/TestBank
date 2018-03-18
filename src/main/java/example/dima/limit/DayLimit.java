package example.dima.limit;

import example.dima.Payments;
import example.dima.Service;
import example.dima.dao.limit.SearchDayLimit;
import example.dima.dao.limit.SearchLimit;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Setter
public class DayLimit extends AbstractLimit {
    private  static int TopHour = 0;
    private static int BotHour = 23;
    private static int LimitMoney = 5000;
    private static final String request = "SELECT SUM(Payments.Money) AS SumMoney FROM Payments WHERE Payments.Confirmation = TRUE AND Payments.DateTimePay >= ? AND Payments.DateTimePay <= ? AND Payments.Service = ?";
    @Override
    protected boolean confirmationOfPayment(Connection connection, Payments payments) {
        Service service = payments.getService();
        LocalDateTime todayTop = LocalDateTime.now().withHour(TopHour).withMinute(0);
        LocalDateTime todayBot = LocalDateTime.now().withHour(BotHour).withMinute(0);
        Integer money = payments.getMoney();
        if (comparisonDate(payments.getDateTime(), todayTop) && comparisonDate(todayBot, payments.getDateTime()));
        else {
            return false;
        }
        List<Object> list = new LinkedList<>();
        list.add(asDate(todayTop));
        list.add(asDate(todayBot));
        list.add(service.getName());
        SearchLimit searchLimit = new SearchLimit();
        searchLimit.searchLimit(list, request,"sum");
        Integer sumMoney = searchLimit.getSum();
        return (!(sumMoney == null)) && (money + sumMoney <= LimitMoney);
//        Service service = payments.getService();
//        LocalDateTime todayTop = LocalDateTime.now().withHour(1).withMinute(0);
//        LocalDateTime todayBot = LocalDateTime.now().withHour(23).withMinute(0);
//        Integer money = payments.getMoney();
//        for (Payments e : list) {
//            if (purse(todayTop,e.getDateTime()) && purse(e.getDateTime(),todayBot) && service.equals(e.getService())) {
//                money += e.getMoney();
//            }
//        }
//        return purse(todayTop,payments.getDateTime()) && purse(payments.getDateTime(),todayBot) && money <= 5000;
//    }
    }
}
