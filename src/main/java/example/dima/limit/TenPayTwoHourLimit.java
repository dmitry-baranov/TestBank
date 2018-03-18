package example.dima.limit;

import example.dima.Client;
import example.dima.Payments;
import example.dima.dao.limit.SearchTenPayTwoHourLimit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TenPayTwoHourLimit extends AbstractLimit {
    private static int hour = 2;
    private static int count = 10;
    private static int sum = 3000;

    @Override
    protected boolean confirmationOfPayment(Connection connection, Payments payments) {
        Client client = payments.getClient();
        LocalDateTime today = LocalDateTime.now().minusHours(hour);
        Integer money = payments.getMoney();
        SearchTenPayTwoHourLimit searchTenPayTwoHourLimit = new SearchTenPayTwoHourLimit();
        searchTenPayTwoHourLimit.searchLimit(asDate(today), client.getName());
        Integer sumLimit = searchTenPayTwoHourLimit.getSum();
        Integer countLimit = searchTenPayTwoHourLimit.getCount();
        return (!(sumLimit == null)) && (!(countLimit == null)) && (countLimit < count) && ((sumLimit + money) <= sum);
//        Client client = payments.getClient();
//        LocalDateTime today = LocalDateTime.now().minusHours(2);
//        int i = 0;
//        Integer money = payments.getMoney();
//        for (Payments e : list) {
//            if (purse(today,e.getDateTime()) && client.equals(e.getClient())) {
//                i++;
//                money += e.getMoney();
//            }
//        }
//        return i < 10 && money <= 3000;
    }
}
