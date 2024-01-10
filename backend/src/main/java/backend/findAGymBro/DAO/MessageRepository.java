package backend.findAGymBro.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import backend.findAGymBro.Models.Message;
import backend.findAGymBro.Models.Chat;

public interface MessageRepository extends CrudRepository<Message, String> {
    Message findByMessageId(int messageId);
    Message findBySender(String sender);
    Message findByReceiver(String receiver);
    // find by both member
    Message findBySenderAndReceiver(String sender, String receiver);
    Message findMessageByChat(Chat chat);
    List<Message> findAllMessageByChat(Chat chat);
}