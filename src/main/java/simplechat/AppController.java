package simplechat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {

    @MessageMapping("/endpoint")
    @SendTo("/topic/messages")
    public DataToSend message(DataToRecv redvData) {
        if (redvData.getName().isEmpty()) {
            redvData.setName("名無しのスーパーハッカー");
        }
        return new DataToSend(redvData.getName(), redvData.getMessage());
    }
}
