package simplechat;

public class DataToSend {
    private String message;

    public DataToSend() {}

    public DataToSend(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
