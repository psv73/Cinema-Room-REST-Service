package cinema.model.response;

import cinema.model.dto.SeatDTO;

import java.util.UUID;

public class ResponsePurchaseTicket {
    private UUID token;

    public ResponsePurchaseTicket(UUID token, SeatDTO ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public SeatDTO getTicket() {
        return ticket;
    }

    public void setTicket(SeatDTO ticket) {
        this.ticket = ticket;
    }

    private SeatDTO ticket;

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
