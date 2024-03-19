package backend.findAGymBro.DAO;
import org.springframework.data.repository.CrudRepository;
// import backend.findAGymBro.Models.ActualExercise;
import backend.findAGymBro.Models.Exercise;
import backend.findAGymBro.Models.MuscleGroup;
import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer>{
    Exercise findById(int id);
    List<Exercise> findAllByMuscleGroup(MuscleGroup muscleGroup);
    Exercise findByName(String name);
}
