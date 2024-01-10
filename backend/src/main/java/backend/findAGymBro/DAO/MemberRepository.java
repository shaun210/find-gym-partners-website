package backend.findAGymBro.DAO;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import backend.findAGymBro.Models.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	Member findByUsername(String username);
    List<Member> findByFirstName(String firstName);
    List<Member> findByLastName(String lastName);
    Member findByEmail(String email);
    List<Member> findByFirstNameAndLastName(String firstName, String lastName);
}