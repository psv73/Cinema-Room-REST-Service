package cinema.model.response;

import cinema.model.Seat;
import cinema.model.dto.SeatDTO;
import cinema.service.SeatService;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseReturnTicket {

    @JsonProperty("returned_ticket")
    private final SeatDTO returnedTicket;

    public ResponseReturnTicket(Seat returnedTicket) {
        SeatService seatService = new SeatService();
        this.returnedTicket = seatService.convertSeatToDTO(returnedTicket);
    }

    public SeatDTO getReturnedTicket() {
        return returnedTicket;
    }
}
