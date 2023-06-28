package cinema.controller;

import cinema.model.Token;
import cinema.model.dto.SeatDTO;
import cinema.model.dto.TheaterDTO;
import cinema.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/seats")
    public TheaterDTO getTheater() {
        return bookingService.convertTheaterToDTO();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody SeatDTO seatDTO) {
        return bookingService.purchaseTicket(seatDTO);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {
        return bookingService.returnTicket(token);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStatistic(@RequestParam(required = false) String password) {
        return bookingService.getStatistic(password);
    }
}
