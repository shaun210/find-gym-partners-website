package backend.findAGymBro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import backend.findAGymBro.DTO.ChatDto;
import backend.findAGymBro.DTO.MessageDto;
import backend.findAGymBro.Models.Chat;
import backend.findAGymBro.Services.ChatService;
import backend.findAGymBro.Services.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    //check if chat exists
    @RequestMapping(value = "/chatExists", method = RequestMethod.GET)
    public boolean chatExists(@RequestParam(value = "member1Username") String member1Username,
                            @RequestParam(value = "member2Username") String member2Username) {
        return chatService.chatExists(member1Username, member2Username);
    }

    //get all messages in chat
    @RequestMapping(value = "/allMessages", method = RequestMethod.GET)
    public List<MessageDto> getMessages(@RequestParam(value = "chatId") String chatId) {
        int chatIdInt = Integer.parseInt(chatId);
        return chatService.getAllChatMessages(chatIdInt).stream().map(message -> new MessageDto(message)).collect(Collectors.toList());
        
    }

    //get chat id
    @RequestMapping(value = "/chatId", method = RequestMethod.GET)
    public int getChatId(@RequestParam(value = "member1Username") String member1Username,
                            @RequestParam(value = "member2Username") String member2Username) {
        return chatService.getChatId(member1Username, member2Username);
    }

    //create chat
    @PostMapping(value = {"/createChat", "/createChat/"})
    public ChatDto createChat(@RequestParam(value = "member1Username") String member1Username,
                           @RequestParam(value = "member2Username") String member2Username) {
        Chat chat = chatService.createChat(member1Username, member2Username);
        return new ChatDto(chat);
    }

    // create a message
    @PostMapping(value = {"/createMessage", "/createMessage/"})
    public MessageDto createMessage(@RequestParam(value = "messageDesc") String messageDesc,
                                 @RequestParam(value = "chatId") String chatId,
                                 @RequestParam(value = "senderUsername") String sender,
                                 @RequestParam(value = "receiverUsername") String receiver) {
        return new MessageDto(messageService.createMessage(messageDesc, Integer.parseInt(chatId), sender, receiver));
    }

    //delete chat
    @RequestMapping(value = "/deleteChat", method = RequestMethod.DELETE)
    public boolean deleteChat(@RequestParam(value = "chatId") String chatId) {
        int chatIdInt = Integer.parseInt(chatId);
        return chatService.deleteChat(chatIdInt);
    }
} 