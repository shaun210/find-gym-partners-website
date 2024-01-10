package backend.findAGymBro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.findAGymBro.Models.Member;
import backend.findAGymBro.Models.Chat;
import backend.findAGymBro.Models.Message;
import backend.findAGymBro.DAO.ChatRepository;
import backend.findAGymBro.DAO.MemberRepository;
import backend.findAGymBro.DAO.MessageRepository;

import java.util.List;

@Service
public class ChatService {
    
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MessageRepository messageRepository;


    // create chat
    @Transactional
    public Chat createChat(String member1Username, String member2Username) {
        try {
            Member[] members = fetchMember(member1Username, member2Username);
            if (chatRepository.findByMember1AndMember2(members[0], members[1]) != null || chatRepository.findByMember1AndMember2(members[1], members[0]) != null) {
                throw new IllegalArgumentException("Chat already exists");
            }
            Chat chat = new Chat(members[0], members[1]);
            chatRepository.save(chat);
            return chat;
        } catch (Exception e) {
            throw new IllegalArgumentException("Chat already exists");
        }
    }

    //check if chat exists
    @Transactional
    public boolean chatExists(String member1Username, String member2Username) {
        try {
            Member[] members = fetchMember(member1Username, member2Username);
            if (chatRepository.findByMember1AndMember2(members[0], members[1]) != null || chatRepository.findByMember1AndMember2(members[1], members[0]) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new IllegalArgumentException("Chat does not exist");
        }
    }

    // delete chat (use case: when a member deletes the chat or if they enter chat)
    @Transactional
    public boolean deleteChat(int chatId) {
        Chat chat = chatRepository.findByChatId(chatId);
        if (chat == null) {
            return false;
        }
        chatRepository.delete(chat);
        return true;
    }

    @Transactional
    public List<Message> getAllChatMessages(int chatId) {
        Chat chat = chatRepository.findByChatId(chatId);
        if (chat == null) {
            throw new IllegalArgumentException("Chat does not exist");
        }
        return messageRepository.findAllMessageByChat(chat);
    }

    @Transactional
    public int getChatId(String member1Username, String member2Username) {
        Member member1 = memberRepository.findByUsername(member1Username);
        Member member2 = memberRepository.findByUsername(member2Username);
        if (member1 == null || member2 == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        Chat chat = chatRepository.findByMember1AndMember2(member1, member2);
        if (chat == null) {
            chat = chatRepository.findByMember1AndMember2(member2, member1);
        }
        if (chat == null) {
            throw new IllegalArgumentException("Chat does not exist");
        }
        return chat.getChatId();
    }
    private Member[] fetchMember(String member1Username, String member2Username) {
        Member member1 = memberRepository.findByUsername(member1Username);
        Member member2 = memberRepository.findByUsername(member2Username);
        if (member1 == null || member2 == null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        return new Member[]{member1, member2};
    }

}