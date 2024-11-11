
import java.time.LocalDate;

public class Reserve {
    private final House house;
    private final Client client;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public Reserve(final House house, final LocalDate checkInDate, final LocalDate checkOutDate, final Client client) {
        this.house = house;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.client = client;
    }

    public House getHouse() {
        return house;
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "house=" + house +
                ", client=" + client +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
