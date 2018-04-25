package simplechat;

public class DataToRecv {
    private String message;

    public DataToRecv() {}

    public DataToRecv(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}