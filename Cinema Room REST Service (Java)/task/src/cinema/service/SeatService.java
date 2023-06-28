package cinema.service;

import cinema.model.Seat;
import cinema.model.dto.SeatDTO;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    public SeatDTO convertSeatToDTO(Seat seat) {
        return new SeatDTO(seat.getRow(), seat.getColumn(), seat.getPrice());
    }
}
