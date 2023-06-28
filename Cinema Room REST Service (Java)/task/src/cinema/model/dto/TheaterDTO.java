package cinema.model.dto;

import java.util.List;

public record TheaterDTO(int totalRows, int totalColumns, List<SeatDTO> availableSeats) {
}
