package net.javaguides.springboot.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import net.javaguides.springboot.websocket.model.Greeting;
import net.javaguides.springboot.websocket.model.HelloMessage;

import java.util.Date;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @Scheduled(fixedRate = 3000)
    @SendTo("/topic/greetings")
    public Greeting send() throws Exception {
        HelloMessage message = new HelloMessage();
        message.setName("Rakesh at " + new Date());
        return greeting(message);
    }
}
