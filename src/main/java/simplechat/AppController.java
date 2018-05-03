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
    public DataToSend messageProcessing(DataToRecv data) {
        String name = data.getName();
        String message = data.getMessage();
        String time = getTime();

        if (name.isEmpty()) {
            name = "名無しのスーパーハッカー";
        }

        name = escape(name);
        message = escape(message);

        return new DataToSend(name, message, time);
    }

    private String escape(String str) {
        String result = HtmlUtils.htmlEscape(str);
        result = result.replace(" ", "&nbsp;");
        result = result.replace("\n", "<br>");
        return result;
    }

    private String getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
        return time.format(fmt);
    }
}