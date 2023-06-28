package cinema.service;

import cinema.model.*;
import cinema.model.dto.SeatDTO;
import cinema.model.dto.TheaterDTO;
import cinema.model.response.ResponsePurchaseTicket;
import cinema.model.response.ResponseReturnTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Service
public class BookingService {

    private final static String PASSWORD = "super_secret";
    private final Theater theater;
    private final SeatService seatService;

    public BookingService(Theater theater, SeatService seatService) {
        this.theater = theater;
        this.seatService = seatService;
    }

    public TheaterDTO convertTheaterToDTO() {
        List<SeatDTO> seats = new ArrayList<>();

        for (Seat s : theater.getAvailableSeats()) {
            if (s.isAvailable()) {
                seats.add(new SeatDTO(s.getRow(), s.getColumn(), s.getPrice()));
            }
        }

        return new TheaterDTO(Theater.TOTAL_ROWS, Theater.TOTAL_COLUMNS, seats);
    }

    private synchronized void changeAvailability(Seat seat, boolean status) {
        seat.setAvailable(status);
    }

    public ResponseEntity<?> purchaseTicket(SeatDTO seatDTO) {
        int row = seatDTO.row();
        int column = seatDTO.column();

        ObjectMapper objectMapper = new ObjectMapper();

        if (row >= 1 & row <= Theater.TOTAL_ROWS &
                column >= 1 & column <= Theater.TOTAL_COLUMNS) {
            int index = (row - 1) * Theater.TOTAL_COLUMNS + column - 1;
            Seat seat = theater.getAvailableSeats().get(index);

            if (seat.isAvailable()) {
                changeAvailability(seat, false);
                try {
                    seat.setToken(UUID.randomUUID());

                    return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(new ResponsePurchaseTicket(seat.getToken(), seatService.convertSeatToDTO(seat))),
                            OK);
                } catch (JsonProcessingException e) {
                    return new ResponseEntity<>(Map.of("error", e.getMessage()),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(Map.of("error", ErrorMessage.TICKET_IS_PURCHASED.toString()),
                        BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(Map.of("error", ErrorMessage.OUT_OF_BOUNDS.toString()),
                    BAD_REQUEST);
        }
    }

    public ResponseEntity<?> returnTicket(Token token) {
        ObjectMapper objectMapper = new ObjectMapper();

        Optional<Seat> seat = theater.getAvailableSeats().stream()
                .filter(s -> token.token().equals(s.getToken()))
                .findFirst();

        try {
            if (seat.isPresent()) {
                theater.getAvailableSeats().stream()
                        .filter(s -> token.token().equals(s.getToken()))
                        .forEach(s -> {
                            s.setAvailable(true);
                            s.setToken(null);
                        });

                return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        new ResponseReturnTicket(seat.get())
                ), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", ErrorMessage.WRONG_TOKEN.toString()), BAD_REQUEST);
            }
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getStatistic(String password) {

        if (!PASSWORD.equals(password)) {
            return new ResponseEntity<>(Map.of("error", ErrorMessage.WRONG_PASSWORD.toString()), HttpStatus.UNAUTHORIZED);
        }

        int currentIncome = theater.getAvailableSeats().stream()
                .filter(s -> !s.isAvailable())
                .map(Seat::getPrice)
                .reduce(0, Integer::sum);

        long numberOfAvailableSeats = theater.getAvailableSeats().stream()
                .filter(Seat::isAvailable)
                .count();

        long numberOfPurchasedTickets = theater.getAvailableSeats().stream()
                .filter(s -> !s.isAvailable())
                .count();

        return new ResponseEntity<>(new Statistic(currentIncome, numberOfAvailableSeats, numberOfPurchasedTickets),
                OK);
    }
}
