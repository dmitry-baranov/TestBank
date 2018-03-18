package example.dima.limit;

import example.dima.Payments;
import lombok.Setter;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.concurrent.FutureTask;

@Setter
public abstract class AbstractLimit {
    private AbstractLimit next;

    protected abstract boolean confirmationOfPayment(Connection connection, Payments payments);

    protected static boolean comparisonDate(LocalDateTime First, LocalDateTime Second) {
        int i = First.compareTo(Second);
        return i >= 0;
    }

    public static java.sql.Timestamp asDate(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    public boolean checkPayment(Connection connection, Payments payments) {
        boolean isOK = confirmationOfPayment(connection, payments);
        return isOK && (next == null || next.checkPayment(connection, payments));
    }
}