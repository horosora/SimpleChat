package simplechat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

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

        redvData.setName(escapeProcessing(redvData.getName()));
        redvData.setMessage(escapeProcessing(redvData.getMessage()));

        return new DataToSend(redvData.getName(), redvData.getMessage(), date);
    }

    private String escapeProcessing(String str) {
        String result = HtmlUtils.htmlEscape(str);
        result = result.replace(" ", "&nbsp;");
        result = result.replace("\n", "<br>");

        return result;
    }
}
