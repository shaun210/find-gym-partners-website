package backend.findAGymBro.Controllers;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import backend.findAGymBro.Services.ExerciseService;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import backend.findAGymBro.Models.MuscleGroup;;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3050", "http://find-gym-partner-env.eba-8tda8qu8.us-east-1.elasticbeanstalk.com"})
@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    // @PostMapping(value = {"/", ""})
    // public ResponseEntity<Object> createExercise(@RequestParam(value = "name") String name,
    //                             @RequestParam(value = "muscleGroup") String muscleGroupName, @RequestParam(value = "date") String date) {

    //     try {
    //         MuscleGroup muscleGroup = MuscleGroup.valueOf(muscleGroupName.toUpperCase());
    //         return ResponseEntity.ok(exerciseService.createExercise(name, muscleGroup, date));
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    @GetMapping(value = {"/getExerciseById", "/getExerciseById/"})
    public ResponseEntity<Object> getExerciseById(@RequestParam(value = "exerciseId") String exerciseId) {
        try {
            return ResponseEntity.ok(exerciseService.getExerciseById(Integer.parseInt(exerciseId)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
