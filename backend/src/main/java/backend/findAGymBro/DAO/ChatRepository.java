package backend.findAGymBro.DAO;

import org.springframework.data.repository.CrudRepository;
import backend.findAGymBro.Models.Chat;
import backend.findAGymBro.Models.Member;

public interface ChatRepository extends CrudRepository<Chat, String> {
    Chat findByChatId(int chatId);
    Chat findByMember1AndMember2(Member member1, Member member2);
}


