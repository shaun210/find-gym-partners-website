package backend.findAGymBro.DAO;
import org.springframework.data.repository.CrudRepository;
import backend.findAGymBro.Models.Workout;
import backend.findAGymBro.Models.Member;
import backend.findAGymBro.Models.Exercise;

public interface WorkoutRepository extends CrudRepository<Workout, Integer>{
    public Workout findById(int id);
    public Workout findByMember(Member member);
    public Workout findByExercises(Exercise exercise);
}
