package simplechat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {

    @MessageMapping("/endpoint")
    @SendTo("/topic/messages")
    public DataToSend message(DataToRecv message) {
        return new DataToSend("test: " + message.getMessage());
    }
}
