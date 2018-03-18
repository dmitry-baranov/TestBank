package example.dima;


import example.dima.limit.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ServicePayments servicePayments = new ServicePayments();
        servicePayments.addLimit(new DayLimit());
        servicePayments.addLimit(new HourLimit());
        servicePayments.addLimit(new SameLimit());
        servicePayments.addLimit(new TenPayTwoHourLimit());
        System.out.println("Введите -y- усли хотите продолжить, или есои не хотите продолжить введите любой другой текс");
        while (in.hasNext("y")) {
            in.next();
            System.out.println("Введите название услуги");
            String service = in.next();
            System.out.println("Введите имя клиента");
            String client = in.next();
            System.out.println("Введите колличество денег");
            Integer money = in.nextInt();
            Payments payments = new Payments(new Service(service), new Client(client), money, LocalDateTime.now());
            servicePayments.addPayments(payments);
            System.out.println("Состояние плотежа" + payments.getConfirmation());
            System.out.println("Введите -y- усли хотите продолжить, или есои не хотите продолжить введите любой другой текс");
        }
    }
}
