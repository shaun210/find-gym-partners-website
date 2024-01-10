package backend.findAGymBro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.findAGymBro.DAO.ChatRepository;
import backend.findAGymBro.DAO.MessageRepository;
import backend.findAGymBro.Models.Chat;
import backend.findAGymBro.Models.Message;

import java.sql.Timestamp;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    public Message createMessage(String messageDesc, int chatId, String sender, String receiver) {
        Chat chat = chatRepository.findByChatId(chatId);
        if (chat == null) {
            throw new IllegalArgumentException("Chat does not exist");
        }
        long currentTime = System.currentTimeMillis();
        Timestamp currentTimestamp = new Timestamp(currentTime);
        Message message = new Message(messageDesc, chat, currentTimestamp, sender, receiver);
        messageRepository.save(message);
        return message;
    }
}
