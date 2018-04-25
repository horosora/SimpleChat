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
        if (redvData.getName().isEmpty()) {
            redvData.setName("名無しのスーパーハッカー");
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date = now.format(fmt);

        return new DataToSend(redvData.getName(), redvData.getMessage(), date);
    }
}
