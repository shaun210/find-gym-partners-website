package backend.findAGymBro.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.findAGymBro.Models.Workout;
import backend.findAGymBro.DAO.WorkoutRepository;
import backend.findAGymBro.Models.Member;
import backend.findAGymBro.Models.Exercise;
import java.sql.Timestamp;
import java.time.LocalDate;
@Service
public class WorkoutService {
    
    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private MemberService memberService;

    @Transactional
    public int createWorkout(String date, String MemberUsername) {
        Member member = memberService.getMember(MemberUsername);
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        LocalDate localDate = LocalDate.parse(date);
        System.out.println(localDate);
        Workout workout = new Workout(localDate, member);
        workoutRepository.save(workout);
        // note that i did not add workout to member
        return workout.getId();
    }

    @Transactional
    public Workout getWorkoutById(int id) {
        return workoutRepository.findById(id);
    }

    @Transactional
    public Workout getWorkoutByMember(String username) {
        Member member = memberService.getMember(username);
        return workoutRepository.findByMember(member);
    }

    @Transactional
    public Workout addExerciseToWorkout(Exercise exercise, int workoutId) {
        Workout workout = workoutRepository.findById(workoutId);
        if (workout == null) {
            throw new IllegalArgumentException("Workout with id " + workoutId + " does not exist");
        }
        // add exercise to workout
        workout.getExercises().add(exercise);
        workoutRepository.save(workout);
        return workout;
    }
}
