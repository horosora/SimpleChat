package simplechat;

public class DataToSend {
    private String name;
    private String message;

    public DataToSend() {}

    public DataToSend(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String naem) {
        this.name = name;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
