package example.dima;

import example.dima.limit.*;
import example.dima.dao.AddPaymentsInBd;
import example.dima.dao.ConnectionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServicePayments {
    private List<AbstractLimit> list = new ArrayList<>();

    public void addPayments(Payments payments) {
        try (Connection connection = ConnectionBD.getConnection()) {
            if (list.get(0).checkPayment(connection, payments)) {
                payments.setConfirmation(true);
            } else {
                payments.setConfirmation(false);
            }
            AddPaymentsInBd.addPayments(connection, payments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLimit(AbstractLimit limit) {
        if (!list.isEmpty()) {
            list.get(list.size() - 1).setNext(limit);
        }
        list.add(limit);
    }
}
