package backend.findAGymBro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.findAGymBro.Models.Exercise;
import backend.findAGymBro.Models.MuscleGroup;
import backend.findAGymBro.DAO.ExerciseRepository;
import java.time.LocalDate;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutService workoutService;

    @Transactional
    public Boolean createExercise(String name, MuscleGroup muscleGroup, String dateString, int workoutId, int weight, int reps) {

        // check if exercise with name already exists
        if (exerciseRepository.findByName(name) != null) {
            throw new IllegalArgumentException("Exercise with name " + name + " already exists");
        }

        if (dateString == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        LocalDate date = LocalDate.parse(dateString);
        Exercise exercise = new Exercise(name, muscleGroup, date, workoutService.getWorkoutById(workoutId));
        exerciseRepository.save(exercise);
        workoutService.addExerciseToWorkout(exercise, workoutId);
        return true;
    }

    @Transactional
    public Exercise getExerciseById(int exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }


}
