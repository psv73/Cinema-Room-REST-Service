package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

public class Statistic {

    @JsonProperty("current_income")
    private final long currentIncome;

    @JsonProperty("number_of_available_seats")
    private final long numberOfAvailableSeats;

    @JsonProperty("number_of_purchased_tickets")
    private final long numberOfPurchasedTickets;

    public Statistic(long currentIncome, long numberOfAvailableSeats, long numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public long getCurrentIncome() {
        return currentIncome;
    }

    public long getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public long getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }
}
