package example.dima.limit;

import example.dima.Client;
import example.dima.Payments;
import example.dima.Service;
import example.dima.dao.limit.SearchSameLimit;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Setter
public class SameLimit extends AbstractLimit{
    private static int today = 24;
    private static int count = 20;

    @Override
    protected boolean confirmationOfPayment(Connection connection, Payments payments) {
        Service service = payments.getService();
        Client client = payments.getClient();
        LocalDateTime todayMinus = LocalDateTime.now().minusHours(SameLimit.today);
        SearchSameLimit searchSameLimit = new SearchSameLimit();
        searchSameLimit.searchLimit(asDate(todayMinus), client.getName(), service.getName());
        Integer countLimit = searchSameLimit.getCount();
        return (!(countLimit == null)) && countLimit < count;
//        Service service = payments.getService();
//        Client client = payments.getClient();
//        LocalDateTime today = LocalDateTime.now().minusHours(24);
//        int i = 0;
//        for (Payments e : list) {
//            if (service.equals(e.getService()) && client.equals(e.getClient()) && purse(today, e.getDateTime())) {
//                i++;
//            }
//        }
//        return i < 20;
    }
}
