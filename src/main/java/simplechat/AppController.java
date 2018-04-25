package simplechat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class AppController {

    @MessageMapping("/endpoint")
    @SendTo("/topic/messages")
    public DataToSend message(DataToRecv redvData) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date = now.format(fmt);

        if (redvData.getName().isEmpty()) {
            redvData.setName("名無しのスーパーハッカー");
        }

        redvData.setName(EscapeProcessing(redvData.getName()));
        redvData.setMessage(EscapeProcessing(redvData.getMessage()));

        return new DataToSend(redvData.getName(), redvData.getMessage(), date);
    }

    public String EscapeProcessing(String str) {
        return str.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&#39;");
    }
}
