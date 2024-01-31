package backend.findAGymBro.DAO;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import backend.findAGymBro.Models.Member;
import backend.findAGymBro.Models.GymLevel;

public interface MemberRepository extends CrudRepository<Member, String> {
	Member findByUsername(String username);
    List<Member> findByFirstName(String firstName);
    List<Member> findByLastName(String lastName);
    Member findByEmail(String email);
    List<Member> findByFirstNameAndLastName(String firstName, String lastName);
    List<Member> findByGymLevelAndAddressTown(GymLevel gymLevel, String addressTown);
    @Query("SELECT COUNT(f) > 0 FROM Member m JOIN m.friends f WHERE m.username = :member1 AND f.username = :member2")
    boolean areFriends(@Param("member1") String member1, @Param("member2") String member2);
}