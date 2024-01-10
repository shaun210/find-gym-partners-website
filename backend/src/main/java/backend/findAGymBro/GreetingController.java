package backend.findAGymBro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import backend.findAGymBro.DTO.MessageDto;
import backend.findAGymBro.Models.Message;
import backend.findAGymBro.Services.MessageService;

@Controller
public class GreetingController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;


  @MessageMapping("/hello/")
  public void handleMessage(HelloMessage message) throws Exception {
    // Thread.sleep(1000); 

    MessageDto messageDto = new MessageDto(messageService.createMessage(message.getMessage(), Integer.parseInt(message.getChatID()), message.getSender(), message.getReceiver()));
    System.out.println("youpi + " + message.getChatID());
    String destination = "/topic/chat/" + message.getChatID();
    messagingTemplate.convertAndSend(destination, messageDto);
  }

  @MessageMapping("/hello")
  // @SendTo("/topic/greetings")
  public void greeting(DummyMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    String destination = "/topic/chat/6";
    messagingTemplate.convertAndSend(destination, new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
  }


}