package cinema.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Theater {
    public static final int TOTAL_ROWS = 9;
    public static final int TOTAL_COLUMNS = 9;

    private List<Seat> availableSeats = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                availableSeats.add(new Seat(i + 1, j + 1,true));
            }
        }
    }

    public Theater() {
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
