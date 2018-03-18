package example.dima;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class Payments {
    private Service service;
    private Client client;
    private Integer money;
    private LocalDateTime dateTime;
    private boolean confirmation;

    public Payments(Service service, Client client, Integer money, LocalDateTime dateTime) {
        this.service = service;
        this.client = client;
        this.money = money;
        this.dateTime = dateTime;
        this.confirmation = false;
    }

    public boolean getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }
}
