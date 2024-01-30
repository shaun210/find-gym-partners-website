package backend.findAGymBro.DAO;
import org.springframework.data.repository.CrudRepository;
import backend.findAGymBro.Models.FriendRequest;
import backend.findAGymBro.Models.Member;

import java.util.List;
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer>{
    // write a query to get all fr by receiver, and not accepted, ordered by sentDateTime

    List<FriendRequest> findAllByReceiverAndAcceptedOrderBySentDateTime(Member receiver, boolean accepted);
    FriendRequest findBySenderAndReceiver(Member sender, Member receiver);
}
