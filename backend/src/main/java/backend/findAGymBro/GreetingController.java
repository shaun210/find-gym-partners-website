package backend.findAGymBro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import backend.findAGymBro.DTO.MessageDto;
import backend.findAGymBro.Services.MessageService;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3050", "http://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com"})
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
    String destination = "/topic/chat/" + message.getChatID();
    messagingTemplate.convertAndSend(destination, messageDto);
  }
}