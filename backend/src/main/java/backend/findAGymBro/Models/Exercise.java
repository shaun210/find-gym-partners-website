package backend.findAGymBro.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;

@Entity
public class Exercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private MuscleGroup muscleGroup;
    private LocalDate date;
    @ManyToOne
    private Workout workout;
    private int[] sets; // [weight, reps]
    
    public Exercise() {
        this.sets = new int[2];
    }

    public Exercise(String name, MuscleGroup muscleGroup, LocalDate date, Workout workout) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.date = date;
        this.workout = workout;
        this.sets = new int[2];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public LocalDate getDate() {
        return date;
    }

    public Workout getWorkout() {
        return workout;
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", muscleGroup=" + muscleGroup +
                '}';
    }
}
