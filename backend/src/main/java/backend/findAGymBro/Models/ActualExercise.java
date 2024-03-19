package backend.findAGymBro.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.List;


@Entity
public class ActualExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private List<Integer> reps;
    @ManyToOne
    private Exercise exercise;
    private Timestamp date;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Workout workout;

    public ActualExercise() {
    }

    public ActualExercise(List<Integer> reps, Exercise exercise, Timestamp date, Member member, Workout workout) {
        this.reps = reps;
        this.exercise = exercise;
        this.date = date;
        this.member = member;
        this.workout = workout;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getReps() {
        return reps;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public Timestamp getDate() {
        return date;
    }

    public Member getMember() {
        return member;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setReps(List<Integer> reps) {
        this.reps = reps;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    @Override
    public String toString() {
        return "ActualExercise{" +
                "id=" + id +
                ", reps=" + reps +
                ", exercise=" + exercise +
                ", date=" + date +
                '}';
    }




}
