package cinema.model;

public enum ErrorMessage {
    OUT_OF_BOUNDS("The number of a row or a column is out of bounds!"),
    TICKET_IS_PURCHASED("The ticket has been already purchased!"),
    WRONG_TOKEN("Wrong token!"),
    WRONG_PASSWORD("The password is wrong!");

    final String error;

    ErrorMessage(String error) {
        this.error = error;
    }


    @Override
    public String toString() {
        return this.error;
    }
}
