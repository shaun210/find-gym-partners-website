package backend.findAGymBro.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import backend.findAGymBro.Services.ExerciseService;
import backend.findAGymBro.Services.WorkoutService;
import backend.findAGymBro.Models.Workout;
import backend.findAGymBro.DTO.IncomingExerciseDto;
import backend.findAGymBro.Models.MuscleGroup;
import backend.findAGymBro.Models.Exercise;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3050", "http://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com"})
@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping(value = {"/", ""})
    public ResponseEntity<Object> createWorkout(@RequestParam(value = "date") String date,
                                                @RequestParam(value = "MemberUsername") String memberUsername,
                                                @RequestParam(value = "exerciseName") String exerciseName,
                                                @RequestParam(value = "muscleGroupName") String muscleGroupName,
                                                @RequestBody List<IncomingExerciseDto> workoutSets) {
        try {
            int workoukId = workoutService.createWorkout(date, memberUsername);
            MuscleGroup muscleGroup = MuscleGroup.valueOf(muscleGroupName.toUpperCase());
            for (IncomingExerciseDto workoutSet  : workoutSets) {
                exerciseService.createExercise(exerciseName, muscleGroup, date, workoukId, workoutSet.getWeight(), workoutSet.getReps());
            }
            return ResponseEntity.ok(true);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = {"/getWorkoutById", "/getWorkoutById/"})
    public ResponseEntity<Object> getWorkoutById(@RequestParam(value = "workoutId") String workoutId) {
        try {
            return ResponseEntity.ok(workoutService.getWorkoutById(Integer.parseInt(workoutId)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = {"/getWorkoutByMember", "/getWorkoutByMember/"})
    public ResponseEntity<Object> getWorkoutByMember(@RequestParam(value = "username") String username) {
        try {
            return ResponseEntity.ok(workoutService.getWorkoutByMember(username));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
